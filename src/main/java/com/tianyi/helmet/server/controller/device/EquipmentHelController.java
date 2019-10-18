package com.tianyi.helmet.server.controller.device;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.websocket.sender.MessageSender;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.TyBoxLineData;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.power.Menu;
import com.tianyi.helmet.server.service.client.HelmetUniversalService;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.HelmetBatteryService;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import com.tianyi.helmet.server.service.mqtt.MqttClientService;
import com.tianyi.helmet.server.service.mqtt.MqttConfig;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.AppAccountVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备管理
 * Created by xiayuan on 2018/10/19.
 */
@Controller
@RequestMapping("device")
@Api(value = "api", description = "设备管理")
public class EquipmentHelController {
    @Autowired
    private MqttClientService mqttClientService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VersionService versionService;
    @Autowired
    private HelmetBatteryService helmetBatteryService;
    @Autowired
    private TyBoxDataService tyBoxDataService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private HelmetUniversalService helmetUniversalService;

    private Logger logger = LoggerFactory.getLogger(EquipmentHelController.class);


    /**
     * 扫码入库
     *
     * @return
     */
    @RequestMapping(value = "ledger/helmet/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "扫码入库")
    @Transactional
    public AppAccountVo codeWarehousing(@RequestParam String deviceNumber,
                                        @RequestParam Integer affiliationId, @RequestParam Integer userId,
                                        @RequestParam String model, @RequestParam String batch,
                                        @RequestParam Integer categoryId, @RequestParam String version) {
        MessageSender sender = new MessageSender();
        String deviceUUID = HelmetImeiHolder.get();
        EquipmentLedger equipmentLedger = new EquipmentLedger();
        equipmentLedger.setUserId(userId);
        equipmentLedger.setAffiliationId(affiliationId);
        if (versionService.getVersion(version) == null) {
            versionService.addVersion(version);
        }
        int versionId = versionService.getVersion(version);
        equipmentLedger.setVersionId(versionId);
        equipmentLedger.setCategoryId(categoryId);
        equipmentLedger.setDeviceNumber(deviceNumber);
        equipmentLedger.setModel(model);
        equipmentLedger.setBatch(batch);
        equipmentLedger.setDeviceUUID(deviceUUID);
        equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_ADD);
        EquipmentLedger equip = equipmentService.selectByDeviceNumber(deviceNumber);
        if (equip != null) {
            sender.sendMessage(userId, "设备编号已存在.");
            return new AppAccountVo("601", "设备编号已存在.");
        }
        int rs = equipmentService.insert(equipmentLedger);
        if (rs == MyConstants.DEVICE_FIND_DUPLICATE) {
            sender.sendMessage(userId, "该设备已扫码.");
            return new AppAccountVo("600", "该设备已扫码.");
        }
        if (!(rs > 0)) {
            sender.sendMessage(userId, "入库失败.");
            return new AppAccountVo("602", "入库失败.");
        }
        sender.sendMessage(userId, "入库成功.");
        return new AppAccountVo("200", "入库成功.");

    }


    /**
     * 注销
     *
     * @return
     */
    @RequestMapping(value = "ledger/logout", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "注销")
    public AppAccountVo logout() {
        String deviceUUID = HelmetImeiHolder.get();
        if (StringUtils.isEmpty(deviceUUID)) {
            return new AppAccountVo("600", "参数传递错误");
        }

        EquipmentLedger device = equipmentService.selectByUUID(deviceUUID);
        device.setStatus(6);
        int rs = equipmentService.update(device);
        if (device == null) {
            return new AppAccountVo("600", "该设备不存在" + deviceUUID);
        }
        if (rs == MyConstants.DEVICE_FAIL) {
            return new AppAccountVo("600", "数据库操作失败");
        }
        return new AppAccountVo("200", "注销成功" + deviceUUID);

    }

    /**
     * 请求设备关联的蓝牙盒子MAC地址
     *
     * @return
     */
    @RequestMapping(value = "helmet/getBlueBoxMac", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "请求设备关联的蓝牙盒子MAC地址")
    public AppAccountVo selectBlueBoxMac() {
        String deviceUUID = HelmetImeiHolder.get();
        EquipmentLedger device = equipmentService.selectByUUID(deviceUUID);
        if (device == null) {
            return new AppAccountVo("600", "该设备未扫码.");
        }
        String blueMac = equipmentService.selectBlueBoxMac(device.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("blueMac", blueMac);
        return new AppAccountVo("200", "", data);
    }

    /**
     * 设备开机请求网易账户信息
     *
     * @return
     */
    @RequestMapping(value = "helmet/get/neUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "设备开机请求网易账户信息")
    public AppAccountVo getNeUser() {
        String deviceUUID = HelmetImeiHolder.get();
        EquipmentLedger device = equipmentService.selectByUUID(deviceUUID);
        if (device == null) {
            return new AppAccountVo("600", "该设备未扫码.");
        }
        Map<String, Object> resultMap = new HashMap<>();
        Integer userId = device.getUserId();
        if (userId == -1 || userId == 0) {
            resultMap.put("userId", -1);
            return new AppAccountVo("200", "头盔账号获取成功", resultMap);
        }
        User user = userService.selectById(userId);
        List<Menu> menus = userService.getHelmetMenuByUserId(userId);

        String defaultMenu="工单";
        List<String> menusName = new ArrayList<>();
        for (Menu m : menus) {
            menusName.add(m.getMenuName());
            if(m.getDefaultMenu()!=null && m.getDefaultMenu().intValue()==1){
                defaultMenu=m.getMenuName();
            }
        }
        resultMap.put("neUserName", user.getNeUserHel());
        NeteaseUser neUserInfo = neteaseUserService.selectByUsername(user.getNeUserHel());
        resultMap.put("yun_token", neUserInfo.getYunToken());
        resultMap.put("userName", user.getName());
        resultMap.put("deviceId", device.getId());
        resultMap.put("userId", device.getUserId());
        resultMap.put("menusName", menusName);
        resultMap.put("defaultMenu", defaultMenu);
        /**
         * 根据用户的角色查询所管理项目应用的哪个厂商音视频服务
         */
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalService.selectByUserId(user.getId());
        resultMap.put("company", helmetUniversalInfo.getAvprovider());//configService.getNeteaseCompany()
        if (redisTemplate.opsForValue().get(user.getNeUserHel()) != null) {
            redisTemplate.delete(user.getNeUserHel());
        }
        redisTemplate.opsForValue().set(user.getNeUserHel(), deviceUUID);
        return new AppAccountVo("200", "头盔账号获取成功", resultMap);
    }
    /**
     * 获取设备最新电量信息
     *
     * @return
     */
    @RequestMapping(value = "helmet/getLatestBattery", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取设备最新电量信息")
    public AppAccountVo getLatestBattery(@RequestParam String clientId) {
        int battery = helmetBatteryService.getLatestBattery(clientId);
        Map result = new HashMap();
        result.put("battery", battery);
        return new AppAccountVo("200", "获取信息成功", result);
    }
    /**
     * 设备请求发布头盔订阅消息
     *
     * @return
     */
    @RequestMapping(value = "helmet/pubHelmetMessage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发布头盔订阅的消息1电池2定位3陀螺仪4脑波5所有，格式1-2-4")
    public AppAccountVo pubHelmetMessage(@RequestParam(required = false) String types) {
        String deviceUUID = HelmetImeiHolder.get();
        EquipmentLedger device = equipmentService.selectByUUID(deviceUUID);
        if (device == null) {
            return new AppAccountVo("600", "该设备未扫码.");
        }
        // 发布订阅消息信息
        if(types!=null){
            String[] ts = types.split("-");
            if(ts.length>0){
                for (String t : ts) {
//                    publishHelmetMessage(deviceUUID, 0, 300, "TRUE");//Integer.parseInt(t)// 注销发送消息
                }
            }
        }
        return new AppAccountVo("200", "发布头盔订阅主题成功");
    }

    private void publishHelmetMessage(String id, Integer type, Integer timeslot, String alltime) {
        String topicName = MqttConfig.TOPIC_HM_DATASTATUS + id;
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(true);

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        map.put("timeslot", timeslot);
        map.put("alltime", alltime);

        JSONObject json = new JSONObject(map);

        String payLoadString = json.toString();
        message.setPayload(payLoadString.getBytes());

        mqttClientService.publishMessage(topicName, message);
    }

    @RequestMapping(value="tybox/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取黑盒子提取出来的数据列表")
    public ResponseVo listTyBoxLines(@RequestParam Integer page, String clientId, String time1, String time2) {

        Map<String, Object> map = PageListVo.createParamMap(page);
        List<TyBoxLineData> lineList = tyBoxDataService.selectTyBox(map);
        int count = tyBoxDataService.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(lineList);
        vo.setTotal(count);

        return ResponseVo.success(vo);
    }

    /**
     * 进入头盔tyboxline数据查询主页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value="tybox/index", method = RequestMethod.GET)
    @ApiOperation(value = "打开神户钢铁数据浏览界面")
    public String index(Model model) {/*
        Map<String, Object> map = PageListVo.createParamMap(1);
        List<TyBoxLineData> lineList = tyBoxDataService.selectTyBox(map);
        model.addAttribute("tyboxList", JSON.toJSONString(lineList));*/
        return "helmet/tyBoxList";
    }

}

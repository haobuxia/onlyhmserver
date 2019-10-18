package com.tianyi.helmet.server.controller.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.VideoLocation;
import com.tianyi.helmet.server.service.baidu.BaiduMapService;
import com.tianyi.helmet.server.service.client.UserComponent;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.HelmetBatteryService;
import com.tianyi.helmet.server.service.data.HelmetGpsService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanMapApiHelper;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * 手机端个人中心
 *
 * @author zhouwei
 * 2019/3/24 21:52
 * @version 0.1
 **/
@Controller
@RequestMapping("/app/person")
@Api(description= "手机端个人中心", value = "api")
public class AppPersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private HelmetBatteryService helmetBatteryService;
    @Autowired
    private HelmetGpsService helmetGpsService;
    @Autowired
    private BaiduMapService baiduMapService;
    @Autowired
    private TianYuanMapApiHelper tianYuanMapApiHelper;

    @ApiOperation("更新用户密码")
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo updatePwd(@ApiParam(required = true, value="旧密码") @RequestParam(value = "oldpwd")String oldpwd,
                                @ApiParam(required = true, value="新密码") @RequestParam(value = "pwd")String pwd) {
        if (isEmpty(pwd)) {
            return ResponseVo.fail("新密码不能为空");
        }
        if (pwd.length() > 15) {
            return ResponseVo.fail("密码长度不能超过15位");
        }

        User user = userService.selectById(TianyiUserHolder.get().getId());
        if (user == null) {
            return ResponseVo.fail("用户信息丢失");
        }
        if (!user.getPassword().equals(oldpwd)) {
            return ResponseVo.fail("旧的密码错误");
        }
        int cnt = userComponent.updateUserPassword(user, pwd, false);
        return cnt == 1 ? ResponseVo.success() : ResponseVo.fail("修改密码失败");
    }

    @ApiOperation("我的头盔信息")
    @RequestMapping(value = "myhelmet", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo myhelmet() {
        //根据用户查询头盔信息
        //查询版本号
        //查询登陆用户名
        //查询持有用户名
        List<EquipmentLedger> equipments = equipmentService.selectByUserId(TianyiUserHolder.get().getId());
        EquipmentLedger equipment;
        if (equipments.size() > 0) {
            equipment = equipments.get(0);
        }else{
            return ResponseVo.success();
        }
        String version = "";
        if (!isEmpty(equipment.getVersionId())) {
            List<Map<Integer, String>> versions = versionService.selectVersion(equipment.getVersionId());
            if (versions.size() > 0) {
                version = versions.get(0).get("version");
            }
        }

        User user = userService.selectById(equipment.getUserId());
        User possessorUser = null;
        if(!isEmpty(equipment.getPossessorId())) {
            //出库才有持有人
            possessorUser = userService.selectById(equipment.getPossessorId());
        }

        //获取定位信息
        HelmetGps helmetGps = helmetGpsService.getLatest(equipment.getDeviceUUID());
        String address = "";
        if (helmetGps != null && helmetGps.getLat() != null && helmetGps.getLon() != null) {
            JSONObject jo = tianYuanMapApiHelper.getLocation(helmetGps.getLon(), helmetGps.getLat(), false);
            if(jo!=null && jo.getBoolean("status")) {
                JSONArray ja = jo.getJSONArray("descdata");
                JSONObject ja0 = (JSONObject) ja.get(0);
                // 地理位置信息
                address = ja0.getString("description");
            }
        }

        //获取电量
        int helmetCurrentBattery = helmetBatteryService.getLatestBattery(equipment.getDeviceUUID());

        Map<String, Object> responseInfo = new HashMap<>();
        responseInfo.put("deviceNumber", equipment.getDeviceNumber());
        responseInfo.put("version", version);
        responseInfo.put("possessorName", possessorUser == null ? "" : possessorUser.getName());
        responseInfo.put("name", user == null ? "" : user.getName());
        responseInfo.put("address", address);
        responseInfo.put("battery", String.valueOf(helmetCurrentBattery));

        return ResponseVo.success(responseInfo);
    }
}

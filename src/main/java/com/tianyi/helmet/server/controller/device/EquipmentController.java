package com.tianyi.helmet.server.controller.device;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.entity.data.HelmetSensor;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.HelmetComponent;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.HelmetDataWebSocketService;
import com.tianyi.helmet.server.service.data.HelmetGpsService;
import com.tianyi.helmet.server.service.data.HelmetSensorService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.tianyi.helmet.server.vo.PageListVo.DEFAULT_PAGE_SIZE;

/**
 * 设备管理
 * Created by xiayuan on 2018/9/25.
 */
@Controller
@RequestMapping("device")
@Api(value = "api", description = "设备管理")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private HelmetGpsService helmetGpsService;
    @Autowired
    private HelmetDataWebSocketService helmetDataWebSocketService;
    @Autowired
    private HelmetComponent helmetComponent;
    @Autowired
    private HelmetSensorService helmetSensorService;

    private Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    /**
     * 通过deviceUUID查询设备列表
     *
     * @return
     */
    @RequestMapping(value = "ledger/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询设备列表")
    public ResponseVo getDeviceList(@RequestParam String deviceUUID) {
        logger.debug("getDeviceList调用");
        //这里只针对多选框的接口调用
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(deviceUUID);
        return ResponseVo.success(equipmentLedger);
    }

    /**
     * 通过条件查询设备列表
     *
     * @return
     */
    @RequestMapping(value = "ledger/get/keyword", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过条件查询设备列表")
    public ResponseVo getDeviceListByKeywords(@RequestParam(required = false) Integer affiliationId,
                                              @RequestParam(required = false) String userName,
                                              @RequestParam(required = false) Integer categoryId,
                                              @RequestParam(required = false) Integer versionId,
                                              @RequestParam(required = false) String model,
                                              @RequestParam(required = false) String batch,
                                              @RequestParam(required = false) String startTime,
                                              @RequestParam(required = false) String endTime,
                                              @RequestParam(required = false) Integer status,
                                              @RequestParam(required = false) String deviceNumber,
                                              @RequestParam Integer flag, @RequestParam Integer page) {
        logger.debug("getDeviceListByKeywords调用");

        Map<String, Object> resultMap = new HashMap<>();
        List<EquipmentLedger> list = new ArrayList<>();
        LoginUserInfo lui = LoginUserHolder.get();
        Integer userId = lui.getId();
        if (flag == null) {
            return ResponseVo.fail("参数传递错误");
        }
        Map<String, Object> map = resoveParams(affiliationId, userName, categoryId, versionId, model, batch, startTime, endTime, status, deviceNumber);

        map.put("flag", flag);
        //判断是不是田一的管理员
        User user = userService.selectById(userId);
        double count = 0;
        if (userName != null && userName != "") {
            //模糊查询用户姓名
            list = searchUserName(userName, affiliationId, map, user);
            //模糊查询之后的分页
            count = list.size();
            list = pageList(list, page);

        } else {
            if (user.getOrganisation() == 1) {//是 田一的单位id
                list = equipmentService.selectAll(map);
                list = pageList(list, page);
                count = equipmentService.countBy(map);
            } else {
                map.put("affiliationId", user.getOrganisation());
                list = equipmentService.select(map);
                list = pageList(list, page);
                count = equipmentService.countBy(map);
            }
        }
        //封装分页参数
        resultMap.put("list", list);
        resultMap.put("count", count);
        resultMap.put("pageSize", DEFAULT_PAGE_SIZE);
        resultMap.put("currentPage", page);
        resultMap.put("pageTotal", Math.ceil(count / DEFAULT_PAGE_SIZE));
        return ResponseVo.success(resultMap);

    }

    /**
     * 变更
     *
     * @return
     */
    @RequestMapping(value = "ledger/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "变更")
    public ResponseVo update(@RequestBody EquipmentLedger equipmentLedger) {

        if (equipmentLedger == null) {
            return ResponseVo.fail("参数传递错误");
        }
        equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_USER);
        int rs = equipmentService.update(equipmentLedger);
        if (rs == MyConstants.DEVICE_FIND_EMPTY) {
            return ResponseVo.fail("该设备不存在");
        }
        if (rs == MyConstants.DEVICE_FAIL) {
            return ResponseVo.fail("数据库操作失败");
        }
        return ResponseVo.success();

    }


    /**
     * 删除设备记录
     *
     * @return
     */
    @RequestMapping(value = "ledger/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除设备记录")
    public ResponseVo delete(@RequestParam String deviceUUID, @RequestParam String reason) {
        if (StringUtils.isEmpty(deviceUUID)) {
            return ResponseVo.fail("参数传递错误");
        }
        int rs = equipmentService.deleteById(deviceUUID, reason);
        if (rs == MyConstants.DEVICE_FIND_EMPTY) {
            return ResponseVo.fail("该设备不存在");
        }
        if (rs == MyConstants.DEVICE_FAIL) {
            return ResponseVo.fail("数据库操作失败");
        }
        if (!(rs > 0)) {
            return ResponseVo.fail("删除失败");
        }
        return ResponseVo.success();

    }

    /**
     * 出库登记
     * 相当于变更所属单位（新增所属单位）
     *
     * @return
     */
    @RequestMapping(value = "ledger/delivery", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "出库登记")
    public ResponseVo delivery(@RequestParam List<String> deviceUUIDs, @RequestParam Integer affiliationId) {

        if (affiliationId == null) {
            return ResponseVo.fail("参数传递错误");
        }
        Map<String, Object> map = new HashMap<>();
        int rs = 0;
        for (String deviceUUID : deviceUUIDs) {
            map.put("deviceUUID", deviceUUID);
            map.put("affiliationId", affiliationId);
            rs = equipmentService.updateUserId(map);
            if (rs == MyConstants.DEVICE_FIND_EMPTY) {
                return ResponseVo.fail("该设备不存在" + deviceUUID);
            }
            if (rs == MyConstants.DEVICE_FAIL) {
                return ResponseVo.fail("数据库操作失败");
            }
        }
        if (!(rs > 0)) {
            return ResponseVo.fail("设备出库失败");
        }
        return ResponseVo.success();

    }

    /**
     * 设备历史记录查询
     *
     * @return
     */
    @RequestMapping(value = "ledger/get/history", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "设备历史记录查询")
    public ResponseVo getHistoryListByKeywords(@RequestParam(required = false) Integer affiliationId,
                                               @RequestParam(required = false) String userName,
                                               @RequestParam(required = false) Integer categoryId,
                                               @RequestParam(required = false) Integer versionId,
                                               @RequestParam(required = false) String model,
                                               @RequestParam(required = false) String batch,
                                               @RequestParam(required = false) String startTime,
                                               @RequestParam(required = false) Integer status,
                                               @RequestParam(required = false) String deviceNumber,
                                               @RequestParam(required = false) String endTime, @RequestParam Integer page) {
        logger.debug("getHistoryListByKeywords调用");
        Map<String, Object> resultMap = new HashMap<>();
        List<EquipmentLedger> list = new ArrayList<>();
        LoginUserInfo lui = LoginUserHolder.get();
        Integer userId = lui.getId();

        Map<String, Object> map = resoveParams(affiliationId, userName, categoryId, versionId, model, batch, startTime, endTime, status, deviceNumber);
        //判断是不是田一的管理员
        User user = userService.selectById(userId);
        double count = 0;
        //关联查询持有人
        if (userName != null && userName != "") {
            list = searchUserNameHistory(userName, affiliationId, map, user);
            count = list.size();
            list = pageList(list, page);
        } else {
            if (user.getOrganisation() == 1) {//是
                list = equipmentService.selectAllHistory(map);
                list = pageList(list, page);
                count = equipmentService.countByHistory(map);
            } else {
                map.put("affiliationId", user.getOrganisation());
                list = equipmentService.selectHistory(map);
                list = pageList(list, page);
                count = equipmentService.countByHistory(map);
            }
        }
        //封装分页参数
        resultMap.put("list", list);
        resultMap.put("count", count);
        resultMap.put("pageSize", DEFAULT_PAGE_SIZE);
        resultMap.put("currentPage", page);
        resultMap.put("pageTotal", Math.ceil(count / DEFAULT_PAGE_SIZE));
        return ResponseVo.success(resultMap);
    }


    /**
     * 进入头盔地图定位首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> retVo = refreshAll();
        if (!retVo.isSuccess()) {
            return "redirect:/common/content403";
        }

        List<QuadrupleVo<String, String, Boolean, HelmetGps>> qvList = retVo.getData();//4个数据项分别是：clilentId,deviceNumber+userName,online,gps
//        logger.debug("进入已售头盔地图定位功能,头盔=" + tripleVoList);

        Set<String> clientIdSet = qvList.stream().map(trip -> trip.getOne()).collect(Collectors.toSet());
        String websocketToken = helmetDataWebSocketService.generateToken(clientIdSet);

        model.addAttribute("websocketToken", websocketToken);//所有设备在线状态传送token
        model.addAttribute("clientList", JSON.toJSONString(qvList));
        model.addAttribute("type", "helmet");
        return "helmet/helmet3And1";
    }


    /**
     * 生成websocket通讯的token
     *
     * @param clientId
     * @return
     */
    @RequestMapping("/gettoken")
    @ResponseBody
    public ResponseVo<String> generateWebsocketToken(@RequestParam(required = false) String clientId) {
        Set<String> clientIdSet = null;
        if (org.springframework.util.StringUtils.isEmpty(clientId)) {
            //所有设备
            ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> retVo = refreshAll();
            List<QuadrupleVo<String, String, Boolean, HelmetGps>> qvList = retVo.getData();//4个数据项分别是：clilentId,neUsername,online,gps
            clientIdSet = qvList.stream().map(trip -> trip.getOne()).collect(Collectors.toSet());
        } else {
            clientIdSet = new HashSet<>(Arrays.asList(clientId));
        }
        String websocketToken = helmetDataWebSocketService.generateToken(clientIdSet);
        return ResponseVo.success(websocketToken);
    }

    /**
     * 刷新所有头盔的定位传感器数据
     * 三个数据项分别是：clilentId,online,gps
     *
     * @return
     */
    @RequestMapping("/refreshall")
    @ResponseBody
    public ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> refreshAll() {
        LoginUserInfo userInfo = LoginUserHolder.get();
        List<EquipmentLedger> helmetList = null;
        Map<String, Object> map = new HashMap<>();
        if (userInfo.isAdmin() || userInfo.isDemo()) {
            map.put("status", 1);
            map.put("flag", 1);
            helmetList = equipmentService.selectAll(map);
        } else {
            //自己购买的
            map.put("affiliationId", userInfo.getOrganisation());// 所属单位
            map.put("status", 1);
            helmetList = equipmentService.selectAll(map);
        } /*else if (userInfo.isSales()) {
            //自己销售的
            helmetList = helmetService.selectBySalerId(userInfo.getId());
        } else {
            //机手看不到
            return ResponseVo.fail("您无权查看头盔数据");
        }*/

        List<QuadrupleVo<String, String, Boolean, HelmetGps>> clientList = helmetList
                .stream()
                .map(helmet -> {
                    HelmetGps gps = helmetGpsService.getLatest(helmet.getDeviceUUID());
                    User user = userService.selectById(helmet.getUserId());
                    QuadrupleVo<String, String, Boolean, HelmetGps> qv = new QuadrupleVo<>();
//                    if (user != null && helmet.getFlag() == 1) {
                    qv = new QuadrupleVo(helmet.getDeviceUUID(), helmet.getDeviceNumber()+"-"+user.getName()+"-"+user.getNeUserHel(), equipmentService.isOnLine(helmet.getDeviceUUID()), gps);

                    return qv;
                })
                .collect(Collectors.toList());
        ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> retVo = ResponseVo.success(clientList);
        return retVo;
    }

    @RequestMapping("ledger/clear")
    @ResponseBody
    public ResponseVo unbinded(@RequestParam String deviceUUID) {
        EquipmentLedger device = equipmentService.selectByUUID(deviceUUID);
        //20190129tianxujin修改注销只注销使用人信息，其他保持不变
        device.setStatus(6);
//        int rs = equipmentService.update(device);
        int rs = equipmentService.unbinded(device);
        if (rs > 0) {
            return ResponseVo.success();
        }
        return ResponseVo.fail("注销失败");
    }

    @RequestMapping("/refresh/range/{clientId}")
    @ResponseBody
    public ResponseVo<List<HelmetSensor>> refreshRange(@PathVariable String clientId, Long date1, Long date2) {
        ResponseVo checkVo = checkHelmetViewRight(clientId);
        if (checkVo != null)
            return checkVo;

        if (date1 == null)
            date1 = Dates.toDate(LocalDateTime.now().minusDays(1)).getTime();
        if (date2 != null)
            date2 = Dates.toDate(LocalDateTime.now()).getTime();

        List<HelmetSensor> sensorList = helmetSensorService.selectByClientId(clientId, date1, date2);
        logger.debug(clientId + " 头盔传感器数据：" + (sensorList == null ? 0 : sensorList.size()));
        return ResponseVo.success(sensorList);
    }

    private List<EquipmentLedger> searchUserName(String userName, Integer affiliationId, Map<String, Object> map, User user) {
        List<EquipmentLedger> list = new ArrayList<>();
        //用户名模糊查询
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", userName);
        map1.put("organisation", affiliationId);
        map.remove("start");
        map.remove("length");
        List<User> uList = userService.listByNoPage(map1);
        for (User u : uList) {
            map.put("userId", u.getId());
            List<EquipmentLedger> result = new ArrayList<>();
            if (user.getOrganisation() == 1) {//是 田一的单位id
                result = equipmentService.selectAll(map);
            } else {
                map.put("affiliationId", user.getOrganisation());
                result = equipmentService.select(map);
            }
            list.addAll(result);
        }
        return list;
    }

    private List<EquipmentLedger> searchUserNameHistory(String userName, Integer affiliationId, Map<String, Object> map, User user) {
        List<EquipmentLedger> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", userName);
        map1.put("organisation", affiliationId);
        map.remove("start");
        map.remove("length");
        List<User> uList = userService.listByNoPage(map1);
        for (User u : uList) {
            map.put("userId", u.getId());
            List<EquipmentLedger> result = new ArrayList<>();
            if (user.getOrganisation() == 1) {//是
                result = equipmentService.selectAllHistory(map);
            } else {
                map.put("affiliationId", user.getOrganisation());
                result = equipmentService.selectHistory(map);
            }

            list.addAll(result);
        }
        return list;
    }

    private List<EquipmentLedger> pageList(List<EquipmentLedger> list, int page) {
        List<EquipmentLedger> result = new ArrayList<>();
        for (int i = (page - 1) * 12; i < page * 12 && i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }


    /**
     * 检查当前用户对某个头盔的查看权限
     *
     * @param clientId
     * @return
     */

    private ResponseVo checkHelmetViewRight(String clientId) {
        EquipmentLedger helmet = equipmentService.selectByUUID(clientId);
        if (helmet == null) {
            return ResponseVo.fail("头盔不存在");
        }

        LoginUserInfo userInfo = LoginUserHolder.get();
        if (!userInfo.isAdmin() && !userInfo.isDemo()) {
            if (userInfo.isTianyuan())
                return ResponseVo.fail("天远账号无权查看");
            /*if (userInfo.isSales() && helmet.getSalerId() != userInfo.getId()) {
                logger.error("当前用户是销售员，查看的不是自己销售的头盔，不允许." + clientId);
                return ResponseVo.fail("销售员无权查看");
            } else */
            if (userInfo.isCustomer() && helmet.getAffiliationId() != userInfo.getId()) {
                logger.error("当前用户是客户，查看的不是自己购买的头盔，不允许." + clientId);
                return ResponseVo.fail("客户无权查看");
            }
        }
        return null;
    }

    private Map<String, Object> resoveParams(Integer affiliationId, String userName,
                                             Integer categoryId, Integer versionId, String model, String batch,
                                             String startTime, String endTime, Integer status, String deviceNumber) {
        Map<String, Object> map = new HashMap<>();
        if (affiliationId != null) {
            map.put("affiliationId", affiliationId);
        }
        if (categoryId != null) {
            map.put("categoryId", categoryId);
        }
        if (versionId != null) {
            map.put("versionId", versionId);
        }
        if (model != null && model != "") {
            map.put("model", model);
        }
        if (batch != null && batch != "") {
            map.put("batch", batch);
        }
        if (startTime != null) {
            map.put("startTime", startTime);
        }
        if (endTime != null) {
            map.put("endTime", endTime);
        }
        if (deviceNumber != null && deviceNumber != "") {
            map.put("deviceNumber", deviceNumber);
        }
        if (status != null) {
            map.put("status", status);
        }
        return map;
    }

    @RequestMapping(value = "ledger/list1")
    public String index1() {
        return "device/deviceList";
    }

    @RequestMapping(value = "ledger/list2")
    public String index2() {
        return "device/deviceDelivery";
    }

    @RequestMapping(value = "ledger/list3")
    public String index3() {
        return "device/deviceHistoryList";
    }

}

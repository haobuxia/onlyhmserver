package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.controller.device.EquipmentHelController;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.service.client.HelmetUniversalService;
import com.tianyi.helmet.server.service.data.HelmetOnlineStatusService;
import com.tianyi.helmet.server.vo.AppAccountVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头盔统一访问API入口
 * Created by tianxujin on 2019/6/12 13:49
 */
@Controller
@RequestMapping("aihelmet/us")
@Api(value = "api", description = "头盔统一管理")
public class HelmetUniversalController {
    private Logger logger = LoggerFactory.getLogger(HelmetUniversalController.class);
    @Autowired
    private HelmetUniversalService helmetUniversalService;

    /**
     * 头盔加载配置列表
     * @param uuid
     * @return
     */
    @RequestMapping(value="configs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo listConfigs(@RequestParam String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.listConfigs(uuid);
        return responseVo;
    }

    /**
     * 头盔加载小松工厂车辆列表
     * @param uuid
     * @return
     */
    @RequestMapping(value="customers", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo listCustomers(@RequestParam String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.listCustomers(uuid);
        return responseVo;
    }

    /**
     * 获取检查单列表信息
     * @param uuid
     * @param id
     * @return
     */
    @RequestMapping(value="works", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo listWorks(@RequestParam String uuid,@RequestParam String id) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(id)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.listWorks(uuid, id);
        return responseVo;
    }

    /**
     * 开始工单
     * @param uuid 头盔id
     * @param id 工单ID
     * @return
     */
    @RequestMapping(value="startcar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo startCar(@RequestParam String uuid,@RequestParam String id,@RequestParam String isQuick) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(id) || StringUtils.isEmpty(isQuick)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.startCar(uuid, id, isQuick);
        return responseVo;
    }

    /**
     * 结束工单
     * @param uuid 头盔id
     * @param id 工单ID
     * @return
     */
    @RequestMapping(value="endcar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo endCar(@RequestParam String uuid,@RequestParam String id,@RequestParam String isQuick) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(id) || StringUtils.isEmpty(isQuick)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.endCar(uuid, id, isQuick);
        return responseVo;
    }

    /**
     * 开始工单
     * @param uuid 头盔id
     * @param id 工单ID
     * @return
     */
    @RequestMapping(value="startorder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo startOrder(@RequestParam String uuid,@RequestParam String id,@RequestParam String isQuick) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(id) || StringUtils.isEmpty(isQuick)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.startOrder(uuid, id, isQuick);
        return responseVo;
    }

    /**
     * 结束工单
     * @param uuid 头盔id
     * @param id 工单ID
     * @return
     */
    @RequestMapping(value="endorder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo endOrder(@RequestParam String uuid,@RequestParam String id,@RequestParam String isQuick) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(id) || StringUtils.isEmpty(isQuick)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.endOrder(uuid, id, isQuick);
        return responseVo;
    }

    /**
     * 开始任务
     * @param uuid
     * @param orderNo
     * @param taskid
     * @return
     */
    @RequestMapping(value="starttask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo startTask(@RequestParam String uuid,@RequestParam String orderNo,@RequestParam String taskid) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(taskid) || StringUtils.isEmpty(orderNo)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.startTask(uuid, orderNo, taskid);
        return responseVo;
    }

    /**
     * 结束任务
     * @param uuid
     * @param orderNo
     * @param taskid
     * @return
     */
    @RequestMapping(value="endtask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo endTask(@RequestParam String uuid,@RequestParam String orderNo,@RequestParam String taskid,@RequestParam String pass,@RequestParam(required = false) String remark) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(taskid) || StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(pass)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        ResponseVo responseVo = helmetUniversalService.endTask(uuid, orderNo, taskid, pass, remark);
        return responseVo;
    }

    @RequestMapping("list")
    @ResponseBody
    public ResponseVo list() {
        Map<String, Object> param = new HashMap<>();
        return ResponseVo.success(helmetUniversalService.list(param));
    }

    /**
     * 上传头盔上线时长
     * @param id
     * @param userid
     * @param onlinetime
     * @param offlinetime
     * @return
     */
    @RequestMapping(value="loginlong", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo loginlong(@RequestParam String id,@RequestParam String userid,@RequestParam String onlinetime,@RequestParam String offlinetime) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(userid) || StringUtils.isEmpty(onlinetime) || StringUtils.isEmpty(offlinetime)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        String helmetImei = HelmetImeiHolder.get();
        ResponseVo responseVo = helmetUniversalService.loginLong(id, userid, onlinetime, offlinetime, helmetImei);
        return responseVo;
    }

    /**
     * 上传头盔充电时长
     * @param id
     * @param userid
     * @param onlinetime
     * @param offlinetime
     * @return
     */
    @RequestMapping(value="chargelong", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo chargelong(@RequestParam String id,@RequestParam String userid,@RequestParam String onlinetime,@RequestParam String offlinetime) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(userid) || StringUtils.isEmpty(onlinetime) || StringUtils.isEmpty(offlinetime)) {
            return ResponseVo.fail("600", "参数传递错误");
        }
        String helmetImei = HelmetImeiHolder.get();
        ResponseVo responseVo = helmetUniversalService.chargeLong(id, userid, onlinetime, offlinetime, helmetImei);
        return responseVo;
    }

    /**
     * 头盔加载配置列表
     * @return
     */
    @RequestMapping(value="contacts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<List<ContactState>> listContact() {
        String helmetImei = HelmetImeiHolder.get();
        ResponseVo<List<ContactState>> responseVo = helmetUniversalService.listContact(helmetImei);
        return responseVo;
    }
}

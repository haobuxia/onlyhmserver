package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.LoginUserTokenService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.vo.AppAccountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.tianyi.helmet.server.service.support.CacheKeyConstants.APP_USER_BY_IMEI;

/**
 * 系统用户二维码接口,便于头盔绑定天远/田一服务人员
 * <p>
 * <p>
 * Created by liuhanc on 2018/1/30.
 */
@Controller
@RequestMapping("serviceqr")
@Api(value = "api", description = "登陆二维码")
public class ServiceQrController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private LoginUserTokenService loginUserTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(ServiceQrController.class);

    //二维码账号绑定验证
    @RequestMapping(value = "/code/{token}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("二维码绑定验证")
    public AppAccountVo qrcodeBindPost(@ApiParam(required = true, value = "token") @PathVariable("token") String token,
                                       @ApiParam(value = "用户ID",required = true) @RequestParam int userId) {
        LoginUserInfo userInfo = loginUserTokenService.getLoginUserInfo(token);
        Map<String, Object> appLoginInfo = (Map<String, Object>) redisTemplate.opsForHash().get(APP_USER_BY_IMEI, token);
        if (userInfo == null && appLoginInfo == null) {
            //zhouwei 20190630 增加手机登陆信息的判断
            return new AppAccountVo("600", "请求URL无效");
        }
        /**
         * update by xiayuan 2018/10/19
         */
        String imei = HelmetImeiHolder.get();
        EquipmentLedger helmet = equipmentService.selectByUUID(imei);
        if (helmet == null) {
            return new AppAccountVo("600", "头盔不存在或者尚未初始化.imei=" + imei);
        }
        /**
         * update by xiayuan 2018/10/19
         *
         */
//
//        if(helmet.getUserId()!=0){
//            return new AppAccountVo("600", "头盔已绑定用户，请先注销再绑定新用户");
//        }
        User user = userService.selectById(userId);
        if (user == null) {
            return new AppAccountVo("600", "用户不存在userid=" + userId);
        }
        EquipmentLedger equipmentLedger = new EquipmentLedger();
        equipmentLedger.setUserId(userId);
        equipmentLedger.setDeviceUUID(imei);
        equipmentLedger.setStatus(1);
        int rs = equipmentService.update(equipmentLedger);
        if (rs>0) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("username", user.getName());
            return new AppAccountVo("200", "头盔绑定田一用户成功", dataMap);
        } else {
            return new AppAccountVo("600", "头盔绑定田一用户失败");
        }
    }

}

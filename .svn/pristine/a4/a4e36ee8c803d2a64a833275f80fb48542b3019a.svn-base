package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟头盔imei转换成天远账号的Authorization code拦截器
 */
@Component
public class MockTianYuanUserInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(MockTianYuanUserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HelmetImeiHolder.remove();
        String neUserName = request.getHeader("neUsername");
        if (StringUtils.isEmpty(neUserName)) {
            logger.error("模拟天远用户身份请求执行前,header中没找到neUsername参数.uri=" + request.getRequestURI());
            throw new TianyiException("请求中没有header neUsername");
        }
        /**
         * update by xiayuan 2018/10/10
         */
        String helmetImei="";
        Map<String, Object> map = new HashMap<>();
        map.put("neUserHel", neUserName);
        List<User> users = userService.listByNoPage(map);
        User user = users.get(0);
        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
        for (EquipmentLedger e : list) {
            String deviceUUID = (String) redisTemplate.opsForValue().get(neUserName);
            if (StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                helmetImei= deviceUUID;
            }
        }

        if (StringUtils.isEmpty(helmetImei)) {
            logger.error("模拟天远用户身份请求执行前,header中的neUsername没有对应的头盔.uri=" + request.getRequestURI() + ",neUsername=" + neUserName);
            throw new TianyiException("请求中header neUsername无对应头盔");
        }

        logger.debug("模拟天远用户身份请求执行前,header中找到neUsername参数:" + neUserName + ",对应头盔imei:" + helmetImei + ".uri=" + request.getRequestURI());

        HelmetImeiHolder.set(helmetImei);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HelmetImeiHolder.remove();
    }

}

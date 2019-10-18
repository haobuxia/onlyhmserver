package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 头盔imei转换成田一用户账号的拦截器
 */
@Component
public class TianyiUserInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(TianyiUserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String imei = HelmetImeiHolder.get();
        if (StringUtils.isEmpty(imei)) {
            throw new TianyiException("请求中没有header imei");
        }
/**
 * update by xiayuan 2018/10/10
 */
        EquipmentLedger helmet = equipmentService.selectByUUID(imei);
        User user;
        if (helmet == null) {
            //zhouwei 190316 不是头盔登陆，判断app登陆
            Map<String, Object> tianyiUserInfo = (Map<String, Object>) redisTemplate.opsForHash().get(CacheKeyConstants.APP_USER_BY_IMEI, HelmetImeiHolder.get());
            if (tianyiUserInfo == null) {
                logger.error("没有" + HelmetImeiHolder.get() + "的登陆信息");
                throw new RuntimeException("请登陆手机账号");
            }
            int tyUserId = (int) tianyiUserInfo.get("userId");
            user = userService.selectById(tyUserId);
            if (user == null) {
                logger.error("请求中的header imei[" + imei + "]对应的田一账号[" + tyUserId + "]并不存在,uri=" + request.getRequestURI() + ",method=" + request.getMethod() + ",header imei=" + request.getHeader("imei"));
                throw new RuntimeException("田一账号不存在");
            }
            //zhouwei 190316
        }else {

            Integer tyUserId = helmet.getUserId();
            if (tyUserId == null || tyUserId == 0) {
                logger.error("请求中的header imei[" + imei + "]对应头盔并未绑定田一账号,uri=" + request.getRequestURI() + ",method=" + request.getMethod() + ",header imei=" + request.getHeader("imei"));
                throw new TianyiException("请求中的header imei[" + imei + "]对应头盔并未绑定田一账号");
            }

            user = userService.selectById(tyUserId);
            if (user == null) {
                logger.error("请求中的header imei[" + imei + "]对应头盔绑定的田一账号[" + tyUserId + "]并不存在,uri=" + request.getRequestURI() + ",method=" + request.getMethod() + ",header imei=" + request.getHeader("imei"));
                throw new TianyiException("请求中的header imei[" + imei + "]对应头盔绑定的田一账号[" + tyUserId + "]并不存在");
            }
        }
        //
//        logger.debug("当前请求对应的头盔imei:" + imei + ",对应网易账号id:" + helmet.getNeUserId() + ",name:" + helmet.getNeUsername() + ",田一账号:" + tianyiUser.getUsername() + "," + tianyiUser.getNeUsername() + ",uri=" + request.getRequestURI() + ",method=" + request.getMethod());
        TianyiUserHolder.set(user);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        TianYuanUserHolder.remove();
    }

}

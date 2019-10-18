package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 头盔imei转换成天远账号的Authorization code拦截器
 */
@Component
public class TianYuanUserInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    /*@Autowired
    private HelmetService helmetService;
    @Autowired
    private TianYuanUserService tianYuanUserService;

    private Logger logger = LoggerFactory.getLogger(TianYuanUserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String imei = HelmetImeiHolder.get();
        if (StringUtils.isEmpty(imei)) {
            throw new TianyiException("请求中没有header imei");
        }

        Helmet helmet = helmetService.getHelmetByImei(imei);
        if (helmet == null) {
            logger.error("请求中的header imei无效:"+imei+",uri="+request.getRequestURI()+",method="+request.getMethod()+",header imei="+request.getHeader("imei"));
            throw new TianyiException("请求中的header imei无效:"+imei);
        }

        Integer tyUserId = helmet.getTianYuanUserId();
        if (tyUserId == null || tyUserId == 0) {
            logger.error("请求中的header imei["+imei+"]对应头盔并未绑定天远账号,uri="+request.getRequestURI()+",method="+request.getMethod()+",header imei="+request.getHeader("imei"));
            throw new TianyiException("请求中的header imei["+imei+"]对应头盔并未绑定天远账号");
        }

        TianYuanUser tianYuanUser = tianYuanUserService.selectById(tyUserId);
        if (tianYuanUser == null) {
            logger.error("请求中的header imei["+imei+"]对应头盔绑定的天远账号["+tyUserId+"]并不存在,uri="+request.getRequestURI()+",method="+request.getMethod()+",header imei="+request.getHeader("imei"));
            throw new TianyiException("请求中的header imei["+imei+"]对应头盔绑定的天远账号["+tyUserId+"]并不存在");
        }

        //
        logger.debug("当前请求对应的头盔imei:"+imei+",对应网易账号id:"+helmet.getNeUserId()+",name:"+helmet.getNeUsername()+",天远账号:"+tianYuanUser.getUsername()+","+tianYuanUser.getOprtId()+","+tianYuanUser.getOprtName()+",uri="+request.getRequestURI()+",method="+request.getMethod());
        TianYuanUserHolder.set(tianYuanUser);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        TianYuanUserHolder.remove();
    }*/

}

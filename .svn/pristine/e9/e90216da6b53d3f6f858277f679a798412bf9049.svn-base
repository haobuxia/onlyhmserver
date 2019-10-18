package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在输出里添加版本号属性
 *
 * @author liuhan
 * @since 1.0
 */
@Component
public class VersionInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(VersionInterceptor.class);
    @Autowired
    private ConfigService configService;

    private static final ThreadLocal<Long> processTime = new ThreadLocal();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        processTime.set(System.currentTimeMillis());
        //设置版本号
        if ("1".equalsIgnoreCase(configService.getResourceStaticVersionUseSysTime())) {
            request.setAttribute("version", System.currentTimeMillis());
        } else {
            request.setAttribute("version", configService.getResourceStaticVersion());
        }
        return true;
    }


}

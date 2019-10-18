package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 田一管理员才能进行的操作的拦截器
 */
@Component
public class TianyiAdminUserInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(TianyiAdminUserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo == null)
            return false;
        return userInfo.isAdmin();
    }

}

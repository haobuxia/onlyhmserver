package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 天远场景功能后台操作拦截器。
 *
 * @author liuhan
 * @since 1.0
 */
@Component
public class SceneQueryInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(SceneQueryInterceptor.class);

    @Autowired
    private TianYuanUserService tianYuanUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isAdmin() || userInfo.isDemo() || userInfo.isTianyuan()) {
            //管理员和天远用户才能访问
            String oprtId = "";
            if(userInfo.isTianyuan()){
                TianYuanUser tianYuanUser = tianYuanUserService.selectById(userInfo.getId());
                oprtId = tianYuanUser.getOprtId();
            }
            request.setAttribute("oprtId", oprtId);
            return true;
        } else {
            response.sendRedirect("/common/content403");
            return false;
        }
    }

}

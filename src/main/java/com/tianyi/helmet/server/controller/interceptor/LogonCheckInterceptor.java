package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.service.client.LoginUserTokenService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.support.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 用户登录状态拦截器。
 *
 * @author liuhan
 * @since 1.0
 */
@Component
public class LogonCheckInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LogonCheckInterceptor.class);
    @Autowired
    private InterceptorHelper interceptorHelper;
    @Autowired
    private LoginUserTokenService loginUserTokenService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private ConfigService configService;

    private static final ThreadLocal<Long> processTime = new ThreadLocal();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LoginUserHolder.remove();

        processTime.set(System.currentTimeMillis());
        //获得当前登录用户
        LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(request);
        //检查用户是否登录
        if(lui == null){
            request.setAttribute("isLogin",false);
            logger.debug("未登录用户访问:"+request.getRequestURI());
            interceptorHelper.writeNoAccessResponse(request,response,"用户未登录或登录过期");
            return false;
        }
        request.setAttribute("isLogin",true);

        LoginUserHolder.set(lui);

//        logger.debug("检查用户权限.username="+lui.getUsername());
        //@todo 检查 lui里的信息和当前信息是否存在不一致,防止cookie伪造.主要是ip,user-agent等检查

        //cookie延期
        loginUserTokenService.recordUserNewOperate(lui);
        response.addHeader(LoginUserTokenService.LOGINUSER_TOKEN_NAME,lui.getToken());
        cookieService.writeCookie(response,LoginUserTokenService.LOGINUSER_TOKEN_NAME,lui.getToken(),configService.getUserLogonExpireMinute() * 60);

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception{
        LoginUserHolder.remove();

        if(request.getRequestURI().startsWith("/video/play/")){
            long startTime = processTime.get();
            long endTime = System.currentTimeMillis();
            logger.debug("请求耗时："+(endTime-startTime)+"毫秒,uri="+request.getRequestURI());
        }
        processTime.remove();
    }

}

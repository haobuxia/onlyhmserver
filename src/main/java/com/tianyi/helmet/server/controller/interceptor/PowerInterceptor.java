package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.power.Api;
import com.tianyi.helmet.server.entity.power.RoleApi;
import com.tianyi.helmet.server.service.client.LoginUserTokenService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.power.ApiService;
import com.tianyi.helmet.server.service.power.RoleApiService;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tianyi.helmet.server.service.client.LoginUserTokenService.LOGINUSER_CACHE_NAME;

/**
 * 权限拦截器
 * Created by wenxinyan on 2018/10/11.
 */
@Component
public class PowerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

    @Autowired
    private InterceptorHelper interceptorHelper;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private RoleApiService roleApiService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private JsonRedisTemplate redisTemplate;
    @Autowired
    private LoginUserTokenService loginUserTokenService;
    @Autowired
    private EquipmentService equipmentService;

    private Logger logger = LoggerFactory.getLogger(PowerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String checkUri = request.getRequestURI();
        List<String> checkList = new ArrayList<>();
        int userId;
        boolean result = false;
        //userId，头盔访问从header中获取imei，网页访问从cookie中获取 @todo从redis中获取，以让权限和登录同步
        if (request.getHeader("imei") == null) {
//            Cookie cookie = cookieService.readCookie(request, "userId");
//            if(cookie == null){
//                logger.debug("未知用户访问:"+request.getRequestURI());
//                interceptorHelper.writeNoAccessResponse(request, response, "权限限制:未知用户！");
//                return result;
//            } else {
//                ui = cookie.getValue();
//            }
//
            LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(request);
            userId = lui.getId();
        } else {
            userId = equipmentService.selectByUUID(request.getHeader("imei")).getUserId();
        }

        User user = userService.selectById(userId);
        String[] roleCodes = user.getRoleCodes().split(",");
        for (int i = 0; i < roleCodes.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", Integer.parseInt(roleCodes[i]));
            List<RoleApi> roleApis = roleApiService.listByNoPage(map);
            for (RoleApi ra : roleApis) {
                Api api = apiService.selectById(ra.getApiId());
                if (!checkList.contains(api.getUrlName())) {
                    checkList.add(api.getUrlName());
                }
            }
        }

        for (String cu : checkList) {
            if (checkUri.matches(cu)) {
                result = true;
            }
        }
        if (!result) {
            interceptorHelper.writeNoAccessResponse(request, response, "权限限制:您没有执行该操作的权限！");
        }

        return result;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

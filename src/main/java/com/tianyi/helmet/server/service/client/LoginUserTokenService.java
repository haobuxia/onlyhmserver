package com.tianyi.helmet.server.service.client;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.support.CookieService;
import com.tianyi.helmet.server.util.EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 登录用户token
 *
 * Created by liuhanc on 2017/11/22.
 */
@Service
public class LoginUserTokenService {

    @Autowired
    private JsonRedisTemplate jedisTemplate;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private ConfigService configService;

    public static final String LOGINUSER_CACHE_NAME = "loginUser";
    public static final String LOGINUSER_TOKEN_NAME = "user-token";

    /**
     * 获得当前登录用户
     *
     * @param request
     * @return
     */
    public LoginUserInfo getCurrentLoginUserInfo(HttpServletRequest request){
        Cookie cookie = cookieService.readCookie(request,LoginUserTokenService.LOGINUSER_TOKEN_NAME);
        if(cookie == null){
            return null;
        }else{
            String token = cookie.getValue();
            LoginUserInfo lui = this.getLoginUserInfo(token);
            return lui;
        }
    }

    public String generateToken(LoginUserInfo lui){
        String json = JSON.toJSONString(lui);
        String token = EncoderUtil.md5(json);
        return token;
    }

    public String createUserToken(LoginUserInfo lui){
        String token = generateToken(lui);
        lui.setToken(token);
        jedisTemplate.opsForValue().set(LOGINUSER_CACHE_NAME+":"+token,lui,configService.getUserLogonExpireMinute(), TimeUnit.MINUTES);
        return token;
    }

    public LoginUserInfo getLoginUserInfo(String token){
        return (LoginUserInfo)jedisTemplate.opsForValue().get(LOGINUSER_CACHE_NAME+":"+token);
    }

    public void deleteUserToken(String token){
        jedisTemplate.opsForValue().getOperations().delete(LOGINUSER_CACHE_NAME+":"+token);
    }

    public boolean recordUserNewOperate(LoginUserInfo lui){
        lui.setLastOperateTime(System.currentTimeMillis());
        //update
        jedisTemplate.opsForValue().set(LOGINUSER_CACHE_NAME+":"+lui.getToken(),lui,configService.getUserLogonExpireMinute(), TimeUnit.MINUTES);
        return true;
    }
}

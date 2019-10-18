package com.tianyi.helmet.server.service.support;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  cookie操作相关服务
 *
 * @author liuhan
 * @since 1.0
 */
@Service
public class CookieService {

    public void writeCookie(HttpServletResponse response,String name,String value,int maxAgeSeconds){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAgeSeconds);
        cookie.setPath("/");
        writeCookie(response,cookie);
    }

    public void writeCookie(HttpServletResponse response,Cookie ck){
        ck.setPath("/");
        response.addCookie(ck);
    }

    public void deleteCookie(HttpServletResponse response,String name){
        writeCookie(response,name,"expired-now",0);//0表示立刻失效
    }

    public Cookie readCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie ck:cookies){
                if(cookieName.equals(ck.getName())){
                    return ck;
                }
            }
        }
        return null;
    }
}

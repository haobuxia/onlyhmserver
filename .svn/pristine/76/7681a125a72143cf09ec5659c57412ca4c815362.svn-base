package com.tianyi.helmet.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器判断
 *
 * Created by liuhanc on 2017/11/22.
 */
public class Browser {

    public static Map<String,Boolean> versions(String userAgent){
        Map<String,Boolean> mm = new HashMap<>();
        mm.put("trident",indexCheck(userAgent,"Trident"));//IE内核
        mm.put("presto",indexCheck(userAgent,"Presto"));//opera内核
        mm.put("webKit",indexCheck(userAgent,"AppleWebKit"));//苹果、谷歌内核
        mm.put("gecko",indexCheck(userAgent,"Gecko") && indexCheck(userAgent,"KHTML") );//火狐内核
        mm.put("mobile",matchCheck(userAgent,"/AppleWebKit.*Mobile.*/"));//是否为移动终端
        mm.put("ios",matchCheck(userAgent,"/\\(i[^;]+;( U;)? CPU.+Mac OS X/"));
        mm.put("android",indexCheck(userAgent,"Android") ||indexCheck(userAgent,"Adr") );
        mm.put("iPhone",indexCheck(userAgent,"iPhone"));//是否为iPhone或者QQHD浏览器
        mm.put("iPad",indexCheck(userAgent,"iPad"));
        mm.put("webApp",indexCheck(userAgent,"Safari"));//是否web应用程序，没有头部与底部
        mm.put("weixin",indexCheck(userAgent,"MicroMessenger"));
        mm.put("qq",matchCheck(userAgent,"/\\sQQ/i"));
        return mm;
    }

    private static boolean indexCheck(String userAgent,String checkStr){
        return userAgent.indexOf(checkStr) > -1;
    }

    private static boolean matchCheck(String userAgent,String matchReg){
        return userAgent.matches(matchReg);
    }
}

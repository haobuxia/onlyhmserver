package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.TianYuanUser;

/**
 * 天远用户token记录工具
 * <p>
 * Created by liuhanc on 2017/11/27.
 */
public class TianYuanUserHolder {
    private static final ThreadLocal<TianYuanUser> tl = new ThreadLocal();

    public static void set(TianYuanUser user) {
        tl.set(user);
    }

    //得到的是fullToken
    public static String getFullToken() {
        return tl.get().getFullToken();
    }

    public static String getAccessToken(){
        return tl.get().getAccessToken();
    }

    public static TianYuanUser get(){
        return tl.get();
    }

    public static String getTianYuanOprtId(){
        return get().getOprtId();
    }

    public static String getTianYuanOprtName(){
        return get().getOprtName();
    }

    public static void remove() {
        tl.remove();
    }
}

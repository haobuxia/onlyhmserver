package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.client.LoginUserInfo;

/**
 *  当前登录用户记录工具
 *
 * Created by liuhanc on 2017/11/27.
 */
public class LoginUserHolder {
    private static final ThreadLocal<LoginUserInfo> tl = new ThreadLocal();

    public static void set(LoginUserInfo lui) {
        tl.set(lui);
    }

    public static LoginUserInfo get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}

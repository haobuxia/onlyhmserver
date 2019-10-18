package com.tianyi.helmet.server.controller.interceptor;


import com.tianyi.helmet.server.entity.client.User;

/**
 * 田一用户记录工具
 * <p>
 * Created by liuhanc on 2017/11/27.
 */
public class TianyiUserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal();

    public static void set(User user) {
        tl.set(user);
    }

    public static User get() {
        return tl.get();
    }

    public static String getNeUserWeb() {
        return get().getNeUserWeb();
    }
    public static String getNeUserHel() {
        return get().getNeUserHel();
    }
    public static String getNeUserPhn() {
        return get().getNeUserPhn();
    }

    public static void remove() {
        tl.remove();
    }
}

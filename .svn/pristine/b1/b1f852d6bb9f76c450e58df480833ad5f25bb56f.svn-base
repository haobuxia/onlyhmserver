package com.tianyi.helmet.server.controller.interceptor;

/**
 * 头盔imei记录工具
 *
 * <p>
 * Created by liuhanc on 2017/11/27.
 */
public class HelmetImeiHolder {
    private static final ThreadLocal<String> tl = new ThreadLocal();

    public static void set(String imei) {
        tl.set(imei);
    }

    public static String get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}

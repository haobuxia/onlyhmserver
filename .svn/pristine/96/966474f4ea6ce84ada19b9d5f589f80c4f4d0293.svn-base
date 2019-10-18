package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.entity.log.OperaLog;

/**
 * 操作日志消息记录工具
 * 主要保存头盔号、UUID、日志流ID
 *
 * Created by wenxinyan on 2018/8/24.
 */
public class OperaLogHolder {
    private static final ThreadLocal<OperaLog> hol = new ThreadLocal();

    public static void set(OperaLog operaLog) {
        hol.set(operaLog);
    }

    public static OperaLog get() {
        return hol.get();
    }

    public static String getClientId() {
        return hol.get().getClientId();
    }

    public static String getUUID() {
        return hol.get().getUUID();
    }

    public static String getLogflowId() {
        return hol.get().getLogflowId();
    }

    public static void remove() {
        hol.remove();
    }
}

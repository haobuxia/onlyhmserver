package com.tianyi.helmet.server.controller.websocket.manager;

/**
 * Created by tianxujin on 19/01/16.
 */
public class WebSocketFactory {

    //创建在线人员管理工具
    public static IUserManager createManager(){
        return UserManager.getInstance();
    }

}

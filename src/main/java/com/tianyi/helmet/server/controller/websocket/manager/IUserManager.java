package com.tianyi.helmet.server.controller.websocket.manager;


import com.tianyi.helmet.server.controller.websocket.pojo.SocketUser;

import javax.websocket.Session;

/**
 * Created by tianxujin on 19/01/16.
 */
public interface IUserManager {

    boolean addUser(SocketUser user);

    boolean removeUser(SocketUser user);

    int getOnlineCount();

    SocketUser getUser(int userId);

}

package com.tianyi.helmet.server.controller.websocket;



import com.tianyi.helmet.server.controller.websocket.manager.WebSocketFactory;
import com.tianyi.helmet.server.controller.websocket.pojo.SocketUser;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by tianxujin on 19/01/16.
 */
@ServerEndpoint("/websocket/{uid}")
public class TianYiUserWebSocketServer {

    @OnOpen
    public  void open(Session session, @PathParam("uid") int uid){
        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(uid);

        //保存在线列表
        WebSocketFactory.createManager().addUser(user);
        print("当前在线用户："+WebSocketFactory.createManager().getOnlineCount());
    }

    @OnMessage
    public void receiveMessage(String message,Session session){
        //try {

        //}catch (Exception e){
          //  e.printStackTrace();
        //}
    }

    @OnError
    public void error(Throwable t) {
        print(t.getMessage());
    }

    @OnClose
    public void close(Session session){
        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(0);
        print("用户掉线");
        //移除该用户
        WebSocketFactory.createManager().removeUser(user);
       //print("当前在线用户："+LayIMFactory.createManager().getOnlineCount());

    }

    private void print(String msg){
        System.out.println(msg);
    }
}

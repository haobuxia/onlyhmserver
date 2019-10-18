package com.tianyi.helmet.server.controller.websocket.sender;

import com.tianyi.helmet.server.controller.websocket.manager.WebSocketFactory;
import com.tianyi.helmet.server.controller.websocket.pojo.SocketUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 发送信息类
 * Created by tianxujin on 19/01/16.
 */
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
    //给单个用户发
    public void sendMessage(Integer userId,String message){
        SocketUser user = WebSocketFactory.createManager().getUser(userId);
        if (user.isExist()) {
            if (user.getSession() == null) {
                logger.info("该用户不在线 ");
            } else {
                Session session = user.getSession();
                if (session.isOpen()) {
                    //构造用户需要接收到的消息
                    synchronized(session){
                        try {
                            session.getBasicRemote().sendText(message);
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                            e.printStackTrace();
                        }
                    }
//                    session.getAsyncRemote().sendText(message);
                }
            }
        }else{
            logger.info("该用户不在线 ");
        }
    }
}

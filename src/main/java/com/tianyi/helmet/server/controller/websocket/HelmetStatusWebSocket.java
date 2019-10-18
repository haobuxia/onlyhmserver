package com.tianyi.helmet.server.controller.websocket;

import com.tianyi.helmet.server.service.data.HelmetDataWebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * websocket方式推送回头状态数据到网页端
 *
 * Created by liuhanc on 2017/12/12.
 */
@ServerEndpoint(value = "/ws/helmet")
public class HelmetStatusWebSocket {
    private static final Logger logger = LoggerFactory.getLogger(HelmetStatusWebSocket.class);


    public static HelmetDataWebSocketService getInstance() {
        return ContextLoader.getCurrentWebApplicationContext().getBean(HelmetDataWebSocketService.class);
    }

    private HelmetDataWebSocketService helmetDataWebSocketService = getInstance();


    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
//        logger.debug("session连接.sessionid = " + session.getId());
        try{
            boolean success = helmetDataWebSocketService.verifySession(session);
            if (success) {
//            logger.debug("token检验通过.sessionId = " + session.getId());
            } else {
                logger.debug("token检验不通过，关闭websocket.sessionId = " + session.getId());
                try{
                    session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "token无效,请刷新页面"));
                }catch(Exception e){
                }
                return;
            }
        }catch(Exception e){
            logger.error("校验websocket session发生异常",e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        logger.debug("session关闭.sessionId = " + session.getId());
        try{
            helmetDataWebSocketService.removeSession(session.getId());
        }catch(Exception e){
            logger.error("websocket关闭事件处理异常",e);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        logger.debug("忽略来自客户端的消息:" + message + ",sessionId = " + session.getId());
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("websoscket发生错误.sessionId = " + session.getId()+",msg="+error.getMessage(),error);
//        helmetDataWebSocketService.removeSession(session.getId());
    }

}

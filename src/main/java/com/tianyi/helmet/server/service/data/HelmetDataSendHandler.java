package com.tianyi.helmet.server.service.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.SendHandler;
import javax.websocket.SendResult;

/**
 * 头盔传感器 websocket数据发送handler
 * Created by liuhanc on 2018/7/12.
 */

public class HelmetDataSendHandler implements SendHandler {
    private String sessionId;
    private HelmetDataWebSocketService service;
    private Logger logger = LoggerFactory.getLogger(HelmetDataSendHandler.class);

    public HelmetDataSendHandler(String sessionId, HelmetDataWebSocketService service) {
        this.sessionId = sessionId;
        this.service = service;
    }

    public void onResult(SendResult var1) {
        if (var1.isOK()) {
//                logger.debug("发送头盔数据到websocket成功");
        } else {
            logger.error("发送头盔数据到websocket失败.", var1.getException());
            service.removeSession(this.sessionId);
        }
    }
}

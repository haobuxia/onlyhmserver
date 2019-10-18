package com.tianyi.helmet.server.service.rabbitmq;

/**
 * Created by tianxujin on 2019/8/30 16:12
 */
public interface MQProducer {
    /**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String queueKey, Object object);
}

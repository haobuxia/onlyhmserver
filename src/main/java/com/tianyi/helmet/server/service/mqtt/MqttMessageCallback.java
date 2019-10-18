package com.tianyi.helmet.server.service.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

/**
 * 发布消息的回调类
 *
 * wenxinyan        2018-8-14       修改connectionLost()
 */
public class MqttMessageCallback implements MqttCallback {
    private Logger logger = LoggerFactory.getLogger(MqttMessageCallback.class);
    private BiConsumer<String, MqttMessage> messageConsumer;

    public MqttMessageCallback(BiConsumer<String, MqttMessage> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void connectionLost(Throwable cause) {
        /*连接中断时抛出异常信息
        2018-08-14 wxy
        */
        logger.error("mqtt client connection is lost by :"+cause);
        for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
            logger.error(stackTraceElement.toString());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // publish后会执行到这里
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if(messageConsumer != null){
            messageConsumer.accept(topic,message);
        }
    }

}
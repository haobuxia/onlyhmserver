package com.tianyi.helmet.server.service.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.function.BiConsumer;

/**
 * 订阅消息的回调类
 *
 */
public class MqttMessageSubscribeListener implements IMqttMessageListener {

    private BiConsumer<String,MqttMessage> messageConsumer;
    private String clientId;

    public MqttMessageSubscribeListener(String clientId,BiConsumer<String,MqttMessage> messageConsumer){
        this.clientId = clientId;
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception{
        messageConsumer.accept(topic,message);
    }
}
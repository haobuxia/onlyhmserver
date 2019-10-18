package com.tianyi.helmet.server.service.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * mqtt消息订阅和接收处理器配置
 * <p>
 * Created by liuhanc on 2018/6/30.
 */
@Component
public class MqttTopicProcessorConfig {
    @Autowired
    private MqttBackendConsumer mqttBackendConsumer;

    private Map<String, BiConsumer<String, MqttMessage>> helmetTopicConsumerMap = new HashMap(8);
    private Map<String, BiConsumer<String, MqttMessage>> mobileTopicConsumerMap = new HashMap(2);

    @PostConstruct
    private void initConfig() {
        helmetTopicConsumerMap.put("/sensor/helmet", mqttBackendConsumer::processHelmetSensorData);
        helmetTopicConsumerMap.put("/gps/helmet", mqttBackendConsumer::processGpsData);
        helmetTopicConsumerMap.put("/state/helmet", mqttBackendConsumer::processStateData);
        helmetTopicConsumerMap.put("/heartbeat/helmet", mqttBackendConsumer::processNewStateData);
        helmetTopicConsumerMap.put("/battery/helmet", mqttBackendConsumer::processBatteryData);
        helmetTopicConsumerMap.put("/mindwaver/helmet", mqttBackendConsumer::processMindWaverData);
        helmetTopicConsumerMap.put("/log/helmet", mqttBackendConsumer::processOperLog);
        
        mobileTopicConsumerMap.put("/gps/mobile", mqttBackendConsumer::processGpsData);
        mobileTopicConsumerMap.put("/state/mobile", mqttBackendConsumer::processStateData);
    }

    public Map<String, BiConsumer<String, MqttMessage>> getHelmetTopicConsumerMap() {
        return helmetTopicConsumerMap;
    }

    public Map<String, BiConsumer<String, MqttMessage>> getMobileTopicConsumerMap() {
        return mobileTopicConsumerMap;
    }

}

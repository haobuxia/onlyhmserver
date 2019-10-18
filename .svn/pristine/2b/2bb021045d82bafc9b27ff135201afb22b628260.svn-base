package com.tianyi.helmet.server.service.mqtt;

import com.tianyi.helmet.server.service.support.ConfigService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * mqtt客户端
 * Created by liuhanc on 2017/9/23.
 *
 * wenxinyan        2018-8-14       修改createClient(),setReconnectWhenLost()
 */
@Component
public class MqttClientService {
    private Logger logger = LoggerFactory.getLogger(MqttClientService.class);

    @Autowired
    MqttConfig mqttConfig;
    @Autowired
    ConfigService configService;

    private MqttConnectOptions options = null;
    private MqttClientPersistence persistence = null;
    private static MqttClient client = null;

    @PostConstruct
    private void init() {
        options = mqttConfig.getMqttConnectOptions();

        persistence = new MemoryPersistence();

        client = createClient(configService.getMqttBackendClientId(), null);
    }

    /**
     * 根据clientId创建客户端
     *
     * @param clientId
     * @return
     */
    public MqttClient createClient(String clientId, BiConsumer<String, MqttMessage> messageConsumer) {
        try {
            MqttClient client = new MqttClient(mqttConfig.getServerUrl(), clientId, persistence);
            /*在发布消息和订阅消息时都设置回调
            2018-08-14 wxy
            */
            /*if (messageConsumer != null) {
                MqttCallback callback = new MqttMessageCallback(messageConsumer);
                client.setCallback(callback);
            }*/
            MqttCallback callback = new MqttMessageCallback(messageConsumer);
            client.setCallback(callback);
            client.connect(options);
            //设置订阅端断线重连
            setReconnectWhenLost(client, 60);
            return client;
        } catch (MqttException e) {
            logger.error("createClient exception", e);
        }
        return null;
    }

    /**
     * 发布消息
     *
     * @param topicName
     * @param message
     */
    public void publishMessage(String topicName, MqttMessage message) {
        if (client == null) return;
        try {
            MqttTopic topic = client.getTopic(topicName);
            IMqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();
        } catch (Exception e) {
            logger.error("publishMessage exception", e);
        }
    }

    /**
     * 订阅某个topic的消息并制定消息消费方式以及连接断线检测时间间隔
     *
     * @param topicName
     * @param messageConsumer
     * @return
     */
    public ScheduledFuture subscribeMessage(String topicName, BiConsumer<String, MqttMessage> messageConsumer) {
        try {
            // 订阅消息
            String[] topicFilters = {topicName};
            int[] Qos = {1};
            if (messageConsumer != null) {
                MqttMessageSubscribeListener listner = new MqttMessageSubscribeListener(client.getClientId(), messageConsumer);
                client.subscribe(topicFilters, Qos, new IMqttMessageListener[]{listner});
            } else {
                client.subscribe(topicFilters, Qos);
            }
        } catch (MqttException e) {
            logger.error("subscribeMessage exception", e);
        }
        return null;
    }

    /**
     * 取消消息订阅
     *
     * @param topicName
     * @param future
     */
    public void unscribeMessage(String topicName, ScheduledFuture future) {
        try {
            String[] topicFilters = {topicName};
            client.unsubscribe(topicFilters);

            if (future != null) {
                future.cancel(true);
            }

            logger.debug("退订完毕.topic=" + topicName + ",客户端Id=" + client.getClientId());
        } catch (MqttSecurityException e) {
            logger.error("unscribeMessage exception", e);
        } catch (MqttException e) {
            logger.error("unscribeMessage exception", e);
        }
    }

    /**
     * 断开与mqtt server 的连接，不再连接
     *
     */
    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
        }
    }

    /**
     * 监控连接状态如果断掉则自动重连
     *
     * @param client1
     */
    protected ScheduledFuture setReconnectWhenLost(MqttClient client1, int checkIntervalSeconds) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        /*
        打印日志监控重连执行过程
        2018-08-14 wxy
         */
        logger.info("start mqtt client reconnect future.");
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                logger.debug("check mqtt client is connected.");

                if (!client1.isConnected()) {
                    logger.info("mqtt client is disconnected, ready to reconnect.");
                    try {
                        client1.connect(options);
                    } catch (Exception e) {
                        logger.error("mqtt clinet reconnect exception:"+e);
                    }
                }
            }
        }, 0 * 1000, checkIntervalSeconds * 1000, TimeUnit.MILLISECONDS);
        return future;
    }

    public void testPublishMessage(MqttClient testClient, String topicName, MqttMessage message){
        if (testClient == null) return;
        try {
            MqttTopic topic = testClient.getTopic(topicName);
            IMqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();
        } catch (Exception e) {
            logger.error("publishMessage exception", e);
        }
    }

}

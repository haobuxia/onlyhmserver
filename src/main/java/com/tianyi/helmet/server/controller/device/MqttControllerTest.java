package com.tianyi.helmet.server.controller.device;


import com.tianyi.helmet.server.service.mqtt.MqttClientService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Created by xiayuan on 2018/10/17.
 */
@Controller
public class MqttControllerTest {

    @Autowired
    MqttClientService mqttClientService;

    @RequestMapping("test")
    public void test() {
        MyThread myThreads = new MyThread();

        for (int i = 0; i < 100; i++) {
            System.out.println("###" + i + "%%%");
            new Thread(myThreads).start();
        }
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            UUID clientId = UUID.randomUUID();

            System.out.println("--------------1--------" + clientId);
            String topicName = "/heartbeat/helmet";
            MqttClient client = mqttClientService.createClient(clientId + "@pub", null);
            System.out.println("--------------2--------" + clientId);
            MqttMessage message = new MqttMessage();
            message.setQos(1);
            message.setRetained(true);
            System.out.println("--------------3--------" + clientId);
            for (int i = 0; i < 1; i++) {
                System.out.println("--------------4--------" + clientId);

                String payLoadString = "【测试消息.批次=" + i + ",发布topic=" + topicName + ",发布time=" + LocalDateTime.now().toString() + ",发布客户端Id=" + clientId + "@publish】";
                message.setPayload(payLoadString.getBytes());

                mqttClientService.testPublishMessage(client, topicName, message);
                System.out.println("发布消息完毕.topic=" + topicName + ",发布客户端id=" + clientId + "@publish,内容=" + payLoadString);
                System.out.println("--------------5--------" + clientId);
//                try {
////                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                }
            }

        }
    }


}

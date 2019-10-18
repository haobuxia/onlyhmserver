package com.tianyi.helmet.server.service.rabbitmq.listeners;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.mqtt.MqttClientService;
import com.tianyi.helmet.server.service.mqtt.MqttConfig;
import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianxujin on 2019/8/31 9:17
 */
@Component
public class QueueListenter implements MessageListener {
    @Autowired
    private UserService userService;
    @Autowired
    private MqttClientService mqttClientService;
    @Autowired
    private EquipmentService equipmentService;
    @Override
    public void onMessage(Message msg) {
        try{
            System.out.print(msg.toString());
            Map<String, Object> message = JSONObject.parseObject(msg.getBody(), HashMap.class);
            // 1、获取消息体
            JSONObject messageJson = (JSONObject) message.get("message");
            // 2、获取用户账户信息
            String userAccount = messageJson.getString("userAccount");
            Integer userId = -1;
            if(!StringUtils.isEmpty(userAccount)) {
                User user = userService.selectByTianyuanAccount(userAccount);
                if(user != null) {
                    userId = user.getId();
                }
            } else {
                // 3、获取头盔编号
                String deviceNumber = messageJson.getString("deviceNumber");
                if(!StringUtils.isEmpty(deviceNumber)) {
                    EquipmentLedger equipmentLedger = equipmentService.selectByDeviceNumber(deviceNumber);
                    if(equipmentLedger != null)
                    userId = equipmentLedger.getUserId();
                }
            }
            // 4、发送mqtt消息
            if(userId != -1)
            sendMqttMessage(messageJson, userId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void sendMqttMessage(JSONObject messageJson, Integer userId) {
        String topicName = "/workorder/update/" + userId;
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(true);

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", messageJson.getString("orderId"));
        JSONObject json = new JSONObject(map);
        String payLoadString = json.toString();
        message.setPayload(payLoadString.getBytes());
        mqttClientService.publishMessage(topicName, message);
    }

}

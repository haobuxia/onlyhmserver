package com.tianyi.helmet.server.controller.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.mqtt.MqttClientService;
import com.tianyi.helmet.server.service.mqtt.MqttConfig;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenxinyan on 2018/9/19.
 */
@Controller
@RequestMapping("mqtt")
public class MqttController {

    private Logger logger = LoggerFactory.getLogger(MqttController.class);
    @Autowired
    private MqttClientService mqttClientService;

    @RequestMapping("getStatusData")
    @ResponseBody
    public ResponseVo getStatusData(@RequestParam String id, @RequestParam Integer type, @RequestParam Integer timeslot, @RequestParam String alltime){
//        String topicName = "/datastatus/helmet/"+id;
        String topicName = MqttConfig.TOPIC_HM_DATASTATUS+"id";
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(true);

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        map.put("timeslot", timeslot);
        map.put("alltime", alltime);

        JSONObject json = new JSONObject(map);

        String payLoadString = json.toString();
        message.setPayload(payLoadString.getBytes());

        mqttClientService.publishMessage(topicName, message);

        return ResponseVo.success();
    }
}

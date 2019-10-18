package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.mqtt.MqttClientService;
import com.tianyi.helmet.server.service.mqtt.MqttTopicProcessorConfig;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * mqtt的系统订阅工具，随系统启动
 * <p>
 * <p>
 * Created by liuhanc on 2017/10/9.
 *
 * wenxinyan        2018-8-16       修改addNewSubscribeTopic()
 */
@Component
public class MqttBackendSubscribeJob extends AbstractContextJob {

    private Logger logger = LoggerFactory.getLogger(MqttBackendSubscribeJob.class);
    @Autowired
    private ConfigService configService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MqttClientService mqttClientService;
    @Autowired
    private MqttTopicProcessorConfig mqttTopicProcessorConfig;
    @Autowired
    private UserService userService;

    //private MqttClient backendClient = null;


    @Override
    public boolean thisJobStart() {
        return configService.getMqttJobStart() == 1;
    }

    /**
     * 启动订阅工作
     */
    @Override
    public void startJob() {
//        logger.info("MqttSystemSubscriber start...");
//        backendClient = mqttClientService.createClient(configService.getMqttBackendClientId(), null);
//        if (backendClient == null) {
//            logger.error("创建mqtt客户端以便订阅头盔消息时，创建客户端失败，则不订阅头盔消息");
//            return;
//        }
//        subscribeHelmetData();  //兼容旧版每个头盔使用其各自的主题
//        subscribeMobileAppData();//@todo 手机端报文规范还未确定,topic命名方式未定
        addUnifiedTopic();  //新版所有头盔统一主题
    }

    /**
     * 订阅统一主题
     */
    public void addUnifiedTopic() {
        try {
            Map<String, BiConsumer<String, MqttMessage>> map = mqttTopicProcessorConfig.getHelmetTopicConsumerMap();
            map.entrySet().stream().forEach(entry -> {
                String topicName = entry.getKey();
                this.addNewSubscribeTopic(topicName, entry.getValue());
            });
        } catch (Exception e) {
            logger.error("添加mqtt订阅异常:::::", e);
        }
    }

    /**
     * 添加某个头盔到订阅中
     *
     * @param neUserName
     */
    public void addNewHelmetTopic(String neUserName) {
        try {
            Map<String, BiConsumer<String, MqttMessage>> map = mqttTopicProcessorConfig.getHelmetTopicConsumerMap();
            map.entrySet().stream().forEach(entry -> {
                String topicName = entry.getKey() + "/" + neUserName;
                this.addNewSubscribeTopic(topicName, entry.getValue());
            });
        } catch (Exception e) {
            logger.error("添加头盔用户到mqtt订阅异常.neUserName=" + neUserName, e);
        }

    }

    /**
     * 添加某个手机到订阅中
     *
     * @param neUserName
     */
    public void addNewMobileTopic(String neUserName) {
        try {
            Map<String, BiConsumer<String, MqttMessage>> map = mqttTopicProcessorConfig.getMobileTopicConsumerMap();
            map.entrySet().stream().forEach(entry -> {
                String topicName = entry.getKey() + "/" + neUserName;
                this.addNewSubscribeTopic(topicName, entry.getValue());
            });
        } catch (Exception e) {
            logger.error("添加手机用户到mqtt订阅异常.neUserName=" + neUserName, e);
        }
    }


    /**
     * 添加新的订阅主题
     *
     * @param topicName
     */
    public void addNewSubscribeTopic(String topicName, BiConsumer<String, MqttMessage> consumer) {
        /*打印订阅日志信息
        2018-08-14 wxy
        */
        logger.info("订阅mqtt主题:" + topicName);
        try {
            mqttClientService.subscribeMessage(topicName, consumer);
        } catch (Exception e) {
            logger.error("mqtt订阅传感器主题异常.topicName=" + topicName, e);
        }
    }

    /**
     * 订阅有效头盔的传感器数据
     * update by xiayuan
     */
    protected void subscribeHelmetData() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        List<EquipmentLedger> helmetList = equipmentService.selectAll(map);
        helmetList.stream().forEach(helmet -> {
            User user = userService.selectById(helmet.getUserId());
            String neUsername = user.getNeUserHel();
            addNewHelmetTopic(neUsername);
        });
    }

    /**
     * 订阅手机app的传感器数据
     */
    protected void subscribeMobileAppData() {
        //手机端传输的报文格式未定。可能是网易账号也可能是田一账号
        //@todo
        Map<String, Object> map = new HashMap<>(1);
        map.put("status", 1);
        List<User> userList = userService.listBy(map);
        userList.stream().forEach(user -> {
            String hadNeUserName = user.getNeUserPhn();
            addNewMobileTopic(hadNeUserName);
        });
    }

}

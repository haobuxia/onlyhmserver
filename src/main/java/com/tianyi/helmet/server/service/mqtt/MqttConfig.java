package com.tianyi.helmet.server.service.mqtt;

import com.tianyi.helmet.server.service.support.ConfigService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * mqtt配置
 *
 * Created by liuhanc on 2017/9/23.
 */
@Component
public class MqttConfig implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(MqttConfig.class);

    @Autowired
    private ConfigService configService;
    private String serverUrl;

    public final static String TOPIC_HM_WORKORDER = "/workorder/helmet/";
    public final static String TOPIC_HM_DATASTATUS= "/datastatus/helmet/";

    public void afterPropertiesSet() throws Exception{
        logger.info("MqttConfig 配置.protocal="+configService.getMqttProtocal()+",server="+configService.getMqttServer()+",port="+configService.getMqttPort());

        this.serverUrl = configService.getMqttProtocal()+ configService.getMqttServer()+":"+  configService.getMqttPort();
    }

    public String getServerUrl(){
        return serverUrl;
    }

    public MqttConnectOptions getMqttConnectOptions(){
        // MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        if(!StringUtils.isEmpty(configService.getMqttUser()))
            options.setUserName(configService.getMqttUser());
        if(!StringUtils.isEmpty(configService.getMqttPassword()))
            options.setPassword(configService.getMqttPassword().toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);

        return options;
    }

}

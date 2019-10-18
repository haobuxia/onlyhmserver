package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.svc.sdk.basic.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * svc接口初始化配置
 * 在系统启动后执行
 *
 * <p>
 * Created by liuhanc on 2018/4/3.
 */
@Component
public class SvcConfigBean implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigService configService;

    private Logger logger = LoggerFactory.getLogger(SvcConfigBean.class);

    private boolean configInit = false;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (configInit)
            return;

        configInit = true;
        initSvcConfig();
    }

    public void initSvcConfig() {
        logger.debug("初始化田一服务日志接口配置.baseUrl=" + configService.getTySvcRestBaseUrl());
        Config config = Config.getInstance();
        config.setBaseUrl(configService.getTySvcRestBaseUrl());
        config.setAppKey(configService.getTySvcRestAppKey());
        config.setAppSecret(configService.getTySvcRestAppSecret());
    }
}

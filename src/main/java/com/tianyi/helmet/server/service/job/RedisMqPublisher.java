package com.tianyi.helmet.server.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *  mq 数据push发布
 *
 * Created by liuhanc on 2017/10/30.
 */
@Component
public class RedisMqPublisher {
    @Autowired
    private RedisTemplate redisTemplate;

    public void sendMessage(String channel, String dataId) {
        redisTemplate.convertAndSend(channel, dataId);
    }
}

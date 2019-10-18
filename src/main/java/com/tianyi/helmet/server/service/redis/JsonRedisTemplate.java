package com.tianyi.helmet.server.service.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * json格式的redis数据存储模板
 *
 * Created by liuhanc on 2017/11/2.
 */
public class JsonRedisTemplate extends RedisTemplate<String, Object> {

    public JsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        this.setHashKeySerializer(keySerializer);

        this.setKeySerializer(keySerializer);

        JsonRedisSerializer valueSerializer = new JsonRedisSerializer();
        this.setHashValueSerializer(valueSerializer);

        this.setValueSerializer(keySerializer);

        this.setConnectionFactory(redisConnectionFactory);
        this.afterPropertiesSet();
    }
}

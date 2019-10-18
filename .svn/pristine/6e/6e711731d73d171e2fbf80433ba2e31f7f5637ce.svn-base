package com.tianyi.helmet.server.service.redis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * json格式消息序列化反序列化工具
 *
 */
public class JsonRedisSerializer implements RedisSerializer<Object> {
    public JsonRedisSerializer() {
    }
    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
    public byte[] serialize(Object object) throws SerializationException {
        if(object != null) {
            try {
                return JSON.toJSONBytes(object, new SerializerFeature[]{SerializerFeature.WriteClassName});
            } catch (Exception var3) {
                throw new SerializationException("Fail to serialize object(" + object.getClass().getName() + ") to byte[]!", var3);
            }
        } else {
            return new byte[0];
        }
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        if(bytes != null && bytes.length > 0) {
            try {
                return JSON.parse(bytes, new Feature[0]);
            } catch (Exception var3) {
                throw new SerializationException("Fail to deserialize byte[] to object!", var3);
            }
        } else {
            return null;
        }
    }
}
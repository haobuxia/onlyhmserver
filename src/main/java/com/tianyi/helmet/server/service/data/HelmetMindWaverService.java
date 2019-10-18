package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.dao.data.HelmetMindWaverDao;
import com.tianyi.helmet.server.entity.data.HelmetMindWaver;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by wenxinyan on 2018/9/20.
 */
@Service
public class HelmetMindWaverService {
    @Autowired
    private HelmetMindWaverDao helmetMindWaverDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void insert(HelmetMindWaver helmetMindWaver) {
//        helmetMindWaverDao.insert(helmetMindWaver);
        redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_MIND_NEWEST_BY_IMEI + ":" + helmetMindWaver.getClientId(), JSON.toJSONString(helmetMindWaver));
    }


    public HelmetMindWaver getLatestMindWaver(String clientId) {
//        HelmetMindWaver mindWaver = helmetMindWaverDao.getLatestMindWaver(clientId); // 存储到数据库
        String mindWaverJson = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_MIND_NEWEST_BY_IMEI + ":" + clientId);
        if (!StringUtils.isEmpty(mindWaverJson) && !"NULL".equals(mindWaverJson)) {
            return JSON.parseObject(mindWaverJson, HelmetMindWaver.class);
        }
        if ("NULL".equals(mindWaverJson)) {
            return null;
        }
        return null;
    }
}

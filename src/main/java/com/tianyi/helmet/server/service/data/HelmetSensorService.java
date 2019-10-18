package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSON;
import com.sagittarius.bean.result.FloatPoint;
import com.sagittarius.bean.result.IntPoint;
import com.tianyi.helmet.server.entity.data.HelmetData;
import com.tianyi.helmet.server.entity.data.HelmetSensor;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.kmx.SensorSupport;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 头盔传感器数据服务
 * <p>
 * Created by liuhanc on 2017/10/17.
 */
@Service
public class HelmetSensorService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private SensorSupport sensorSupport;
    @Autowired
    private RedisTemplate jedisTemplate;

    private Logger logger = LoggerFactory.getLogger(HelmetSensorService.class);

    @PostConstruct
    public void initClientIdSet() {
        /**
         * update by xiayuan 2018/10/10
         */
        Set<String> set = equipmentService.getEffectHelmetIdSet();
        if(set.isEmpty()){//当没有头盔的时候
            return;
        }
        jedisTemplate.opsForSet().add(CacheKeyConstants.HELMET_CLIENTID_SET_ALL, set.toArray());
        logger.debug("初始化激活的头盔的数据缓存完毕...");
    }

    public void insert(HelmetSensor sensor) {
        kmxService.insertHelmetSensor(sensor);
        redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_SENSOR_NEWEST_BY_IMEI + ":" + sensor.getClientId(), JSON.toJSONString(sensor));
    }

    public HelmetSensor getLatest(final String clientId) {
        String gpsJson = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_SENSOR_NEWEST_BY_IMEI + ":" + clientId);
        if (!StringUtils.isEmpty(gpsJson) && !"NULL".equals(gpsJson)) {
            return JSON.parseObject(gpsJson, HelmetSensor.class);
        }
        if ("NULL".equals(gpsJson)) {
            return null;
        }

        final Map<String, IntPoint> intMap = kmxService.queryIntLatest(SensorSupport.HELMET_DEVICE_IDPREFIX + clientId, sensorSupport.toSensorIdList(sensorSupport.getHelmetIntSensorList()));
        final Map<String, FloatPoint> floatMap = kmxService.queryFloatLatest(SensorSupport.HELMET_DEVICE_IDPREFIX + clientId, sensorSupport.toSensorIdList(sensorSupport.getHelmetFloatSensorList()));
        if (!CollectionUtils.isEmpty(intMap) || !CollectionUtils.isEmpty(floatMap)) {
            final HelmetSensor sensor = new HelmetSensor();
            if (!CollectionUtils.isEmpty(intMap)) {
                Arrays.stream(sensorSupport.getHelmetIntSensorMetaDataArray()).forEach(
                        quadrupleVo -> {
                            quadrupleVo.getFour().accept(sensor, sensorSupport.getIntValue(intMap, quadrupleVo.getOne()));
                        }
                );
            }
            if (!CollectionUtils.isEmpty(floatMap)) {
                Arrays.stream(sensorSupport.getHelmetFloatSensorMetaDataArray()).forEach(
                        quadrupleVo -> {
                            quadrupleVo.getFour().accept(sensor, sensorSupport.getFloatValue(floatMap, quadrupleVo.getOne()));
                        }
                );
            }
            redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_SENSOR_NEWEST_BY_IMEI + ":" + clientId, JSON.toJSONString(sensor));
            return sensor;
        } else {
            redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_SENSOR_NEWEST_BY_IMEI + ":" + clientId, "NULL");
            return null;
        }
    }

    public List<HelmetSensor> selectByClientId(String clientId, long createTime1, long createTime2) {
        return kmxService.queryHelmetSensorList(clientId, createTime1, createTime2);
    }

    public boolean isEffectHelmetData(String clientId) {
//        return jedisTemplate.opsForSet().isMember(CacheKeyConstants.HELMET_CLIENTID_SET_ALL, clientId);
        //20190319头盔设备是否有效具体含义是什么不清楚，所以先注销掉
        return true;
    }

    public void addEffectHelmet(String clientId) {
        boolean exist = isEffectHelmetData(clientId);
        if (!exist) {
            jedisTemplate.opsForSet().add(CacheKeyConstants.HELMET_CLIENTID_SET_ALL, clientId);
        }
    }

    private static final LocalDateTime effectTime = LocalDateTime.of(2017, 10, 1, 0, 0, 0);

    public boolean isEffectHelmetData(HelmetData data) {
        return isEffectHelmetData(data.getClientId()) && data.getCreateTime().isAfter(effectTime);
    }
}

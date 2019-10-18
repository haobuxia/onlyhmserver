package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSON;
import com.sagittarius.bean.result.GeoPoint;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.kmx.KmxServiceSupport;
import com.tianyi.helmet.server.service.kmx.SensorSupport;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 头盔定位数据服务
 * <p>
 * Created by liuhanc on 2017/10/17.
 */
@Service
public class HelmetGpsService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private KmxServiceSupport kmxServiceSupport;
    @Autowired
    private SensorSupport sensorSupport;

    public void insert(HelmetGps gps) {
        kmxService.insertHelmetGps(gps);
        redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_GPS_NEWEST_BY_IMEI + ":" + gps.getClientId(), JSON.toJSONString(gps));
    }

    public HelmetGps getLatest(String clientId) {
        String gpsJson = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_GPS_NEWEST_BY_IMEI + ":" + clientId);
        if (!StringUtils.isEmpty(gpsJson) && !"NULL".equals(gpsJson)) {
            return JSON.parseObject(gpsJson, HelmetGps.class);
        }
        if ("NULL".equals(gpsJson)) {
            return null;
        }

        Map<String, GeoPoint> geoMap = kmxService.queryGeoLatest(SensorSupport.HELMET_DEVICE_IDPREFIX+clientId, sensorSupport.toSensorIdList(sensorSupport.getHelmetGeoSensorList()));
        HelmetGps latestGps = null;
        if (!CollectionUtils.isEmpty(geoMap)) {
            //常规来说有两条数据
            Comparator<HelmetGps> comparator = Comparator.comparing(HelmetGps::getCreateTime);
            latestGps = geoMap.keySet().stream().map(sensorId -> {
                GeoPoint gp = geoMap.get(sensorId);
                if (gp == null) return null;

                QuadrupleVo<String, String, Function<HelmetGps, Float[]>, BiConsumer<HelmetGps, Float[]>> geoMeta = sensorSupport.getHelmetGeoSensorMeta(sensorId);
                HelmetGps gps = kmxServiceSupport.createHelmetGpsInstance(clientId, sensorId, gp.getPrimaryTime(), geoMeta.getFour(), sensorSupport.getGeoValue(geoMap, geoMeta.getOne()));
                return gps;
            }).filter(gps -> gps != null)
                    .sorted(comparator).findFirst().orElse(null);
        }

        redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_GPS_NEWEST_BY_IMEI + ":" + clientId, (latestGps == null ? "NULL" : JSON.toJSONString(latestGps)));
        return latestGps;
    }

    public List<HelmetGps> selectByClientId(String clientId, long createTime1, long createTime2) {
        return kmxService.queryHelmetGpsList(clientId, createTime1, createTime2);
    }
}

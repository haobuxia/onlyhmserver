package com.tianyi.helmet.server.service.kmx;

import com.tianyi.helmet.server.entity.data.GpsData;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.entity.data.HelmetSensor;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * kmx服务支持类
 *
 * Created by liuhanc on 2018/1/8.
 */
@Component
public class KmxServiceSupport {
    @Autowired
    private SensorSupport sensorSupport;

    public <T extends Number> void initGpsDataSensorValue(String imei, long pointTime,String sensorId, BiConsumer<GpsData, Integer> consumer, Integer value, Map<Long, GpsData> sensorMap) {
        GpsData sensor = sensorMap.get(pointTime);
        if (sensor == null) {
            sensor = createGpsDataSensorInstance(imei, pointTime,sensorId, consumer, value);
            sensorMap.put(pointTime, sensor);
        } else {
            sensor.setDataType(GpsDataTypeItemEnum.valueOf(sensorId).getId());
            consumer.accept(sensor, value);
        }
    }

    public <T extends Number> void initHelmetSensorValue(long pointTime, String clientId, BiConsumer<HelmetSensor, T> consumer, T value, Map<Long, HelmetSensor> sensorMap) {
        HelmetSensor sensor = sensorMap.get(pointTime);
        if (sensor == null) {
            sensor = createHelmetSensorInstance(clientId, pointTime, consumer, value);
            sensorMap.put(pointTime, sensor);
        } else {
            consumer.accept(sensor, value);
        }
    }

    public void initHelmetGeoSensorValue(long pointTime, String clientId, String sensorId, BiConsumer<HelmetGps, Float[]> consumer, Float[] lonLatValues, Map<Long, HelmetGps> sensorMap) {
        HelmetGps sensor = sensorMap.get(pointTime);
        if (sensor == null) {
            sensor = createHelmetGpsInstance(clientId, sensorId, pointTime, consumer, lonLatValues);
            sensorMap.put(pointTime, sensor);
        } else {
            consumer.accept(sensor, lonLatValues);
        }
    }

    public GpsData createGpsDataSensorInstance(String imei, long pointTime,String sensorId, BiConsumer<GpsData, Integer> consumer, Integer value) {
        GpsData sensor = new GpsData();
        sensor.setCreateTime(Dates.toLocalDateTime(new Date(pointTime)));
        sensor.setDataType(GpsDataTypeItemEnum.valueOf(sensorId).getId());
        sensor.setImei(imei);
        consumer.accept(sensor, value);
        return sensor;
    }

    public <T extends Number> HelmetSensor createHelmetSensorInstance(String clientId, long pointTime, BiConsumer<HelmetSensor, T> consumer, T value) {
        HelmetSensor sensor = new HelmetSensor();
        sensor.setCreateTime(Dates.toLocalDateTime(new Date(pointTime)));
        sensor.setClientId(clientId);
        consumer.accept(sensor, value);
        return sensor;
    }

    public HelmetGps createHelmetGpsInstance(String clientId, String sensorId, long pointTime, BiConsumer<HelmetGps, Float[]> consumer, Float[] lonLatValues) {
        HelmetGps sensor = new HelmetGps();
        sensor.setCreateTime(Dates.toLocalDateTime(new Date(pointTime)));
        sensor.setClientId(clientId);
        sensor.setGpsType(sensorSupport.getHelmetGpsNetType(sensorId));//1 gps定位,2 网络定位
        consumer.accept(sensor, lonLatValues);
        return sensor;
    }
}

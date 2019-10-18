package com.tianyi.helmet.server.service.kmx;

import com.sagittarius.bean.common.TimePartition;
import com.sagittarius.bean.common.ValueType;
import com.sagittarius.bean.result.FloatPoint;
import com.sagittarius.bean.result.GeoPoint;
import com.sagittarius.bean.result.IntPoint;
import com.tianyi.helmet.server.entity.data.GpsData;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.entity.data.HelmetSensor;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.SgGpsDataTypeItemEnum;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tsinghua.thss.sdk.bean.common.Sensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * kmx传感器支持类
 *
 * Created by liuhanc on 2018/1/3.
 */
@Component
public class SensorSupport {

    @Autowired
    private DeviceTypeService deviceTypeService;

    public static final String HELMET_DEVICETYPE_ID = "HELMET_V1";//设备类型id定义
    public static final String TYBOX_DEVICETYPE_ID = "TYBOX";

    public static final String HELMET_DEVICE_IDPREFIX = "helmet_";//设备id前缀,防止不同类型的设备id相同了
    public static final String TYBOX_DEVICE_IDPREFIX = "tybox_";

    Function<HelmetSensor, Integer> helmet_sensor_getConcert = HelmetSensor::getConcert;
    BiConsumer<HelmetSensor, Integer> helmet_sensor_setConcert = HelmetSensor::setConcert;
    Function<HelmetSensor, Integer> helmet_sensor_getRelax = HelmetSensor::getRelax;
    BiConsumer<HelmetSensor, Integer> helmet_sensor_setRelax = HelmetSensor::setRelax;
    Function<HelmetSensor, Integer> helmet_sensor_getBattery = HelmetSensor::getBattery;
    BiConsumer<HelmetSensor, Integer> helmet_sensor_setBattery = HelmetSensor::setBattery;
    Function<HelmetSensor, Integer> helmet_sensor_getRuntime = HelmetSensor::getRuntime;
    BiConsumer<HelmetSensor, Integer> helmet_sensor_setRuntime = HelmetSensor::setRuntime;

    Function<HelmetSensor, Float> helmet_sensor_getXa = HelmetSensor::getXa;
    BiConsumer<HelmetSensor, Float> helmet_sensor_setXa = HelmetSensor::setXa;
    Function<HelmetSensor, Float> helmet_sensor_getYa = HelmetSensor::getYa;
    BiConsumer<HelmetSensor, Float> helmet_sensor_setYa = HelmetSensor::setYa;
    Function<HelmetSensor, Float> helmet_sensor_getZa = HelmetSensor::getZa;
    BiConsumer<HelmetSensor, Float> helmet_sensor_setZa = HelmetSensor::setZa;
    Function<HelmetSensor, Float> helmet_sensor_getXg = HelmetSensor::getXg;
    BiConsumer<HelmetSensor, Float> helmet_sensor_setXg = HelmetSensor::setXg;
    Function<HelmetSensor, Float> helmet_sensor_getYg = HelmetSensor::getYg;
    BiConsumer<HelmetSensor, Float> helmet_sensor_setYg = HelmetSensor::setYg;
    Function<HelmetSensor, Float> helmet_sensor_getZg = HelmetSensor::getZg;
    BiConsumer<HelmetSensor, Float> helmet_sensor_setZg = HelmetSensor::setZg;

    Function<HelmetGps, Float[]> helmet_sensor_getLonLat = HelmetGps::getLonLat;
    BiConsumer<HelmetGps, Float[]> helmet_sensor_setLonLat = HelmetGps::setLonLat;

    /**
     * 传感器int元数据定义
     */
    private final QuadrupleVo<String, String, Function<HelmetSensor, Integer>, BiConsumer<HelmetSensor, Integer>>[] helmetIntSensorMetaDataArray = new QuadrupleVo[]{
            new QuadrupleVo("concert", "专注度", helmet_sensor_getConcert, helmet_sensor_setConcert),
            new QuadrupleVo("relax", "放松度", helmet_sensor_getRelax, helmet_sensor_setRelax),
            new QuadrupleVo("battery", "电量", helmet_sensor_getBattery, helmet_sensor_setBattery),
            new QuadrupleVo("runtime", "运行时间", helmet_sensor_getRuntime, helmet_sensor_setRuntime)
    };

    /**
     * 传感器float元数据定义
     */
    private final QuadrupleVo<String, String, Function<HelmetSensor, Float>, BiConsumer<HelmetSensor, Float>>[] helmetFloatSensorMetaDataArray = new QuadrupleVo[]{
            new QuadrupleVo("xa", "角度x", helmet_sensor_getXa, helmet_sensor_setXa),
            new QuadrupleVo("ya", "角度y", helmet_sensor_getYa, helmet_sensor_setYa),
            new QuadrupleVo("za", "角度z", helmet_sensor_getZa, helmet_sensor_setZa),
            new QuadrupleVo("xg", "加速度x", helmet_sensor_getXg, helmet_sensor_setXg),
            new QuadrupleVo("yg", "加速度y", helmet_sensor_getYg, helmet_sensor_setYg),
            new QuadrupleVo("zg", "加速度z", helmet_sensor_getZg, helmet_sensor_setZg)
    };

    private List<Sensor> helmetIntSensorList;
    private List<Sensor> helmetFloatSensorList;
    private List<Sensor> helmetGeoSensorList;
    private List<Sensor> helmetSensorList;
    private List<Sensor> tyBoxSensorList;

    /**
     * 传感器gps元数据定义
     */
    private final QuadrupleVo<String, String, Function<HelmetGps, Float[]>, BiConsumer<HelmetGps, Float[]>>[] helmetGeoSensorMetaDataArray = new QuadrupleVo[]{
            new QuadrupleVo(HELMET_SENSOR_GPSNET, "网络定位", helmet_sensor_getLonLat, helmet_sensor_setLonLat),
            new QuadrupleVo(HELMET_SENSOR_GPSGPS, "gps定位", helmet_sensor_getLonLat, helmet_sensor_setLonLat)
    };


    /**
     * 头盔定位信息-网络
     */
    public static final String HELMET_SENSOR_GPSNET = "gps_net";
    /**
     * 头盔定位信息-gps
     */
    public static final String HELMET_SENSOR_GPSGPS = "gps_gps";
    /**
     * 头盔连接的天远盒子数据
     */
    public static final String HELMET_SENSOR_TYBOX = "tybox";

    /**
     * 1gps定位,2网络定位
     *
     * @param gpsSensorId
     * @return
     */
    public int getHelmetGpsNetType(String gpsSensorId) {
        if ("gps_net".equals(gpsSensorId)) {
            return 2;
        } else if ("gps_gps".equals(gpsSensorId)) {
            return 1;
        }
        return 0;
    }

    public QuadrupleVo<String, String, Function<HelmetSensor, Integer>, BiConsumer<HelmetSensor, Integer>>[] getHelmetIntSensorMetaDataArray() {
        return helmetIntSensorMetaDataArray;
    }

    public QuadrupleVo<String, String, Function<HelmetSensor, Float>, BiConsumer<HelmetSensor, Float>>[] getHelmetFloatSensorMetaDataArray() {
        return helmetFloatSensorMetaDataArray;
    }

    public QuadrupleVo<String, String, Function<HelmetGps, Float[]>, BiConsumer<HelmetGps, Float[]>>[] getHelmetGeoSensorMetaDataArray() {
        return helmetGeoSensorMetaDataArray;
    }

    public QuadrupleVo<String, String, Function<HelmetSensor, Integer>, BiConsumer<HelmetSensor, Integer>> getHelmetIntSensorMeta(String sensorId) {
        return Arrays.stream(helmetIntSensorMetaDataArray).filter(meta -> meta.getOne().equals(sensorId)).findAny().orElse(null);
    }

    public QuadrupleVo<String, String, Function<HelmetSensor, Float>, BiConsumer<HelmetSensor, Float>> getHelmetFloatSensorMeta(String sensorId) {
        return Arrays.stream(helmetFloatSensorMetaDataArray).filter(meta -> meta.getOne().equals(sensorId)).findAny().orElse(null);
    }

    public QuadrupleVo<String, String, Function<HelmetGps, Float[]>, BiConsumer<HelmetGps, Float[]>> getHelmetGeoSensorMeta(String sensorId) {
        return Arrays.stream(helmetGeoSensorMetaDataArray).filter(meta -> meta.getOne().equals(sensorId)).findAny().orElse(null);
    }

    public QuadrupleVo<String, String, Function<GpsData, Integer>, BiConsumer<GpsData, Integer>> getGpsDataSensorMeta(String sensorId) {
        GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.valueOf(sensorId);
        return new QuadrupleVo<String, String, Function<GpsData, Integer>, BiConsumer<GpsData, Integer>>(typeItemEnum.toString(), typeItemEnum.getName(), GpsData::getVal, GpsData::setVal);
    }

    public List<Sensor> getHelmetIntSensorList() {
        if (helmetIntSensorList == null) {
            helmetIntSensorList = Arrays.stream(this.helmetIntSensorMetaDataArray).map(vo -> {
                Sensor s = deviceTypeService.createSensor(vo.getOne(), TimePartition.DAY, ValueType.INT, vo.getTwo());
                return s;
            }).collect(Collectors.toList());
        }
        return helmetIntSensorList;
    }

    public List<String> toSensorIdList(List<Sensor> sensorList) {
        return sensorList.stream().map(Sensor::getId).collect(Collectors.toList());
    }

    public List<Sensor> getHelmetFloatSensorList() {
        if (helmetFloatSensorList == null) {
            helmetFloatSensorList = Arrays.stream(this.helmetFloatSensorMetaDataArray).map(vo -> {
                Sensor s = deviceTypeService.createSensor(vo.getOne(), TimePartition.DAY, ValueType.FLOAT, vo.getTwo());
                return s;
            }).collect(Collectors.toList());
        }
        return helmetFloatSensorList;
    }

    public List<Sensor> getHelmetGeoSensorList() {
        if (helmetGeoSensorList == null) {
            helmetGeoSensorList = Arrays.stream(this.helmetGeoSensorMetaDataArray).map(vo -> {
                Sensor s = deviceTypeService.createSensor(vo.getOne(), TimePartition.DAY, ValueType.GEO, vo.getTwo());
                return s;
            }).collect(Collectors.toList());
        }
        return helmetGeoSensorList;
    }

    /**
     * 头盔的所有传感器列表
     *
     * @return
     */
    public List<Sensor> getHelmetSensorList() {
        if (helmetSensorList == null) {
            helmetIntSensorList = getHelmetIntSensorList();
            helmetFloatSensorList = getHelmetFloatSensorList();
            helmetGeoSensorList = getHelmetGeoSensorList();

            helmetSensorList = new ArrayList<>();
            helmetSensorList.addAll(helmetIntSensorList);
            helmetSensorList.addAll(helmetFloatSensorList);
            helmetSensorList.addAll(helmetGeoSensorList);

            //记录1个对应盒子变化的传感器
            helmetSensorList.add(deviceTypeService.createSensor(HELMET_SENSOR_TYBOX, TimePartition.DAY, ValueType.STRING, "头盔连接的天远盒子信息"));
        }

        return helmetSensorList;
    }

    //天远蓝牙盒子传感器列表
    public List<Sensor> getTyBoxSensorList() {
        if (tyBoxSensorList == null) {
            List<Sensor> list1 = GpsDataTypeItemEnum.getSimpleDataTypeList().stream().map(gpsDataTypeItemEnum -> {
                return createTyBoxIntSensor(gpsDataTypeItemEnum);
            }).collect(Collectors.toList());
            List<Sensor> list2 = GpsDataTypeItemEnum.getComplexDataTypeList().stream().map(gpsDataTypeItemEnum -> {
                return createTyBoxStringSensor(gpsDataTypeItemEnum);
            }).collect(Collectors.toList());
            list1.addAll(list2);
            tyBoxSensorList = list1;
        }
        return tyBoxSensorList;
    }

    //天远蓝牙盒子传感器列表sg
    public List<Sensor> getSgTyBoxSensorList() {
        if (tyBoxSensorList == null) {
            List<Sensor> list1 = SgGpsDataTypeItemEnum.getSimpleDataTypeList().stream().map(gpsDataTypeItemEnum -> {
                return createSgTyBoxIntSensor(gpsDataTypeItemEnum);
            }).collect(Collectors.toList());
            tyBoxSensorList = list1;
        }
        return tyBoxSensorList;
    }

    public Sensor createTyBoxIntSensor(GpsDataTypeItemEnum typeItemEnum){
        Sensor s = deviceTypeService.createSensor(typeItemEnum.toString(), TimePartition.DAY, ValueType.INT, typeItemEnum.getName());
        return s;
    }

    public Sensor createSgTyBoxIntSensor(SgGpsDataTypeItemEnum typeItemEnum){
        Sensor s = deviceTypeService.createSensor(typeItemEnum.toString(), TimePartition.DAY, ValueType.INT, typeItemEnum.getName());
        return s;
    }

    public Sensor createTyBoxStringSensor(GpsDataTypeItemEnum typeItemEnum){
        Sensor s = deviceTypeService.createSensor(typeItemEnum.toString(), TimePartition.DAY, ValueType.STRING, typeItemEnum.getName());
        return s;
    }

    public int getIntValue(Map<String, IntPoint> map, String sensorId) {
        IntPoint ip = map.get(sensorId);
        if (ip == null) return 0;
        return ip.getValue();
    }

    public int getIntValue(Map<String, Map<String, IntPoint>> map, String deviceId, String sensorId) {
        Map<String, IntPoint> map1 = map.get(deviceId);
        return getIntValue(map1, sensorId);
    }

    public float getFloatValue(Map<String, FloatPoint> map, String sensorId) {
        FloatPoint ip = map.get(sensorId);
        if (ip == null) return 0f;
        return ip.getValue();
    }

    public float getFloatValue(Map<String, Map<String, FloatPoint>> map, String deviceId, String sensorId) {
        Map<String, FloatPoint> map1 = map.get(deviceId);
        return getFloatValue(map1, sensorId);
    }

    public Float[] getGeoValue(Map<String, GeoPoint> map, String sensorId) {
        GeoPoint ip = map.get(sensorId);
        if (ip == null) return new Float[]{0f, 0f};
        return new Float[]{ip.getLongitude(), ip.getLatitude()};
    }

    public Float[] getGeoValue(Map<String, Map<String, GeoPoint>> map, String deviceId, String sensorId) {
        Map<String, GeoPoint> map1 = map.get(deviceId);
        return getGeoValue(map1, sensorId);
    }

}

package com.tianyi.helmet.server.service.kmx;

import com.datastax.driver.core.ConsistencyLevel;
import com.sagittarius.bean.query.Shift;
import com.sagittarius.bean.result.FloatPoint;
import com.sagittarius.bean.result.GeoPoint;
import com.sagittarius.bean.result.IntPoint;
import com.sagittarius.bean.result.StringPoint;
import com.sagittarius.exceptions.UnregisteredHostMetricException;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.SgGpsDataTypeItemEnum;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tsinghua.thss.sdk.bean.common.Sensor;
import tsinghua.thss.sdk.core.Client;
import tsinghua.thss.sdk.core.KMXClient;
import tsinghua.thss.sdk.core.KMXConfig;
import tsinghua.thss.sdk.read.Reader;
import tsinghua.thss.sdk.write.Writer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.tianyi.helmet.server.service.kmx.SensorSupport.HELMET_DEVICE_IDPREFIX;
import static com.tianyi.helmet.server.service.kmx.SensorSupport.TYBOX_DEVICE_IDPREFIX;

/**
 * kmx读写服务
 *
 * Created by liuhanc on 2017/12/26.
 *
 * kmx杜绝删除操作
 * wenxinyan 2018-8-17 注释了deleteDeviceData
 */
@Service
public class KmxService {

    private Reader reader;
    private Writer writer;
    private Logger logger = LoggerFactory.getLogger(KmxService.class);

    @Autowired
    private ConfigService configService;
    @Autowired
    private SensorSupport sensorSupport;
    @Autowired
    private KmxServiceSupport kmxServiceSupport;

    /**
     * 系统启动时初始化
     */
    @PostConstruct
    public void init() {
        if (!isEnabled()) {
            logger.debug("kmx存储服务不启用");
            return;
        }

        String server = configService.getKmxServer();
        int port = configService.getKmxPort();
        logger.debug("kmx存储服务启用...server=" + server + ",port=" + port);
        String[] nodes = null;
        if (server.contains(",")) {
            nodes = server.split(",");
        } else {
            nodes = new String[]{server};
        }

        KMXConfig config = KMXConfig.builder()
                .setClusterNodes(nodes)
                .setClusterPort(port)
                .setCoreConnectionsPerHost(10)
                .setMaxConnectionsPerHost(100)
                .setMaxRequestsPerConnection(4096)
                .setHeartbeatIntervalSeconds(0)
                .setTimeoutMillis(300000)//300s
                .setConsistencyLevel(ConsistencyLevel.LOCAL_ONE)
                .setCacheSize(10000)
//                .setAutoBatch(true)//添加这个属性会导致数据查不出来
                .setBatchSize(3000)
                .setLingerMs(2)
                .build();
        Client client = new KMXClient(config);
        reader = client.getReader();
        writer = client.getWriter();
    }


    @PreDestroy
    public void destory() {
        if (configService.getKmxEnable() == 0)
            return;

        if (writer != null)
            writer.closeSender();
    }

    public boolean isEnabled() {
        return configService.getKmxEnable() == 1;
    }


    public Reader getReader() {
        return reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public boolean insertHelmetSensor(HelmetSensor sensor) {
        if (!isEnabled())
            return false;

        long now = System.currentTimeMillis();
        long sensorTime = Dates.toDate(sensor.getCreateTime()).getTime();
        String clientId = sensor.getClientId();

        Arrays.stream(sensorSupport.getHelmetIntSensorMetaDataArray()).forEach(vo -> {
            Integer integerVal = vo.getThree().apply(sensor);
            if (integerVal != null)
                insert(HELMET_DEVICE_IDPREFIX + clientId, vo.getOne(), sensorTime, now, integerVal);
        });

        Arrays.stream(sensorSupport.getHelmetFloatSensorMetaDataArray()).forEach(vo -> {
            Float floatVal = vo.getThree().apply(sensor);
            if (floatVal != null)
                insert(HELMET_DEVICE_IDPREFIX + clientId, vo.getOne(), sensorTime, now, floatVal);
        });
        return true;
    }

    public boolean insertHelmetGps(HelmetGps gps) {
        if (!isEnabled())
            return false;

        long now = System.currentTimeMillis();
        long sensorTime = Dates.toDate(gps.getCreateTime()).getTime();
        String clientId = gps.getClientId();

        Arrays.stream(sensorSupport.getHelmetGeoSensorMetaDataArray()).forEach(vo -> {
            insertGeo(HELMET_DEVICE_IDPREFIX + clientId, vo.getOne(), sensorTime, now, vo.getThree().apply(gps));
        });
        return true;
    }

    public boolean insertGpsData(GpsData gps) {
        if (!isEnabled())
            return false;

        GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.get(gps.getDataType());
        if (typeItemEnum != null) {
            long now = System.currentTimeMillis();
            long sensorTime = Dates.toDate(gps.getCreateTime()).getTime();
            this.insertHelmetTyBoxImei(gps.getClientId(), sensorTime, now, gps.getImei());
            return insert(TYBOX_DEVICE_IDPREFIX + gps.getImei(), typeItemEnum.toString(), sensorTime, now, gps.getVal());
        }
        return false;
    }

    public boolean insertShgtGpsData(GpsData gps) {
        if (!isEnabled())
            return false;

        SgGpsDataTypeItemEnum typeItemEnum = SgGpsDataTypeItemEnum.get(gps.getDataType());
        if (typeItemEnum != null) {
            long now = System.currentTimeMillis();
            long sensorTime = Dates.toDate(gps.getCreateTime()).getTime();
            this.insertHelmetTyBoxImei(gps.getClientId(), sensorTime, now, gps.getImei());
            return insert(TYBOX_DEVICE_IDPREFIX + gps.getImei(), typeItemEnum.toString(), sensorTime, now, gps.getVal());
        }
        return false;
    }

    public boolean insertGpsAction(GpsActionData ad) {
        if (!isEnabled())
            return false;

        long now = System.currentTimeMillis();
        long sensorTime = Dates.toDate(ad.getCreateTime()).getTime();

        this.insertHelmetTyBoxImei(ad.getClientId(), sensorTime, now, ad.getImei());
        return insert(TYBOX_DEVICE_IDPREFIX + ad.getImei(), GpsDataTypeItemEnum.ACTION.toString(), sensorTime, now, ad.dataJson());
    }

    public boolean insertGpsGyro(GpsGyroData ad) {
        if (!isEnabled())
            return false;
        long now = System.currentTimeMillis();
        long sensorTime = Dates.toDate(ad.getCreateTime()).getTime();
        this.insertHelmetTyBoxImei(ad.getClientId(), sensorTime, now, ad.getImei());
        return insert(TYBOX_DEVICE_IDPREFIX + ad.getImei(), GpsDataTypeItemEnum.GYRO.toString(), sensorTime, now, ad.dataJson());
    }

    public boolean insertGpsLocation(GpsLocationData ad) {
        if (!isEnabled())
            return false;
        long now = System.currentTimeMillis();
        long sensorTime = Dates.toDate(ad.getCreateTime()).getTime();
        this.insertHelmetTyBoxImei(ad.getClientId(), sensorTime, now, ad.getImei());
        return insert(TYBOX_DEVICE_IDPREFIX + ad.getImei(), GpsDataTypeItemEnum.GPS_LOCATION.toString(), sensorTime, now, ad.dataJson());
    }

    //保存某个头盔在某个时刻连接的天远盒子Imei数据
    public boolean insertHelmetTyBoxImei(String clientId, long time1, long time2, String imei) {
        if (!isEnabled())
            return false;
        return insert(HELMET_DEVICE_IDPREFIX + clientId, SensorSupport.HELMET_SENSOR_TYBOX, time1, time2, imei);
    }


    /**
     * 获得头盔在某个时刻连接的天远盒子imei号
     *
     * @param clientId
     * @param time
     * @return
     */
    public String getHelmetTyBoxImei(String clientId, long time) {
        if (!isEnabled())
            return null;

        StringPoint sp = null;
        try {
            sp = reader.getFuzzyStringPoint(HELMET_DEVICE_IDPREFIX + clientId, SensorSupport.HELMET_SENSOR_TYBOX, time, Shift.NEAREST);
        } catch (Exception e) {
            logger.error("getFuzzyStringPoint exception.clientId=" + clientId, e);
        }
        if (sp == null)
            return null;
        return sp.getValue();
    }

    /**
     * 获得头盔在某个时刻的最近的定位数据
     *
     * @param clientId
     * @param time
     * @return
     */
    public Float[] getHelmetGeo(String clientId, long time) {
        if (!isEnabled())
            return null;

        //按时间和参数时间哪个最接近排序
        Comparator<GeoPoint> compartor = Comparator.comparing(geoPoint -> {
            return geoPoint.getPrimaryTime() - time;
        });

        GeoPoint geoPoint = sensorSupport.toSensorIdList(sensorSupport.getHelmetGeoSensorList()).stream().map(sensorId -> {
            GeoPoint sp = null;
            try {
                sp = reader.getFuzzyGeoPoint(HELMET_DEVICE_IDPREFIX + clientId, sensorId, time, Shift.NEAREST);
                return sp;
            } catch (Exception e) {
                logger.error("getFuzzyGeoPoint exception.clientId=" + clientId + e.getMessage());
            }
            return null;
        }).filter(sp -> sp != null).max(compartor).orElse(null);

        if (geoPoint != null)
            return new Float[]{geoPoint.getLongitude(), geoPoint.getLatitude()};
        return null;
    }

    /**
     * 查询某个时间段的某个头盔的传感器数据
     *
     * @param clientId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<HelmetSensor> queryHelmetSensorList(String clientId, long startTime, long endTime) {
        if (!isEnabled())
            return Collections.emptyList();

        Map<String, List<IntPoint>> intMap = queryIntRange(HELMET_DEVICE_IDPREFIX + clientId, sensorSupport.toSensorIdList(sensorSupport.getHelmetIntSensorList()), startTime, endTime);
        Map<String, List<FloatPoint>> floatMap = queryFloatRange(HELMET_DEVICE_IDPREFIX + clientId, sensorSupport.toSensorIdList(sensorSupport.getHelmetFloatSensorList()), startTime, endTime);
        if (CollectionUtils.isEmpty(intMap) && CollectionUtils.isEmpty(floatMap))
            return Collections.emptyList();

        Map<Long, HelmetSensor> sensorMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(intMap)) {
            for (String sensorId : intMap.keySet()) {
                List<IntPoint> points = intMap.get(sensorId);
                if (CollectionUtils.isEmpty(points)) {
                    continue;
                }

                QuadrupleVo<String, String, Function<HelmetSensor, Integer>, BiConsumer<HelmetSensor, Integer>> intMeta = sensorSupport.getHelmetIntSensorMeta(sensorId);
                if (intMeta != null) {
                    points.stream().forEach(ip -> {
                        kmxServiceSupport.initHelmetSensorValue(ip.getPrimaryTime(), clientId, intMeta.getFour(), ip.getValue(), sensorMap);
                    });
                } else {
                    logger.error("不存在的头盔传感器." + sensorId);
                }
            }
        }
        if (!CollectionUtils.isEmpty(floatMap)) {
            for (String sensorId : floatMap.keySet()) {
                List<FloatPoint> points = floatMap.get(sensorId);
                if (CollectionUtils.isEmpty(points)) {
                    continue;
                }

                QuadrupleVo<String, String, Function<HelmetSensor, Float>, BiConsumer<HelmetSensor, Float>> floatMeta = sensorSupport.getHelmetFloatSensorMeta(sensorId);
                if (floatMeta != null) {
                    points.stream().forEach(ip -> {
                        kmxServiceSupport.initHelmetSensorValue(ip.getPrimaryTime(), clientId, floatMeta.getFour(), ip.getValue(), sensorMap);
                    });
                } else {
                    logger.error("不存在的头盔传感器." + sensorId);
                }

            }
        }

        Comparator<HelmetSensor> comparator = Comparator.comparing(HelmetSensor::getCreateTime);
        return sensorMap.values().stream().sorted(comparator).collect(Collectors.toList());
    }

    /**
     * 查询某个时间段的某个头盔的gps传感器数据
     *
     * @param clientId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<HelmetGps> queryHelmetGpsList(String clientId, long startTime, long endTime) {
        if (!isEnabled())
            return Collections.emptyList();

        List<Sensor> geoSensors = sensorSupport.getHelmetGeoSensorList();
        Map<String, List<GeoPoint>> geoMap = queryGeoRange(HELMET_DEVICE_IDPREFIX + clientId, sensorSupport.toSensorIdList(geoSensors), startTime, endTime);
        if (CollectionUtils.isEmpty(geoMap))
            return Collections.emptyList();

        Map<Long, HelmetGps> sensorMap = new HashMap<>();
        for (String sensorId : geoMap.keySet()) {
            List<GeoPoint> points = geoMap.get(sensorId);
            if (CollectionUtils.isEmpty(points)) {
                continue;
            }

            QuadrupleVo<String, String, Function<HelmetGps, Float[]>, BiConsumer<HelmetGps, Float[]>> geoMeta = sensorSupport.getHelmetGeoSensorMeta(sensorId);
            if (geoMeta != null) {
                points.stream().forEach(geoPoint -> {
                    kmxServiceSupport.initHelmetGeoSensorValue(geoPoint.getPrimaryTime(), clientId, sensorId, geoMeta.getFour(), new Float[]{geoPoint.getLongitude(), geoPoint.getLatitude()}, sensorMap);
                });
            } else {
                logger.error("不存在的头盔传感器." + sensorId);
            }

        }
        Comparator<HelmetGps> comparator = Comparator.comparing(HelmetGps::getCreateTime);
        return sensorMap.values().stream().sorted(comparator).collect(Collectors.toList());
    }

    /**
     * 查询车辆传感器数值型数据
     *
     * @return
     */
    public Map<String, Map<Long, Integer>> queryGpsDataMap(String imei, long startTime, long endTime, List<GpsDataTypeItemEnum> keyDataTypeList) {
        if (!isEnabled())
            return Collections.emptyMap();

        List<String> sensorIdList = keyDataTypeList.stream().map(gpsDataTypeItemEnum -> gpsDataTypeItemEnum.toString()).collect(Collectors.toList());
        Map<String, List<IntPoint>> intMap = queryIntRange(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei, sensorIdList, startTime, endTime);
        if (CollectionUtils.isEmpty(intMap))
            return Collections.emptyMap();

        Map<String, Map<Long, Integer>> resultMap = new HashMap<>(intMap.size());
        for (String sensorId : intMap.keySet()) {
            List<IntPoint> points = intMap.get(sensorId);
            if (CollectionUtils.isEmpty(points)) {
                continue;
            }
            Map<Long, Integer> timeValueMap = points.stream().collect(Collectors.toMap(intPoint -> intPoint.getPrimaryTime(), intPoint -> intPoint.getValue()));
            resultMap.put(sensorId, timeValueMap);
        }
        return resultMap;
    }
    /**
     * 查询车辆传感器数值型数据
     *
     * @return
     */
    public Map<String, Map<Long, String>> queryGpsDataMap1(String imei, long startTime, long endTime, List<GpsDataTypeItemEnum> keyDataTypeList) {
        if (!isEnabled())
            return Collections.emptyMap();

        List<String> sensorIdList = keyDataTypeList.stream().map(gpsDataTypeItemEnum -> gpsDataTypeItemEnum.toString()).collect(Collectors.toList());
        Map<String,List<StringPoint>> stringMap = queryStringRange(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei, sensorIdList, startTime, endTime);

        Map<String, Map<Long, String>> resultMap = new HashMap<>(stringMap.size());
        for (String sensorId : stringMap.keySet()) {
            List<StringPoint> points = stringMap.get(sensorId);
            if (CollectionUtils.isEmpty(points)) {
                continue;
            }
            Map<Long, String> timeValueMap = points.stream().collect(Collectors.toMap(stringPoint -> stringPoint.getPrimaryTime(), stringPoint -> stringPoint.getValue()));
            resultMap.put(sensorId, timeValueMap);
        }
        return resultMap;
    }
    /**
     * 查询车辆传感器数值型数据
     *
     * @return
     */
    public Map<String, Map<Long, Integer>> queryGpsDataMapSg(String imei, long startTime, long endTime, List<SgGpsDataTypeItemEnum> keyDataTypeList) {
        if (!isEnabled())
            return Collections.emptyMap();

        List<String> sensorIdList = keyDataTypeList.stream().map(gpsDataTypeItemEnum -> gpsDataTypeItemEnum.toString()).collect(Collectors.toList());
        Map<String, List<IntPoint>> intMap = queryIntRange(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei, sensorIdList, startTime, endTime);
        if (CollectionUtils.isEmpty(intMap))
            return Collections.emptyMap();

        Map<String, Map<Long, Integer>> resultMap = new HashMap<>(intMap.size());
        for (String sensorId : intMap.keySet()) {
            List<IntPoint> points = intMap.get(sensorId);
            if (CollectionUtils.isEmpty(points)) {
                continue;
            }
            Map<Long, Integer> timeValueMap = points.stream().collect(Collectors.toMap(intPoint -> intPoint.getPrimaryTime(), intPoint -> intPoint.getValue()));
            resultMap.put(sensorId, timeValueMap);
        }
        return resultMap;
    }
    public Map<Long, GpsActionData> queryGpsActionDataMap(String imei, long startTime, long endTime) {
        Map<Long, String> map = queryGpsStringDataMap(imei, startTime, endTime, GpsDataTypeItemEnum.ACTION.toString());
        Map<Long, GpsActionData> timeMap = map.keySet().stream().collect(Collectors.toMap(time -> time, time -> {
            String json = map.get(time);
            GpsActionData actionData = GpsActionData.jsonToData(json);
            return actionData;
        }));
        return timeMap;
    }

    public Map<Long, GpsGyroData> queryGpsGyroDataMap(String imei, long startTime, long endTime) {
        Map<Long, String> map = queryGpsStringDataMap(imei, startTime, endTime, GpsDataTypeItemEnum.GYRO.toString());
        Map<Long, GpsGyroData> timeMap = map.keySet().stream().collect(Collectors.toMap(time -> time, time -> {
            String json = map.get(time);
            GpsGyroData actionData = GpsGyroData.jsonToData(json);
            return actionData;
        }));
        return timeMap;
    }

    /**
     * 查询车辆传感器字符型数据，如工作模式、时间等
     *
     * @return
     */
    public Map<Long, Integer> queryGpsIntDataMap(String imei, long startTime, long endTime, String sensorId) {
        if (!isEnabled())
            return Collections.emptyMap();

        List<String> sensorIdList = Arrays.asList(sensorId);
        Map<String, List<IntPoint>> strMap = queryIntRange(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei, sensorIdList, startTime, endTime);
        if (CollectionUtils.isEmpty(strMap))
            return Collections.emptyMap();
        List<IntPoint> points = strMap.get(sensorId);
        if (CollectionUtils.isEmpty(points)) {
            return Collections.emptyMap();
        }

        Map<Long, Integer> timeValueMap = points.stream().collect(Collectors.toMap(intPoint -> intPoint.getPrimaryTime(), intPoint -> intPoint.getValue()));
        return timeValueMap;
    }

    /**
     * 查询车辆传感器字符型数据，如动作、定位、陀螺仪
     *
     * @return
     */
    public Map<Long, String> queryGpsStringDataMap(String imei, long startTime, long endTime, String sensorId) {
        if (!isEnabled())
            return Collections.emptyMap();

        List<String> sensorIdList = Arrays.asList(sensorId);
        Map<String, List<StringPoint>> strMap = queryStringRange(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei, sensorIdList, startTime, endTime);
        if (CollectionUtils.isEmpty(strMap))
            return Collections.emptyMap();
        List<StringPoint> points = strMap.get(sensorId);
        if (CollectionUtils.isEmpty(points)) {
            return Collections.emptyMap();
        }

        Map<Long, String> timeValueMap = points.stream().collect(Collectors.toMap(strPoint -> strPoint.getPrimaryTime(), strPoint -> strPoint.getValue()));
        return timeValueMap;
    }

    /**
     * 获得某个传感器几个时间点上的对应传感器的数据值
     *
     * @param imei
     * @param timeList
     * @return
     */
    public Map<Integer, List<Integer>> queryGpsIntDataByTimeList(String imei, List<Long> timeList) {
        if (!isEnabled())
            return null;

        String deviceId = SensorSupport.TYBOX_DEVICE_IDPREFIX + imei;
        Map<Integer, List<Integer>> typeValListMap = GpsDataTypeItemEnum.getCompareDataTypeList().stream()
                .map(typeItemEnum -> {
                    String sensorId = typeItemEnum.toString();//传感器
                    List<Integer> valList = timeList.stream().map(longTime -> {
                        return getFuzzyIntValue(deviceId, sensorId, longTime);
                    }).collect(Collectors.toList());
                    return new DoubleVo<>(typeItemEnum.getId(), valList);
                }).collect(Collectors.toMap(doubleVo -> doubleVo.getKey(), doubleVo -> doubleVo.getVal()));
        return typeValListMap;
    }

    /**
     * 获得某个时刻的传感器的模糊值
     *
     * @param deviceId
     * @param sensorId
     * @param longTime
     * @return
     */
    public int getFuzzyIntValue(String deviceId, String sensorId, long longTime) {
        try {
            IntPoint ip = reader.getFuzzyIntPoint(deviceId, sensorId, longTime, Shift.BEFORE);
            int val = ip.getValue();
            return Integer.valueOf(val);
        } catch (Exception e) {
            logger.error("kmx获取模糊值异常.deviceId=" + deviceId + ",sensorId=" + sensorId + ",longtime=" + longTime, e);
            return new Integer(0);
        }
    }

    public boolean insert(String deviceId, String sensorId, long primaryTime, long secondaryTime, String value) {
        if (!isEnabled()) {
            return false;
        }
//        logger.debug("insert String."+deviceId+","+sensorId+","+primaryTime+","+secondaryTime+","+value);
        try {
            writer.insert(deviceId, sensorId, primaryTime, secondaryTime, value);
            return true;
        } catch (UnregisteredHostMetricException e) {
            logger.error("时序数据入库string异常." + e.getMessage());
        } catch (Exception e) {
            logger.error("时序数据入库string异常", e);
        }
        return false;
    }

    public boolean insert(String deviceId, String sensorId, long primaryTime, long secondaryTime, int value) {
        if (!isEnabled()) {
            return false;
        }
//        logger.debug("insert int."+deviceId+","+sensorId+","+primaryTime+","+secondaryTime+","+value);
        try {
            writer.insert(deviceId, sensorId, primaryTime, secondaryTime, value);
            return true;
        } catch (UnregisteredHostMetricException e) {
            logger.error("时序数据入库string异常." + e.getMessage());
        } catch (Exception e) {
            logger.error("时序数据入库int异常", e);
        }
        return false;
    }

    public boolean insert(String deviceId, String sensorId, long primaryTime, long secondaryTime, float value) {
        if (!isEnabled()) {
            return false;
        }
//        logger.debug("insert float."+deviceId+","+sensorId+","+primaryTime+","+secondaryTime+","+value);
        try {
            writer.insert(deviceId, sensorId, primaryTime, secondaryTime, value);
            return true;
        } catch (UnregisteredHostMetricException e) {
            logger.error("时序数据入库float异常." + e.getMessage());
        } catch (Exception e) {
            logger.error("时序数据入库float异常", e);
        }
        return false;
    }

    public boolean insertGeo(String deviceId, String sensorId, long primaryTime, long secondaryTime, Float[] lonLat) {
        if (!isEnabled()) {
            return false;
        }
//        logger.debug("insert geo."+deviceId+","+sensorId+","+primaryTime+","+secondaryTime+","+lonLat[0]+":"+lonLat[1]);
        try {
            writer.insert(deviceId, sensorId, primaryTime, secondaryTime, lonLat[1], lonLat[0]);
            return true;
        } catch (UnregisteredHostMetricException e) {
            logger.error("时序数据入库geo异常." + e.getMessage());
        } catch (Exception e) {
            logger.error("时序数据入库geo异常", e);
        }
        return false;
    }

    public StringPoint queryStringLatest(String deviceId, String sensorId) {
        if (!isEnabled())
            return null;
        Map<String, Map<String, StringPoint>> map = null;
        try {
            map = reader.getStringLatest(Arrays.asList(deviceId), Arrays.asList(sensorId));
        } catch (Exception e) {
            logger.error("queryStringLatest exception.deviceId=" + deviceId, e);
        }

        if (map != null && map.containsKey(deviceId)) {
            Map<String, StringPoint> map1 = map.get(deviceId);
            if (map1 != null && map1.containsKey(sensorId)) {
                return map1.get(sensorId);
            }
        }
        return null;
    }

    public Map<String, StringPoint> queryStringLatest(String deviceId, List<String> sensorIdList) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, StringPoint>> map = reader.getStringLatest(Arrays.asList(deviceId), sensorIdList);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryStringLatest exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public Map<String, IntPoint> queryIntLatest(String deviceId, List<String> sensorIdList) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, IntPoint>> map = reader.getIntLatest(Arrays.asList(deviceId), sensorIdList);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryIntLatest exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public Map<String, FloatPoint> queryFloatLatest(String deviceId, List<String> sensorIdList) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, FloatPoint>> map = reader.getFloatLatest(Arrays.asList(deviceId), sensorIdList);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryFloatLatest exception.deviceId=" + deviceId, e);
        }
        return null;
    }


    public Map<String, GeoPoint> queryGeoLatest(String deviceId, List<String> sensorIdList) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, GeoPoint>> map = reader.getGeoLatest(Arrays.asList(deviceId), sensorIdList);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryGeoLatest exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public List<StringPoint> queryStringRange(String deviceId, String sensorId, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<StringPoint>>> map = reader.getStringRange(Arrays.asList(deviceId), Arrays.asList(sensorId), startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                Map<String, List<StringPoint>> map2 = map.get(deviceId);
                if (map2 != null && map2.containsKey(sensorId)) {
                    return map2.get(sensorId);
                }
            }
        } catch (Exception e) {
            logger.error("queryStringRange exception.deviceId=" + deviceId + ",sensorId=" + sensorId, e);
        }
        return null;
    }

    public Map<String, List<StringPoint>> queryStringRange(String deviceId, List<String> sensorIdList, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<StringPoint>>> map = reader.getStringRange(Arrays.asList(deviceId), sensorIdList, startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryStringRange exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public List<IntPoint> queryIntRange(String deviceId, String sensorId, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<IntPoint>>> map = reader.getIntRange(Arrays.asList(deviceId), Arrays.asList(sensorId), startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                Map<String, List<IntPoint>> map2 = map.get(deviceId);
                if (map2 != null && map2.containsKey(sensorId)) {
                    return map2.get(sensorId);
                }
            }
        } catch (Exception e) {
            logger.error("getIntRange exception.deviceId=" + deviceId + ",sensorId=" + sensorId, e);
        }
        return null;
    }

    public Map<String, List<IntPoint>> queryIntRange(String deviceId, List<String> sensorIdList, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<IntPoint>>> map = reader.getIntRange(Arrays.asList(deviceId), sensorIdList, startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("getIntRange exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public List<FloatPoint> queryFloatRange(String deviceId, String sensorId, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<FloatPoint>>> map = reader.getFloatRange(Arrays.asList(deviceId), Arrays.asList(sensorId), startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                Map<String, List<FloatPoint>> map2 = map.get(deviceId);
                if (map2 != null && map2.containsKey(sensorId)) {
                    return map2.get(sensorId);
                }
            }
        } catch (Exception e) {
            logger.error("queryFloatRange exception.deviceId=" + deviceId + ",sensorId=" + sensorId, e);
        }
        return null;
    }

    public Map<String, List<FloatPoint>> queryFloatRange(String deviceId, List<String> sensorIdList, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<FloatPoint>>> map = reader.getFloatRange(Arrays.asList(deviceId), sensorIdList, startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryFloatRange exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public List<GeoPoint> queryGeoRange(String deviceId, String sensorId, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<GeoPoint>>> map = reader.getGeoRange(Arrays.asList(deviceId), Arrays.asList(sensorId), startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                Map<String, List<GeoPoint>> map2 = map.get(deviceId);
                if (map2 != null && map2.containsKey(sensorId)) {
                    return map2.get(sensorId);
                }
            }
        } catch (Exception e) {
            logger.error("queryGeoRange exception.deviceId=" + deviceId + ",sensorId=" + sensorId, e);
        }
        return null;
    }

    public Map<String, List<GeoPoint>> queryGeoRange(String deviceId, List<String> sensorIdList, long startTime, long endTime) {
        if (!isEnabled())
            return null;

        try {
            Map<String, Map<String, List<GeoPoint>>> map = reader.getGeoRange(Arrays.asList(deviceId), sensorIdList, startTime, endTime, false);
            if (map != null && map.containsKey(deviceId)) {
                return map.get(deviceId);
            }
        } catch (Exception e) {
            logger.error("queryGeoRange exception.deviceId=" + deviceId, e);
        }
        return null;
    }

//    public void deleteDeviceData(String deviceId, List<String> sensorIdList) {
//        try {
//            writer.deleteRange(Arrays.asList(deviceId), sensorIdList, 0, System.currentTimeMillis());
//        } catch (Exception e) {
//            logger.error("删除设备数据异常.deviceId=" + deviceId, e);
//        }
//    }

}

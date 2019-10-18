package com.tianyi.helmet.server.service.kmx;

import com.datastax.driver.core.ConsistencyLevel;
import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.sagittarius.bean.common.ValueType;
import com.sagittarius.bean.result.DoublePoint;
import com.sagittarius.bean.result.IntPoint;
import com.sagittarius.bean.result.StringPoint;
import com.sagittarius.exceptions.NoHostAvailableException;
import com.sagittarius.exceptions.QueryExecutionException;
import com.sagittarius.exceptions.TimeoutException;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsinghua.thss.sdk.core.Client;
import tsinghua.thss.sdk.core.KMXClient;
import tsinghua.thss.sdk.core.KMXConfig;
import tsinghua.thss.sdk.read.Reader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 清华的kmx读写服务
 * <p>
 * Created by liuhanc on 2017/12/26.
 *
 * wenxinyan 2018-8-16 修改了getRange()
 */
@Service
public class TsingHuaKmxService {

    private Reader reader;
    private Logger logger = LoggerFactory.getLogger(TsingHuaKmxService.class);
    private Ordering<PointWithVclid> timeOrder = Ordering.natural().onResultOf(new Function<PointWithVclid, Long>() {
        @Override
        public Long apply(PointWithVclid pointWithVclid) {
            return pointWithVclid.getPrimaryTime();
        }
    });

    @Autowired
    private ConfigService configService;

    /**
     * 系统启动时初始化
     */
    @PostConstruct
    public void init() {
        if (!isEnabled()) {
            logger.debug("清华kmx存储服务不启用");
            return;
        }

        String server = configService.getTsinghuaKmxServer();
        int port = configService.getTsinghuaKmxPort();
        logger.debug("清华kmx存储服务启用...server=" + server + ",port=" + port);
        String[] nodes = null;
        if (server.contains(",")) {
            nodes = server.split(",");
        } else {
            nodes = new String[]{server};
        }

        KMXConfig config = KMXConfig.builder()
                .setClusterNodes(nodes)
                .setClusterPort(port)
                .setCoreConnectionsPerHost(3)
                .setMaxConnectionsPerHost(10)
                .setMaxRequestsPerConnection(4096)
                .setHeartbeatIntervalSeconds(0)
                .setTimeoutMillis(12000)
                .setConsistencyLevel(ConsistencyLevel.TWO)
                .setCacheSize(10000)
                .setAutoBatch(false)
                .build();
        Client client = new KMXClient(config);
        reader = client.getReader();
    }


    @PreDestroy
    public void destory() {
        if (configService.getKmxEnable() == 0)
            return;
    }

    public boolean isEnabled() {
        return configService.getTsinghuaLmxEnable() == 1;
    }


    public Reader getReader() {
        return reader;
    }

    //某个设备的传感器数据
    //修改传感器参数的数据类型为double、int和String
    //2018-08-16 wxy
    public Map<String, List<PointWithVclid>> getRange(String deviceId, Long startTime, Long endTime) throws ParseException, NoHostAvailableException, QueryExecutionException, TimeoutException {
        String[] deviceArray = new String[]{deviceId};
        String[] doubleSensorArray = new String[]{
                "KM_0002_02_03", "KM_0002_02_04", "KM_0002_02_05", "KM_0002_02_06", "KM_0002_02_07",
                "KM_0002_02_08", "KM_0002_02_09", "KM_0002_02_10"
        };
        String[] intSensorArray = new String[]{
                "KM_0002_02_11"
        };
        String[] strSensorArray = new String[]{
                "KM_0002_02_12", "KM_0002_02_13", "TC_0002_02_17"
        };

        Map<String, Map<String, List<DoublePoint>>> doubleMap = getReader().getDoubleRange(Arrays.asList(deviceArray), Arrays.asList(doubleSensorArray), startTime, endTime, false);
//        Map<String, Map<String, List<IntPoint>>> intMap = getReader().getIntRange(Arrays.asList(deviceArray), Arrays.asList(doubleSensorArray), startTime, endTime, false);
//        Map<String, Map<String, List<StringPoint>>> strMap = getReader().getStringRange(Arrays.asList(deviceArray), Arrays.asList(doubleSensorArray), startTime, endTime, false);
        Map<String, Map<String, List<IntPoint>>> intMap = getReader().getIntRange(Arrays.asList(deviceArray), Arrays.asList(intSensorArray), startTime, endTime, false);
        Map<String, Map<String, List<StringPoint>>> strMap = getReader().getStringRange(Arrays.asList(deviceArray), Arrays.asList(strSensorArray), startTime, endTime, false);
        Map<String, List<PointWithVclid>> resultMap = new HashMap();

        if (doubleMap != null && doubleMap.containsKey(deviceId)) {
            Map<String, List<DoublePoint>> doubleMap2 = doubleMap.get(deviceId);
            Map<String, List<PointWithVclid>> doubleMap3 = doubleMap2.keySet().stream().collect(Collectors.toMap(sensorId -> sensorId, sensorId -> {
                List<DoublePoint> doublePoints = doubleMap2.get(sensorId);
                return doublePoints.stream().map(doublePoint -> {
                    return new PointWithVclid(doublePoint, deviceId, ValueType.DOUBLE, String.valueOf(doublePoint.getValue()));
                }).collect(Collectors.toList());
            }));
            resultMap.putAll(doubleMap3);
        }

        if (intMap != null && intMap.containsKey(deviceId)) {
            Map<String, List<IntPoint>> intMap2 = intMap.get(deviceId);
            Map<String, List<PointWithVclid>> intMap3 = intMap2.keySet().stream().collect(Collectors.toMap(sensorId -> sensorId, sensorId -> {
                List<IntPoint> intPoints = intMap2.get(sensorId);
                return intPoints.stream().map(intPoint -> {
                    return new PointWithVclid(intPoint, deviceId, ValueType.INT, String.valueOf(intPoint.getValue()));
                }).collect(Collectors.toList());
            }));
            resultMap.putAll(intMap3);
        }

        if (strMap != null && strMap.containsKey(deviceId)) {
            Map<String, List<StringPoint>> strMap2 = strMap.get(deviceId);
            Map<String, List<PointWithVclid>> strMap3 = strMap2.keySet().stream().collect(Collectors.toMap(sensorId -> sensorId, sensorId -> {
                List<StringPoint> strPoints = strMap2.get(sensorId);
                return strPoints.stream().map(strPoint -> {
                    return new PointWithVclid(strPoint, deviceId, ValueType.STRING, strPoint.getValue());
                }).collect(Collectors.toList());
            }));
            resultMap.putAll(strMap3);
        }

        return resultMap;
    }

}

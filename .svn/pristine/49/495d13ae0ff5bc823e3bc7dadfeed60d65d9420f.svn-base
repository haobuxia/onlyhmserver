package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.service.data.GpsDataService;
import com.tianyi.helmet.server.service.data.HelmetSensorService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 历史数据从mysql导入到kmx,随系统启动
 *
 * <p>
 * Created by liuhanc on 2018/1/8.
 */
@Service
public class KmxDataSyncJob extends AbstractContextJob {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ConfigService configService;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private GpsDataService gpsDataService;
    @Autowired
    private HelmetSensorService helmetSensorService;

    @Value("${kmx.sync.helmet.sensor:0}")
    private int syncHelmetSensor;
    @Value("${kmx.sync.helmet.gps:0}")
    private int syncHelmetGps;
    @Value("${kmx.sync.gps.data:0}")
    private int syncGpsData;
    @Value("${kmx.sync.gps.comples:0}")
    private int syncGpsComples;


    private Logger logger = LoggerFactory.getLogger(KmxDataSyncJob.class);

    @Override
    public boolean thisJobStart() {
        return configService.getKmxSyncJobStart() == 1;
    }

    /**
     * 开始任务
     */
    @Override
    public void startJob() {
        logger.debug("新线程启动历史数据同步到kmx数据库任务.");
        Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                doSync();
            }
        });
    }

    /**
     * 同步步骤
     * 1、头盔传感器
     * 2、头盔gps
     * 3、车辆传感器-int
     * 4、车辆动作
     * 5、车辆定位
     * 6、车辆陀螺仪
     */
    public void doSync() {
        logger.debug("执行时序数据同步到kmx的工作...");

        if(syncHelmetSensor == 1){
            try {
                Function<HelmetSensor, Boolean> consumer = kmxService::insertHelmetSensor;
                syncOneTable("t_helmetsensor",null, this::createHelmetSensor, helmetSensorService::isEffectHelmetData, consumer);
            } catch (Exception e) {
                logger.error("同步HelmetSensor异常", e);
            }
        }else{
            logger.debug("不同步 t_helmetsensor");
        }

        if(syncHelmetGps == 1){
            try {
                Function<HelmetGps, Boolean> consumer = kmxService::insertHelmetGps;
                syncOneTable("t_helmetgps",null, this::createHelmetGps, helmetSensorService::isEffectHelmetData, consumer);
            } catch (Exception e) {
                logger.error("同步HelmetGps异常", e);
            }
        }else{
            logger.debug("不同步 t_helmetgps");
        }

        if(syncGpsData == 1){
            try {
                Function<GpsData, Boolean> consumer = kmxService::insertGpsData;
                syncOneTable("t_gpsdata"," createTime >= '2017-12-01 10:00:00' ", this::createGpsData, gpsDataService::isEffectGpsData, consumer);
            } catch (Exception e) {
                logger.error("同步GpsData异常", e);
            }
        }else{
            logger.debug("不同步 t_gpsdata");
        }

        if(syncGpsComples == 1){
            try {
                Function<GpsActionData, Boolean> consumer = kmxService::insertGpsAction;
                syncOneTable("t_gpsaction",null, this::createGpsActionData, gpsDataService::isEffectGpsData, consumer);
            } catch (Exception e) {
                logger.error("同步GpsActionData异常", e);
            }
            try {
                Function<GpsGyroData, Boolean> consumer = kmxService::insertGpsGyro;
                syncOneTable("t_gpsgyro",null, this::createGpsGyroData, gpsDataService::isEffectGpsData, consumer);
            } catch (Exception e) {
                logger.error("同步GpsGyroData异常", e);
            }
            try {
                Function<GpsLocationData, Boolean> consumer = kmxService::insertGpsLocation;
                syncOneTable("t_gpslocation",null, this::createGpsLocationData, gpsDataService::isEffectGpsData, consumer);
            } catch (Exception e) {
                logger.error("同步GpsLocationData异常", e);
            }
        }else{
            logger.debug("不同步 t_gps_other");
        }
    }

    protected <T extends IdEntity> void syncOneTable(String tableName, String where, Function<ResultSet, T> toObjFunc, Predicate<T> filter, Function<T, Boolean> consumer) {
        logger.debug("同步表开始:" + tableName);
        int batchCount = 1000000;
        List<T> list = readStreamTableData(tableName, where,toObjFunc, new DoubleVo<>(0, batchCount));//读取
        int thisBatchCount = list.size();
        int tableAllCount = 0;
        while (thisBatchCount > 0) {
            list = list.stream().filter(filter).collect(Collectors.toList());
            int newSize = list.size();
            logger.debug("过滤掉无效数据后数据量:"+thisBatchCount+"-->"+newSize);
            int recMinId = list.get(0).getId();
            int recMaxId = list.get(newSize-1).getId();
            logger.debug("数据提取过滤完毕:" + tableName + ",剩余条数：" + newSize+",数据记录id范围："+recMinId+"->"+recMaxId);
            int[] loopCount = new int[]{0};
            int successCount = list.parallelStream().mapToInt(t -> {
                boolean b = false;
                try {
                    b = consumer.apply(t);//入库
                } catch (Exception e) {
                    logger.error("数据存入kmx异常." + tableName, e);
                }
                loopCount[0]++;
                if(loopCount[0] % 500 == 0 ){
                    logger.debug("循环完毕500个数据."+tableName);
                }
                return b ? 1 : 0;
            }).sum();
            logger.debug("同步Kmx完毕:" + tableName + "，总条数：" + thisBatchCount + ",过滤后数量:"+newSize+",剩余成功数量:" + successCount + ",失败数=" + (newSize - successCount));
            tableAllCount += thisBatchCount;
            list = readStreamTableData(tableName, where,toObjFunc, new DoubleVo<>(tableAllCount, batchCount));//读取
            thisBatchCount = list.size();
        }
        logger.debug("同步Kmx完毕:" + tableName + "，总记录条数：" + tableAllCount);
    }

    protected <T> List<T> readStreamTableData(String tabName,String where, Function<ResultSet, T> toObjFunc, DoubleVo<Integer, Integer> limit) {
        String sql = "select * from " + tabName + " order by id limit " + limit.getKey() + "," + limit.getVal();
        if(!StringUtils.isEmpty(where)){
            sql = "select * from " + tabName + " where "+where+" order by id limit " + limit.getKey() + "," + limit.getVal();
        }

        logger.debug("查询sql:" + sql);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>(100000);
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // 流式读取mysql大表 性能极佳
            preparedStatement.setFetchDirection(ResultSet.FETCH_REVERSE);
            preparedStatement.setFetchSize(Integer.MIN_VALUE);
            rs = preparedStatement.executeQuery();
            int row = 0;
            while (rs.next()) {
                try {
                    T t = toObjFunc.apply(rs);//从rs解析成bean
                    list.add(t);
                } catch (Exception e) {
                    logger.error("读行数据异常.row=" + row, e);
                }
                row++;
            }
        } catch (Exception e) {
            logger.error("读取数据库数据异常." + tabName, e);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    protected HelmetSensor createHelmetSensor(ResultSet rs) {
        try {
            HelmetSensor hs = new HelmetSensor();
            hs.setId(getRsInt(rs, "id"));
            hs.setClientId(getRsStr(rs, "clientId"));
            hs.setCreateTime(getRsTime(rs, "createTime"));
            hs.setBattery(getRsInt(rs, "batty"));
            hs.setConcert(getRsInt(rs, "concert"));
            hs.setRelax(getRsInt(rs, "relax"));
            hs.setRuntime(getRsInt(rs, "runtime"));
            hs.setXa(getRsFloat(rs, "xa"));
            hs.setYa(getRsFloat(rs, "ya"));
            hs.setZa(getRsFloat(rs, "za"));
            hs.setXg(getRsFloat(rs, "xg"));
            hs.setYg(getRsFloat(rs, "yg"));
            hs.setZg(getRsFloat(rs, "zg"));
            return hs;
        } catch (Exception e) {
            logger.error("从sql结果集创建HelmetSensor实例异常", e);
        }
        return null;
    }

    protected HelmetGps createHelmetGps(ResultSet rs) {
        try {
            HelmetGps hs = new HelmetGps();
            hs.setId(getRsInt(rs, "id"));
            hs.setClientId(getRsStr(rs, "clientId"));
            hs.setCreateTime(getRsTime(rs, "createTime"));
            hs.setGpsType(getRsInt(rs, "gpsType"));
            hs.setLat(getRsFloat(rs, "lat"));
            hs.setLon(getRsFloat(rs, "lon"));
            return hs;
        } catch (Exception e) {
            logger.error("从sql结果集创建HelmetGps实例异常", e);
        }
        return null;
    }

    protected GpsData createGpsData(ResultSet rs) {
        try {
            GpsData hs = new GpsData();
            hs.setId(getRsInt(rs, "id"));
            hs.setClientId(getRsStr(rs, "clientId"));
            hs.setCreateTime(getRsTime(rs, "createTime"));
            hs.setImei(getRsStr(rs, "imei"));
            hs.setDataType(getRsInt(rs, "dataType"));
            hs.setVal(getRsInt(rs, "val"));
            return hs;
        } catch (Exception e) {
            logger.error("从sql结果集创建GpsData实例异常", e);
        }
        return null;
    }

    protected GpsActionData createGpsActionData(ResultSet rs) {
        try {
            GpsActionData hs = new GpsActionData();
            hs.setId(getRsInt(rs, "id"));
            hs.setClientId(getRsStr(rs, "clientId"));
            hs.setCreateTime(getRsTime(rs, "createTime"));
            hs.setImei(getRsStr(rs, "imei"));
            hs.setAction(getRsInt(rs, "action"));
            hs.setActionVal(getRsStr(rs, "actionVal"));
            hs.setWalk(getRsInt(rs, "walk"));
            return hs;
        } catch (Exception e) {
            logger.error("从sql结果集创建GpsActionData实例异常", e);
        }
        return null;
    }

    protected GpsGyroData createGpsGyroData(ResultSet rs) {
        try {
            GpsGyroData hs = new GpsGyroData();
            hs.setId(getRsInt(rs, "id"));
            hs.setClientId(getRsStr(rs, "clientId"));
            hs.setCreateTime(getRsTime(rs, "createTime"));
            hs.setImei(getRsStr(rs, "imei"));
            hs.setFrontBack(getRsInt(rs, "frontBack"));
            hs.setLeftRight(getRsInt(rs, "leftRight"));
            hs.setRotate(getRsInt(rs, "rotate"));
            hs.setRotateMaxSpeed(getRsInt(rs, "rotateMaxSpeed"));
            hs.setRotateAvgSpeed(getRsInt(rs, "rotateAvgSpeed"));
            hs.setDownAcceleration(getRsLong(rs, "downAcceleration"));
            hs.setUpAcceleration(getRsLong(rs, "upAcceleration"));
            hs.setBackTime(getRsInt(rs, "backTime"));
            return hs;
        } catch (Exception e) {
            logger.error("从sql结果集创建GpsGyroData实例异常", e);
        }
        return null;
    }

    protected GpsLocationData createGpsLocationData(ResultSet rs) {
        try {
            GpsLocationData hs = new GpsLocationData();
            hs.setId(getRsInt(rs, "id"));
            hs.setClientId(getRsStr(rs, "clientId"));
            hs.setCreateTime(getRsTime(rs, "createTime"));
            hs.setImei(getRsStr(rs, "imei"));
            hs.setLat(getRsFloat(rs, "lat"));
            hs.setLon(getRsFloat(rs, "lon"));
            hs.setLatns(getRsInt(rs, "latns"));
            hs.setLonew(getRsInt(rs, "lonew"));
            hs.setSpeed(getRsInt(rs, "speed"));
            hs.setOrient(getRsInt(rs, "orient"));
            hs.setOldnew(getRsInt(rs, "oldnew"));
            hs.setAltPosNeg(getRsInt(rs, "altPosNeg"));
            hs.setAlt(getRsInt(rs, "alt"));
            hs.setStar(getRsInt(rs, "star"));
            hs.setGpsTime(getRsTime(rs, "gpsTime"));
            return hs;
        } catch (Exception e) {
            logger.error("从sql结果集创建GpsLocationData实例异常", e);
        }
        return null;
    }

    public long getRsLong(ResultSet rs, String str) throws SQLException {
        return rs.getLong(str);
    }

    public int getRsInt(ResultSet rs, String str) throws SQLException {
        return rs.getInt(str);
    }

    public float getRsFloat(ResultSet rs, String str) throws SQLException {
        return rs.getFloat(str);
    }

    public String getRsStr(ResultSet rs, String str) throws SQLException {
        return rs.getString(str);
    }

    public LocalDateTime getRsTime(ResultSet rs, String str) throws SQLException {
        Timestamp ts = rs.getTimestamp(str);
        if (ts != null) {
            return Dates.toLocalDateTime(ts);
        }
        return null;
    }

}

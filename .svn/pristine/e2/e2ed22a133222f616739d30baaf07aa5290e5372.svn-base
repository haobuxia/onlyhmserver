package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSON;
import com.sagittarius.bean.result.StringPoint;
import com.tianyi.helmet.server.dao.data.*;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsCatagoryEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.SgGpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.WorkModeEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.service.client.TyBoxImeiService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.MainDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.tianyi.helmet.server.service.kmx.SensorSupport.TYBOX_DEVICE_IDPREFIX;

/**
 * 车载盒子数据服务
 * <p>
 * Created by liuhanc on 2017/10/19.
 */
@Service
public class TyBoxDataService {
    private Logger logger = LoggerFactory.getLogger(TyBoxDataService.class);

    @Autowired
    GpsDataDao gpsDataDao;
    @Autowired
    GpsLocationDataDao gpsLocationDao;
    @Autowired
    GpsGyroDataDao gpsGyroDao;
    @Autowired
    GpsActionDataDao gpsActionDao;

    @Autowired
    TyBoxDataV1Resorver tyBoxDataV1Resorver;
    @Autowired
    TyBoxDataV2Resorver tyBoxDataV2Resorver;
    @Autowired
    TyBoxDataV3Resorver tyBoxDataV3Resorver;
    @Autowired
    GpsLineDataDao gpsLineDataDao;
    @Autowired
    TyBoxLineDataDao tyBoxLineDataDao;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private TyBoxImeiService imeiService;
    @Autowired
    private HelmetSensorService helmetSensorService;

    public GpsLocationData getLatestGpsLocation(String imei) {
        StringPoint sp = kmxService.queryStringLatest(TYBOX_DEVICE_IDPREFIX + imei, GpsDataTypeItemEnum.GPS_LOCATION.toString());
        if (sp != null) {
            GpsLocationData gld = tyBoxDataV1Resorver.resorveLocationData(sp.getValue());
            return gld;
        }
        return null;
    }

    public void insert(AbstractGpsData gps) {
        GpsCatagoryEnum catagory = gps.getGpsCatagoryEnum();
        switch (catagory) {
            case ACTION:
                GpsActionData gad = (GpsActionData) gps;
                kmxService.insertGpsAction(gad);
//                gpsActionDao.insert((GpsActionData) gps);
                break;
            case GYRO:
                GpsGyroData ggd = (GpsGyroData) gps;
                kmxService.insertGpsGyro(ggd);
//                kmxService.insert(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei, GpsDataTypeItemEnum.GYRO.toString(), time, now, gps.getDataFullPart()[3]);
//                gpsGyroDao.insert((GpsGyroData) gps);
                break;
            case DATA:
                GpsData gd = (GpsData) gps;
//                gpsDataDao.insert(gd);
                kmxService.insertGpsData(gd);
                break;
            case LOCAT:
                GpsLocationData gld = (GpsLocationData) gps;
                kmxService.insertGpsLocation(gld);
//                gpsLocationDao.insert((GpsLocationData) gps);
                break;
            default:
                break;
        }
    }

    public String selectImeiByClientIdTimeRange(String clientId, LocalDateTime time1, LocalDateTime time2) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("clientId", clientId);
        map.put("createTime1", time1);
        map.put("createTime2", time2);
        return gpsDataDao.selectImeiByClientIdTimeRange(map);
    }

    /**
     * 关键核心的数字型的车辆传感器数据
     *
     * @param clientId
     * @param createTime1
     * @param createTime2
     * @return
     */
    public DoubleVo<String, Map<String, Map<Long, Integer>>> selectKeyDataBy(String clientId, String imei, LocalDateTime createTime1, LocalDateTime createTime2) {
        imei = imeiRecheck(clientId, imei, createTime1, createTime2);
        if (imei == null) return new DoubleVo(imei, null);

        long time1 = Dates.toDate(createTime1).getTime();
        long time2 = Dates.toDate(createTime2).getTime();
        List<GpsDataTypeItemEnum> keyDataTypeList = GpsDataTypeItemEnum.getKeyDataTypeList();
        Map<String, Map<Long, Integer>> gpsMapData = kmxService.queryGpsDataMap(imei, time1, time2, keyDataTypeList);// map string是监控项目，<long,integer>分别是时间、此刻的值

//        logger.debug("查询车辆传感器关键数据:" + clientId + "," + createTime1 + "-->" + createTime2 + ",对应的天远盒子imei:" + imei + ",查询传感器:" + Arrays.toString(keyDataTypeList.toArray()) + ",最终传感器数据量:" + gpsMapData.size());
//        gpsMapData.keySet().stream().forEach(sensorId -> {
//            logger.debug("传感器:" + sensorId + ",时序数据量:" + gpsMapData.get(sensorId).size());
//        });
        return new DoubleVo(imei, gpsMapData);
    }
    /**
     * 关键核心的数字型的车辆传感器数据
     *
     * @param clientId
     * @param createTime1
     * @param createTime2
     * @return
     */
    public DoubleVo<String, Map<String, Map<Long, String>>> selectKeyDataBy1(String clientId, String imei, LocalDateTime createTime1, LocalDateTime createTime2) {
        imei = imeiRecheck(clientId, imei, createTime1, createTime2);
        if (imei == null) return new DoubleVo(imei, null);

        long time1 = Dates.toDate(createTime1).getTime();
        long time2 = Dates.toDate(createTime2).getTime();
        List<GpsDataTypeItemEnum> keyDataTypeList = GpsDataTypeItemEnum.getKeyDataTypeList();
        Map<String, Map<Long, String>> gpsMapData = kmxService.queryGpsDataMap1(imei, time1, time2, keyDataTypeList);

//        logger.debug("查询车辆传感器关键数据:" + clientId + "," + createTime1 + "-->" + createTime2 + ",对应的天远盒子imei:" + imei + ",查询传感器:" + Arrays.toString(keyDataTypeList.toArray()) + ",最终传感器数据量:" + gpsMapData.size());
//        gpsMapData.keySet().stream().forEach(sensorId -> {
//            logger.debug("传感器:" + sensorId + ",时序数据量:" + gpsMapData.get(sensorId).size());
//        });
        return new DoubleVo(imei, gpsMapData);
    }

    /**
     * 关键核心的数字型的车辆传感器数据
     *
     * @param clientId
     * @param createTime1
     * @param createTime2
     * @return
     */
    public DoubleVo<String, Map<String, Map<Long, Integer>>> selectKeyDataBySg(String clientId, String imei, LocalDateTime createTime1, LocalDateTime createTime2) {
        imei = imeiRecheck(clientId, imei, createTime1, createTime2);
        if (imei == null) return new DoubleVo(imei, null);

        long time1 = Dates.toDate(createTime1).getTime();
        long time2 = Dates.toDate(createTime2).getTime();
        List<SgGpsDataTypeItemEnum> keyDataTypeList = SgGpsDataTypeItemEnum.getKeyDataTypeList();
        Map<String, Map<Long, Integer>> gpsMapData = kmxService.queryGpsDataMapSg(imei, time1, time2, keyDataTypeList);// map string是监控项目，<long,integer>分别是时间、此刻的值

//        logger.debug("查询车辆传感器关键数据:" + clientId + "," + createTime1 + "-->" + createTime2 + ",对应的天远盒子imei:" + imei + ",查询传感器:" + Arrays.toString(keyDataTypeList.toArray()) + ",最终传感器数据量:" + gpsMapData.size());
//        gpsMapData.keySet().stream().forEach(sensorId -> {
//            logger.debug("传感器:" + sensorId + ",时序数据量:" + gpsMapData.get(sensorId).size());
//        });
        return new DoubleVo(imei, gpsMapData);
    }

    public DoubleVo<String, Map<GpsDataTypeItemEnum, Map<Long, String>>> selectIntDataBy(String clientId, String imei, LocalDateTime createTime1, LocalDateTime createTime2, List<DoubleVo<GpsDataTypeItemEnum, Function<Integer, String>>> gpsDataTypes) {
        imei = imeiRecheck(clientId, imei, createTime1, createTime2);
        if (imei == null) return new DoubleVo(imei, null);
        long time1 = Dates.toDate(createTime1).getTime();
        long time2 = Dates.toDate(createTime2).getTime();

        final String finalImei = imei;
        Map<GpsDataTypeItemEnum, Map<Long, String>> strDataMap = gpsDataTypes.stream().collect(Collectors.toMap(
                doubleVo -> doubleVo.getKey(),
                doubleVo -> {
                    String typeName = doubleVo.getKey().toString();
                    Map<Long, Integer> timeData = kmxService.queryGpsIntDataMap(finalImei, time1, time2, typeName);
                    Map<Long, String> timeData2 = timeData.keySet().stream().collect(Collectors.toMap(
                            time -> time,
                            time -> {
                                Integer val = timeData.get(time);
                                //数据进行转换
                                String val2 = doubleVo.getVal().apply(val);
                                return val2;
                            }));
                    return timeData2;
                }
        ));
        return new DoubleVo(imei, strDataMap);
    }

    public DoubleVo<String, Map<Integer, Map<Long, String>>> selectStringDataBy(String clientId, String imei, LocalDateTime createTime1, LocalDateTime createTime2, List<DoubleVo<GpsDataTypeItemEnum, Function<String, String>>> gpsDataTypes) {
        imei = imeiRecheck(clientId, imei, createTime1, createTime2);
        if (imei == null) return new DoubleVo(imei, null);
        long time1 = Dates.toDate(createTime1).getTime();
        long time2 = Dates.toDate(createTime2).getTime();

        final String finalImei = imei;
        Map<Integer, Map<Long, String>> strMap = gpsDataTypes.stream().collect(Collectors.toMap(
                doubleVo -> doubleVo.getKey().getId(),
                doubleVo -> {
                    String typeName = doubleVo.getKey().toString();
                    Map<Long, String> timeData = kmxService.queryGpsStringDataMap(finalImei, time1, time2, typeName);
                    timeData.keySet().stream().forEach(time -> {
                        String val = timeData.get(time);
                        //数据进行转换
                        String val2 = doubleVo.getVal().apply(val);
                        timeData.put(time, val2);
                    });
                    return timeData;
                }
        ));

        logger.debug("查询车辆传感器关键数据:" + clientId + "," + createTime1 + "-->" + createTime2 + ",对应的天远盒子imei:" + imei);
        gpsDataTypes.stream().forEach(doubleVo -> {
            logger.debug("传感器:" + doubleVo.getKey().toString() + ",时序数据量:" + strMap.get(doubleVo.getKey().getId()).size());
        });

        return new DoubleVo(imei, strMap);
    }

    private String imeiRecheck(String clientId, String imei, LocalDateTime createTime1, LocalDateTime createTime2) {
        if (StringUtils.isEmpty(imei)) {
            long time1 = Dates.toDate(createTime1).getTime();
            long time2 = Dates.toDate(createTime2).getTime();
            imei = kmxService.getHelmetTyBoxImei(clientId, time1 + (time2 - time1) / 2);
            if (StringUtils.isEmpty(imei)) {
                logger.debug("查询车辆传感器数据:" + clientId + "," + createTime1 + "-->" + createTime2 + ",从kmx没找到对应的天远盒子imei信息");
                //从mysql再取1次
                if (createTime2.isBefore(LocalDateTime.of(2018, 3, 1, 0, 0, 0, 0))) {
                    //查询时间比20180301早则再查询mysql一次，因为mysql存的还有早期数据
                    imei = selectImeiByClientIdTimeRange(clientId, createTime1, createTime2);
                    if (StringUtils.isEmpty(imei)) {
                        logger.debug("查询车辆传感器数据:" + clientId + "," + createTime1 + "-->" + createTime2 + ",从kmx和mysql都没找到对应的天远盒子imei信息");
                        return null;
                    }
                }
            }
        }
        return imei;
    }

    public List<GpsData> selectKeyDataFromMysqlBy(String clientId, LocalDateTime createTime1, LocalDateTime createTime2) {
        List<Integer> keyDataTypeList = GpsDataTypeItemEnum.getKeyDataTypeList().stream().map(GpsDataTypeItemEnum::getId).collect(Collectors.toList());

        Map<String, Object> params = new HashMap<>(4);
        params.put("clientId", clientId);
        params.put("createTime1", createTime1);
        params.put("createTime2", createTime2);
        params.put("dataTypeList", keyDataTypeList);
        List<? extends AbstractGpsData> list = selectFromMySqlBy(GpsCatagoryEnum.DATA, params);
        return (List<GpsData>) list;
    }

    public List<? extends AbstractGpsData> selectFromMySqlBy(GpsCatagoryEnum catagory, Map<String, Object> params) {
        switch (catagory) {
            case ACTION:
                return gpsActionDao.selectBy(params);
            case GYRO:
                return gpsGyroDao.selectBy(params);
            case DATA:
                return gpsDataDao.selectBy(params);
            case LOCAT:
                return gpsLocationDao.selectBy(params);
            default:
                break;
        }
        return null;
    }

    @CacheEvict(value = CacheKeyConstants.VIDEO_GPS_BY_VIDEO_ID, key = "#videoId.toString()")
    public void clearVideoGpsDataCache(int videoId) {
        logger.debug("删除二手机视频的传感器数据缓存.v.id=" + videoId);
    }


    /**
     * 获得某个视频发生时间段内对应的gps数据信息
     *
     * @param v
     * @return
     */
    @Cacheable(value = CacheKeyConstants.VIDEO_GPS_BY_VIDEO_ID_1, key = "#v.id.toString()", unless = "#result == null")
    public DoubleVo<String, Map<Integer, List<DoubleVo<Long, String>>>> selectGpsDataListByVideo1(Video v) {
        //车载设备信息
        String imei = v.getImei();
        DoubleVo<String, Map<String, Map<Long, String>>> vo = selectKeyDataBy1(v.getClientId(), imei, v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()));
        if (StringUtils.isEmpty(imei) && !StringUtils.isEmpty(vo.getKey())) {
            //此处认为1个视频录制期间，头盔只会连接1个车载蓝牙设备，所以取第1个数据的imei
            imei = vo.getKey();
            v.setImei(imei);
        }

        Map<String, Map<Long, String>> dataMap = vo.getVal();
        if (vo == null || CollectionUtils.isEmpty(dataMap)) {
            return new DoubleVo<>(imei, new HashMap<>(0));
        }

        Comparator<DoubleVo<Long, String>> comprator = Comparator.comparing(DoubleVo::getKey);//按时间排序
        //分组
        Map<Integer, List<DoubleVo<Long, String>>> effectDataListMap = dataMap.keySet().stream()
                .filter(sensorId -> {
                    Map<Long, String> timeValMap = dataMap.get(sensorId);
//                    boolean isZero = timeValMap.values().stream().distinct().allMatch(val -> val == 0);
                    return true;
                }).collect(Collectors.toMap(
                        sensorId -> GpsDataTypeItemEnum.valueOf(sensorId).getId(),
                        sensorId -> {
                            Map<Long, String> timeValMap = dataMap.get(sensorId);
                            return timeValMap.keySet().stream().map(time -> {
                                return new DoubleVo<Long, String>(time, timeValMap.get(time));
                            }).sorted(comprator).collect(Collectors.toList());
                        })
                );

        DoubleVo<String, Map<Integer, List<DoubleVo<Long, String>>>> doubleVo = new DoubleVo<>(imei, effectDataListMap);
        return doubleVo;
    }

    @Cacheable(value = CacheKeyConstants.VIDEO_GPS_BY_VIDEO_ID, key = "#v.id.toString()", unless = "#result == null")
    public DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> selectGpsDataListByVideo(Video v) {
        //车载设备信息
        String imei = v.getImei();
        String description = v.getDescription();
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> doubleVo = new DoubleVo<>();
        if(description!=null && description.equals("600s")) {// 神钢盒子数据
            DoubleVo<String, Map<String, Map<Long, Integer>>> vo = selectKeyDataBySg(v.getClientId(), imei, v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()));
            if (StringUtils.isEmpty(imei) && !StringUtils.isEmpty(vo.getKey())) {
                //此处认为1个视频录制期间，头盔只会连接1个车载蓝牙设备，所以取第1个数据的imei
                imei = vo.getKey();
                v.setImei(imei);
            }

            Map<String, Map<Long, Integer>> dataMap = vo.getVal();// map string是监控项目，<long,integer>分别是时间、此刻的值
            if (vo == null || CollectionUtils.isEmpty(dataMap)) {
                return new DoubleVo<>(imei, new HashMap<>(0));
            }

            Comparator<DoubleVo<Long, Integer>> comprator = Comparator.comparing(DoubleVo::getKey);//按时间排序
            //分组
            Map<Integer, List<DoubleVo<Long, Integer>>> effectDataListMap = dataMap.keySet().stream()
                    .filter(sensorId -> {
                        Map<Long, Integer> timeValMap = dataMap.get(sensorId);
//                    boolean isZero = timeValMap.values().stream().distinct().allMatch(val -> val == 0);
                        return true;
                    }).collect(Collectors.toMap(
                            sensorId -> SgGpsDataTypeItemEnum.valueOf(sensorId).getId(),
                            sensorId -> {
                                Map<Long, Integer> timeValMap = dataMap.get(sensorId);
                                return timeValMap.keySet().stream().map(time -> {
                                    return new DoubleVo<Long, Integer>(time, timeValMap.get(time));
                                }).sorted(comprator).collect(Collectors.toList());
                            })
                    );
            doubleVo = new DoubleVo<>(imei, effectDataListMap);//Integer 是监控项目ID
        } else {
            DoubleVo<String, Map<String, Map<Long, Integer>>> vo = selectKeyDataBy(v.getClientId(), imei, v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()));
            if (StringUtils.isEmpty(imei) && !StringUtils.isEmpty(vo.getKey())) {
                //此处认为1个视频录制期间，头盔只会连接1个车载蓝牙设备，所以取第1个数据的imei
                imei = vo.getKey();
                v.setImei(imei);
            }

            Map<String, Map<Long, Integer>> dataMap = vo.getVal();// map string是监控项目，<long,integer>分别是时间、此刻的值
            if (vo == null || CollectionUtils.isEmpty(dataMap)) {
                return new DoubleVo<>(imei, new HashMap<>(0));
            }

            Comparator<DoubleVo<Long, Integer>> comprator = Comparator.comparing(DoubleVo::getKey);//按时间排序
            //分组
            Map<Integer, List<DoubleVo<Long, Integer>>> effectDataListMap = dataMap.keySet().stream()
                    .filter(sensorId -> {
                        Map<Long, Integer> timeValMap = dataMap.get(sensorId);
//                    boolean isZero = timeValMap.values().stream().distinct().allMatch(val -> val == 0);
                        return true;
                    }).collect(Collectors.toMap(
                            sensorId -> GpsDataTypeItemEnum.valueOf(sensorId).getId(),
                            sensorId -> {
                                Map<Long, Integer> timeValMap = dataMap.get(sensorId);
                                return timeValMap.keySet().stream().map(time -> {
                                    return new DoubleVo<Long, Integer>(time, timeValMap.get(time));
                                }).sorted(comprator).collect(Collectors.toList());
                            })
                    );
            doubleVo = new DoubleVo<>(imei, effectDataListMap);//Integer 是监控项目ID
        }
        return doubleVo;
    }


    public Map<GpsDataTypeItemEnum, Map<Long, String>> selectActionGyroDataByVideo(Video v) {
        long time1 = Dates.toDate(v.getCreateTime()).getTime();
        long time2 = time1 + v.getSeconds() * 1000;
        Map<Long, GpsActionData> actionMap = kmxService.queryGpsActionDataMap(v.getImei(), time1, time2);
        Map<Long, GpsGyroData> gyroMap = kmxService.queryGpsGyroDataMap(v.getImei(), time1, time2);
        Map<Long, String> actionStrMap = actionMap.keySet().stream().collect(Collectors.toMap(time -> time, time -> actionMap.get(time).toCnString()));
        Map<Long, String> gyroStrMap = gyroMap.keySet().stream().collect(Collectors.toMap(time -> time, time -> gyroMap.get(time).toCnString()));
        Map<GpsDataTypeItemEnum, Map<Long, String>> map = new HashMap<>(2);
        map.put(GpsDataTypeItemEnum.ACTION, actionStrMap);
        map.put(GpsDataTypeItemEnum.GYRO, gyroStrMap);
        return map;
    }

    //动作数据
    public Map<Long, String> selectActionDataByVideo(Video v) {
        long time1 = Dates.toDate(v.getCreateTime()).getTime();
        long time2 = time1 + v.getSeconds() * 1000;
        Map<Long, GpsActionData> actionMap = kmxService.queryGpsActionDataMap(v.getImei(), time1, time2);
        Map<Long, String> actionStrMap = actionMap.keySet().stream().collect(Collectors.toMap(time -> time, time -> JSON.toJSONString(actionMap.get(time))));
        return actionStrMap;
    }

    //查询视频的工作模式数据
    public Map<GpsDataTypeItemEnum, Map<Long, String>> selectWorkModeDataByVideo(Video v) {
    	Function<Integer, String> gpsDataTypes = WorkModeEnum::translate;
        DoubleVo<String, Map<GpsDataTypeItemEnum, Map<Long, String>>> vo = this.selectIntDataBy(v.getClientId(), v.getImei(), v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()), Arrays.asList(new DoubleVo<>(GpsDataTypeItemEnum.WORK_MODE,gpsDataTypes)));
        return vo.getVal();
    }
    /**
     * 1个100d gps文件的数据整体入库
     *
     * @param line
     */
    public MainDetailVo<GpsLineData, AbstractGpsData> saveDgpsData(String line, String clientId, int fileId, int lineNo, boolean saveToDb) {
        MainDetailVo<GpsLineData, AbstractGpsData> md = tyBoxDataV2Resorver.resorveGpsData(line, clientId);
        List<AbstractGpsData> list = md.getList();
        GpsLineData ld = md.getMain();

        if (list != null && list.size() > 0 && saveToDb) {
            ld.setDataCount(list.size());
            ld.setFileId(fileId);
            ld.setLineNo(lineNo);

            boolean clientIdEffect = helmetSensorService.isEffectHelmetData(clientId);
            if (clientIdEffect) {
                list.stream().filter(gpsData -> {
                    return imeiService.isEffectImei(gpsData.getImei());
                }).forEach(gpsData -> {
                    try {
                        this.insert(gpsData);
                    } catch (Exception e) {
                        logger.error("保存gps数据入库异常.data=" + JSON.toJSONString(gpsData), e);
                    }
                });
            } else {
                logger.info("头盔客户端无效，对应车辆传感器数据不入库." + clientId);
            }

            try {
                gpsLineDataDao.insert(ld);
            } catch (Exception e) {
                logger.error("保存gps行数据基本信息入库异常.fileId=" + fileId + ",lineNo=" + lineNo, e);
            }
        }

        return md;
    }
    /**
     * 1个gps文件的数据整体入库
     *
     * @param line
     */
    public MainDetailVo<GpsLineData, AbstractGpsData> saveGpsData(String line, String clientId, int fileId, int lineNo, boolean saveToDb) {
        MainDetailVo<GpsLineData, AbstractGpsData> md = tyBoxDataV1Resorver.resorveGpsData(line, clientId);
        List<AbstractGpsData> list = md.getList();
        GpsLineData ld = md.getMain();

        if (list != null && list.size() > 0 && saveToDb) {
            ld.setDataCount(list.size());
            ld.setFileId(fileId);
            ld.setLineNo(lineNo);

            boolean clientIdEffect = helmetSensorService.isEffectHelmetData(clientId);
            if (clientIdEffect) {
                list.stream().filter(gpsData -> {
                    return imeiService.isEffectImei(gpsData.getImei());
                }).forEach(gpsData -> {
                    try {
                        this.insert(gpsData);
                    } catch (Exception e) {
                        logger.error("保存gps数据入库异常.data=" + JSON.toJSONString(gpsData), e);
                    }
                });
            } else {
                logger.info("头盔客户端无效，对应车辆传感器数据不入库." + clientId);
            }

            try {
                gpsLineDataDao.insert(ld);
            } catch (Exception e) {
                logger.error("保存gps行数据基本信息入库异常.fileId=" + fileId + ",lineNo=" + lineNo, e);
            }
        }

        return md;
    }

    /**
     * 1个tybox文件的数据整体入库
     *
     * @param line
     */
    public MainDetailVo<TyBoxLineData, AbstractGpsData> saveTyBoxLineData(String line, String clientId, int fileId, int lineNo, boolean saveToDb) {
        MainDetailVo<TyBoxLineData, AbstractGpsData> md = tyBoxDataV3Resorver.resorveTyBoxData(line, clientId); // 解析出行数据
        TyBoxLineData lineData = md.getMain();
        if (lineData != null && saveToDb) {
            lineData.setFileId(fileId);
            lineData.setLineNo(lineNo);
            try {
                tyBoxLineDataDao.insert(lineData);
            } catch (Exception e) {
                logger.error("保存tybox行数据基本信息入库异常.fileId=" + fileId + ",lineNo=" + lineNo, e);
            }
        }
        List<AbstractGpsData> list = md.getList();
        if (list != null && list.size() > 0 && saveToDb) {
            boolean clientIdEffect = helmetSensorService.isEffectHelmetData(clientId);
            if (clientIdEffect) {
                list.stream().filter(gpsData -> {
                    return imeiService.isEffectImei(gpsData.getImei());
                }).forEach(gpsData -> {
                    try {
                        GpsData gd = (GpsData) gpsData;
                        kmxService.insertShgtGpsData(gd);
                    } catch (Exception e) {
                        logger.error("保存shgtgps数据入库异常.data=" + JSON.toJSONString(gpsData), e);
                    }
                });
            } else {
                logger.info("头盔客户端无效，对应车辆传感器数据不入库." + clientId);
            }
        }
        return md;
    }

    public List<TyBoxLineData> selectTyBox(Map<String, Object> map) {
        List<TyBoxLineData> list = tyBoxLineDataDao.selectBy(map);
        return list;
    }

    public int countBy(Map<String, Object> map) {
        return tyBoxLineDataDao.countBy(map);
    }
}

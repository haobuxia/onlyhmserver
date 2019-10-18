package com.tianyi.helmet.server.service.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.log.OperaLog;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.*;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.util.Dates;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mqtt消息消费者。用于处理各类消息
 * <p>
 * Created by liuhanc on 2017/10/9.
 */
@Component
public class MqttBackendConsumer {

    private Logger logger = LoggerFactory.getLogger(MqttBackendConsumer.class);

    @Autowired
    private HelmetSensorService helmetSensorService;
    @Autowired
    private HelmetGpsService helmetGpsService;
    @Autowired
    private HelmetStateService helmetStateService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private HelmetDataWebSocketService helmetDataWebSocketService;
    @Autowired
    private OperaLogService operaLogService;
    @Autowired
    private HelmetHeartBeatService helmetHeartBeatService;
    @Autowired
    private HelmetBatteryService helmetBatteryService;
    @Autowired
    private HelmetMindWaverService helmetMindWaverService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HelmetOnlineStatusService helmetOnlineStatusService;

    private static final LocalDateTime baseTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);

    public JSONObject parseMqttMessage(String topicName, MqttMessage msg) {
        JSONObject jo = null;
        String json = new String(msg.getPayload());
//        logger.debug("收到mqtt消息.topic="+topicName+",内容"+json);
        try {
            jo = JSON.parseObject(json);
            // 2019-02-11注释处理在线心跳
//            boolean isWill = processWillData(jo);
//            if (isWill) return null;
        } catch (Exception e) {
            logger.error("解析mqtt消息异常.topicName=" + topicName + ",json=" + json + ",异常：" + e.getMessage());
            return null;
        }

        return jo;
    }

    /**
     * 头盔传感器数据
     */
    public void processHelmetSensorData(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        String time = jo.getString("time");

        LocalDateTime createTime = null;
        if ("0".equals(time)) {
            createTime = baseTime;
        } else {
            createTime = Dates.toLocalDateTime(new Date(Long.parseLong(time)));
            if (createTime.isBefore(baseTime)) {
                createTime = baseTime;
            }
        }

        String neUserName = jo.getString("id");
        Float xa = jo.getFloat("xa");
        Float ya = jo.getFloat("ya");
        Float za = jo.getFloat("za");
        Float xg = jo.getFloat("xg");
        Float yg = jo.getFloat("yg");
        Float zg = jo.getFloat("zg");
        String runtime = jo.getString("runtime");

        /**
         * update by xiayuan 2018/10/10
         */
        HelmetSensor sensor = new HelmetSensor();
        /*Map<String, Object> map = new HashMap<>();
        map.put("neUserHel", neUserName);
        List<User> users = userService.listByNoPage(map);
        User user = users.get(0);
        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
        String imei = "";
        for (EquipmentLedger e : list) {
            String deviceUUID = (String) redisTemplate.opsForValue().get(neUserName);
            if (StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                imei = deviceUUID;
            }
        }*/
        String imei = jo.getString("UUID");
        sensor.setNeUsername(jo.getString("id"));
        sensor.setClientId(jo.getString("UUID"));
        sensor.setCreateTime(createTime);

        sensor.setXa(xa);
        sensor.setYa(ya);
        sensor.setZa(za);
        sensor.setXg(xg);
        sensor.setYg(yg);
        sensor.setZg(zg);

        if (!StringUtils.isEmpty(runtime)) {
            if (runtime.contains(":")) {
                String[] parts = runtime.split(":");
                Integer second = new Integer(parts[0]) * 60 * 60 + Integer.parseInt(parts[1]) * 60 + Integer.parseInt(parts[2]);
                sensor.setRuntime(second);
            } else {
                Long runtimeLong = Long.parseLong(runtime);
                if (runtimeLong.longValue() > Integer.MAX_VALUE) {
                    logger.debug("mqtt收到头盔设备运行时间是个Long值:" + runtimeLong + ",imei=" + jo.getString("UUID"));
                } else {
                    sensor.setRuntime(new Integer(runtime));
                }
            }
        }
        int battery = helmetBatteryService.getLatestBattery(imei);
        sensor.setBattery(battery);
        HelmetMindWaver mindWaver = helmetMindWaverService.getLatestMindWaver(imei);
        if(mindWaver != null) {
            sensor.setRelax(mindWaver.getRelax());
            sensor.setConcert(mindWaver.getConcert());
        }
        helmetSensorService.insert(sensor);

        noticeToClient(sensor);

        /*//sensor信息中可能还包括了gps数据
        Float lat = jo.getFloat("lat");
        Float lon = jo.getFloat("lon");

        if (lat != null && lon != null && lat != 0 && lon != 0) {
            equipmentService.cacheGpsState(sensor.getClientId(), true);//gps定位成功
            HelmetGps gps = new HelmetGps();
            gps.setClientId(sensor.getClientId());
            gps.setCreateTime(sensor.getCreateTime());
            gps.setGpsType(1);//gps模块定位
            gps.setLat(lat);
            gps.setLon(lon);
            helmetGpsService.insert(gps);
            noticeToClient(gps);
        } else {
            equipmentService.cacheGpsState(sensor.getClientId(), false);//gps定位失败
        }*/
    }

    /**
     * 网络定位的gps数据
     */
    public void processGpsData(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        Float lat = jo.getFloat("lat");
        Float lon = jo.getFloat("lon");
        String neUserName = jo.getString("id");
        String imei = jo.getString("UUID");
        if (lat != null && lon != null && lat != 0 && lon != 0) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("neUserHel", neUserName);
//            EquipmentLedger equipmentLedger=equipmentService.selectByUUID(imei);
//            List<User> users = userService.listByNoPage(map);
//            User user = userService.selectById(equipmentLedger.getUserId());
//            List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
//            String imei = "";
//            for (EquipmentLedger e : list) {

//            String deviceUUID = (String) redisTemplate.opsForValue().get(neUserName);
//            if (org.apache.commons.lang3.StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(imei)) {
//                imei = deviceUUID;
//            }

//            }
            boolean gpsOk = equipmentService.isGpsStateOk(imei);
            if (gpsOk) {
//                 logger.info("头盔"+clientId+"的gps定位状态ok,网络定位的gps数据忽略");
                return;
            }
//            logger.info("头盔"+clientId+"的gps定位失败,网络定位的gps数据进行保存");
            HelmetGps gps = new HelmetGps();
            gps.setClientId(imei);
            gps.setGpsType(2);//网络定位
            String time = jo.getString("time");
            LocalDateTime createTime = null;
            if ("0".equals(time)) {
                createTime = baseTime;
            } else {
                createTime = Dates.toLocalDateTime(new Date(Long.parseLong(time)));
                if (createTime.isBefore(baseTime)) {
                    createTime = baseTime;
                }
            }
            gps.setCreateTime(createTime);
            gps.setLat(lat);
            gps.setLon(lon);
            helmetGpsService.insert(gps);
            noticeToClient(gps);
        }
    }

    /**
     * 状态数据
     */
    public void processStateData(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        HelmetState state = new HelmetState();
        String id = jo.getString("id");
        state.setClientId(id);
        String time = jo.getString("state_time");
        if (StringUtils.isEmpty(time)) {
            state.setCreateTime(LocalDateTime.now());
        } else {
            state.setCreateTime(Dates.toLocalDateTime(new Date(Long.parseLong(time))));
        }
        state.setOnlineState(jo.getInteger("online_state"));
        state.setNetType(jo.getInteger("net_state"));
        helmetStateService.insert(state);
        noticeToClient(state);
    }

    /**
     * 处理心跳包
     *
     * @param jo
     * @return
     */
    public boolean processWillData(JSONObject jo) {
        String terminal_uid = jo.getString("terminal_uid");
        boolean isWillPacket = false;
        if (!StringUtils.isEmpty(terminal_uid)) {
            //说明是will消息 格式:{"terminal_uid":"helmet1001"}
            isWillPacket = true;
        } else {
            terminal_uid = jo.getString("id");
            if (StringUtils.isEmpty(terminal_uid)) {
                terminal_uid = jo.getString("helmet_id");
            }
        }

        if (!StringUtils.isEmpty(terminal_uid)) {
            Map<String, Object> map = new HashMap<>();
            map.put("neUserHel", terminal_uid);
            List<User> users = userService.listByNoPage(map);
            User user = users.get(0);
            List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
            String imei = "";
            for (EquipmentLedger e : list) {
                String deviceUUID = (String) redisTemplate.opsForValue().get(terminal_uid);
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                    imei = deviceUUID;
                }
            }
            equipmentService.cacheWillTime(imei);
        }

        return isWillPacket;
    }


    /**
     * 操作日志记录
     */
    public void processOperLog(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        String clientId = jo.getString("id");
        String UUID = jo.getString("UUID");
        String deviceType = jo.getString("deviceType");
        String logType = jo.getString("logType");
        String logflowId = jo.getString("logflowId");
        Integer orderNo = jo.getInteger("orderNo");
        LocalDateTime time = Dates.toLocalDateTime(new Date(Long.parseLong(jo.getString("time"))));
        String logContent = jo.getString("content");
        Integer logNature = jo.getInteger("logNature");

        OperaLog log = new OperaLog();
//        String id = jo.getString("id");
//        String time = jo.getString("time");
//        String content = jo.getString("content");
//        if (StringUtils.isEmpty(time)) {
//        	log.setCreateTime(LocalDateTime.now());
//        } else {
//        	log.setCreateTime(Dates.toLocalDateTime(new Date(Long.parseLong(time))));
//        }
//        log.setClientId(id);
//        log.setLogContent(content);
        log.setCreateTime(LocalDateTime.now());
        log.setClientId(clientId);
        log.setUUID(UUID);
        log.setDeviceType(deviceType);
        log.setLogType(logType);
        log.setLogflowId(logflowId);
        log.setOrderNo(orderNo);
        log.setLogDate(time.toLocalDate());
        log.setLogTime(time);
        log.setLogContent(logContent);
        log.setLogNature(logNature);

        operaLogService.insert(log);
    }

    /**
     * 在线心跳
     * 2018-9-13 wenxinyan
     */
    public void processNewStateData(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        HelmetHeartBeat helmethb = new HelmetHeartBeat();
        helmethb.setClientId(jo.getString("UUID"));
        helmethb.setNeUsername(jo.getString("id"));
        Object useridObj = jo.getObject("userid", Object.class );
        Integer userid = -1;
        try{
            userid = (int) useridObj;
        }catch (Exception e){
            userid = -1;
        }
        helmethb.setUserId(userid);
        String time = jo.getString("time");
        if (StringUtils.isEmpty(time)) {
            helmethb.setCreateTime(LocalDateTime.now());
        } else {
            helmethb.setCreateTime(Dates.toLocalDateTime(new Date(Long.parseLong(time))));
        }
        helmethb.setOnlineType(jo.getInteger("type"));
//        helmetHeartBeatService.insert(helmethb); // 20190221注销存储心跳到数据库
        // 设置在线状态
        helmetOnlineStatusService.handleOnline(helmethb);
        noticeToClient(helmethb);

    }

    /*
    * 处理电池数据
    * 2018-9-20 wenxinyan
    * */
    public void processBatteryData(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        HelmetBattery helmetb = new HelmetBattery();
        helmetb.setNeUsername(jo.getString("id"));
        helmetb.setClientId(jo.getString("UUID"));
        /*Map<String, Object> map = new HashMap<>();
        map.put("neUserHel", helmetb.getNeUsername());
        List<User> users = userService.listByNoPage(map);
        User user = users.get(0);
        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
        String imei = "";
        for (EquipmentLedger e : list) {
            String deviceUUID = (String) redisTemplate.opsForValue().get(helmetb.getNeUsername());
            if (StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                imei = deviceUUID;
            }
        }
        helmetb.setClientId(imei);*/
        String time = jo.getString("time");
        if (StringUtils.isEmpty(time)) {
            helmetb.setCreateTime(LocalDateTime.now());
        } else {
            helmetb.setCreateTime(Dates.toLocalDateTime(new Date(Long.parseLong(time))));
        }
        helmetb.setBattery(jo.getInteger("battery"));
        helmetBatteryService.insert(helmetb);
        noticeToClient(helmetb);
    }

    /*
    * 处理脑波数据
    * 2018-9-20 wenxinyan
    * */
    public void processMindWaverData(String topicName, MqttMessage msg) {
        JSONObject jo = parseMqttMessage(topicName, msg);
        if (jo == null)
            return;

        HelmetMindWaver helmetmv = new HelmetMindWaver();
        helmetmv.setNeUsername(jo.getString("id"));
        helmetmv.setClientId(jo.getString("UUID"));
        /*Map<String, Object> map = new HashMap<>();
        map.put("neUserHel", helmetmv.getNeUsername());
        List<User> users = userService.listByNoPage(map);
        User user = users.get(0);
        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
        String imei = "";
        for (EquipmentLedger e : list) {
            String deviceUUID = (String) redisTemplate.opsForValue().get(helmetmv.getNeUsername());
            if (StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                imei = deviceUUID;
            }
        }
        helmetmv.setClientId(imei);*/
        String time = jo.getString("time");
        if (StringUtils.isEmpty(time)) {
            helmetmv.setCreateTime(LocalDateTime.now());
        } else {
            helmetmv.setCreateTime(Dates.toLocalDateTime(new Date(Long.parseLong(time))));
        }
        helmetmv.setConcert(jo.getInteger("concert"));
        helmetmv.setRelax(jo.getInteger("relax"));
        helmetmv.setRuntime(jo.getInteger("runtime"));
        helmetMindWaverService.insert(helmetmv);// 存储到数据库
        noticeToClient(helmetmv);
    }

    public void noticeToClient(HelmetData helmetData) {
//        logger.debug("数据通知到websocket:" + helmetData.getClientId() + ",cls=" + helmetData.getClass().getSimpleName());
        helmetDataWebSocketService.newDataReceived(helmetData);
    }
}

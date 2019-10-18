package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.websocket.SendHandler;
import javax.websocket.Session;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * websocket方式推送回头状态数据到网页端
 * <p>
 * Created by liuhanc on 2017/12/12.
 */
@Service
public class HelmetDataWebSocketService {
    private Logger logger = LoggerFactory.getLogger(HelmetDataWebSocketService.class);

    @Autowired
    private JsonRedisTemplate jedisTemplate;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;

    private final static String SOCKET_TOKEN_NAME = "socket_token";
    private final static String SOCKET_CLIENTID_NAME = "socket_client";
    private final static String SOCKET_SESSIONID_NAME = "socket_session";

    private String[] dataTypeItemRange = new String[]{"gps", "sensor", "state", "heart", "battery", "mind"};

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, Session> webSocketSessionMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, SendHandler> sessionHandlerMap = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, Map<String, Map<String, Long>>> sessionClientDataTimeMap = new ConcurrentHashMap();//session某个类型数据项上次推送时间

    public String generateToken(Set<String> clientIdSet) {
        String token = UUID.randomUUID().toString();
        jedisTemplate.opsForValue().set(SOCKET_TOKEN_NAME + ":" + token, clientIdSet, 3, TimeUnit.MINUTES);
        return token;
    }

    private void updateSessionClientDataTimeMap(String sessionId, String clientId, String dataType) {
        Map<String, Map<String, Long>> clientMap = sessionClientDataTimeMap.get(sessionId);
        if (clientMap == null) {
            clientMap = new HashMap<>();
            sessionClientDataTimeMap.put(sessionId, clientMap);
        }

        Map<String, Long> dataTypeMap = clientMap.get(clientId);
        if (dataTypeMap == null) {
            dataTypeMap = new HashMap<>();
            clientMap.put(clientId, dataTypeMap);
        }

        dataTypeMap.put(dataType, System.currentTimeMillis());
    }

    private Long getSessionClientDataTimeMap(String sessionId, String clientId, String dataType) {
        Map<String, Map<String, Long>> clientMap = sessionClientDataTimeMap.get(sessionId);
        if (clientMap != null) {
            Map<String, Long> dataTypeMap = clientMap.get(clientId);
            if (dataTypeMap != null) {
                return dataTypeMap.get(dataType);
            }
        }
        return null;
    }

    public boolean verifySession(Session session) {
        String token = getSessionParameter(session, "token");
        if (StringUtils.isEmpty(token)) return false;
        String dataType = getSessionParameter(session, "dataType");
        if (StringUtils.isEmpty(dataType))
            return false;//客户端不需要推送任何数据

        Set<String> clientIdSet = (Set<String>) jedisTemplate.opsForValue().get(SOCKET_TOKEN_NAME + ":" + token);
        if (clientIdSet != null && clientIdSet.size() > 0) {
            logger.debug("校验websocket通过.客户端clientIdSet=" + clientIdSet.toArray().toString());
            jedisTemplate.opsForValue().getOperations().delete(SOCKET_TOKEN_NAME + ":" + token);//移除已验证过的token,防止他人再次使用
            String[] dataTypeArray = dataType.split("_");
            String sessionId = session.getId();
            jedisTemplate.opsForValue().set(SOCKET_SESSIONID_NAME + ":" + sessionId, clientIdSet, 12, TimeUnit.HOURS);//最多12小时
            clientIdSet.stream().forEach(clientId -> {
                Arrays.stream(dataTypeArray).forEach(dataTypeItem -> {
                    if (!StringUtils.isEmpty(dataTypeItem)) {
                        jedisTemplate.opsForSet().add(SOCKET_CLIENTID_NAME + ":" + clientId + ":" + dataTypeItem, sessionId);
//                        logger.debug("头盔：" + clientId + " 的 " + dataTypeItem + " 数据被某个客户端关注.客户端sessionId=" + sessionId);
                        updateSessionClientDataTimeMap(sessionId, clientId, dataTypeItem);
                    }
                });
            });

            webSocketSessionMap.put(sessionId, session);
            sessionHandlerMap.put(sessionId, new HelmetDataSendHandler(sessionId,this));
            logger.debug("缓存客户端sessionId对应的数据。sessionId=" + sessionId + ".webSocketSessionMap=" + webSocketSessionMap.size() + ",sessionHandlerMap=" + sessionHandlerMap.size() + ",sessionClientDataTimeMap=" + sessionClientDataTimeMap.size());
            return true;
        } else {
            logger.info("校验websocket不通过.token对应无头盔clientId");
            return false;
        }
    }

    public void removeSession(String sessionId) {
        webSocketSessionMap.remove(sessionId);
        sessionHandlerMap.remove(sessionId);
        sessionClientDataTimeMap.remove(sessionId);
        logger.debug("移除缓存客户端sessionId对应的数据。sessionId=" + sessionId + ".webSocketSessionMap=" + webSocketSessionMap.size() + ",sessionHandlerMap=" + sessionHandlerMap.size() + ",sessionClientDataTimeMap=" + sessionClientDataTimeMap.size());
        //
        String sessionIdKey = SOCKET_SESSIONID_NAME + ":" + sessionId;
        Set<String> clientIdSet = (Set<String>) jedisTemplate.opsForValue().get(sessionIdKey);
        jedisTemplate.opsForValue().getOperations().delete(sessionIdKey);
        if (!CollectionUtils.isEmpty(clientIdSet)) {
            clientIdSet.stream().forEach(clientId -> {
                Stream.of(dataTypeItemRange).forEach(dataTypeItem -> {
//                    logger.debug("头盔："+clientId+"的"+dataTypeItem+"数据被取消关注.sessionId="+sessionId);
                    jedisTemplate.opsForSet().remove(SOCKET_CLIENTID_NAME + ":" + clientId + ":" + dataTypeItem, sessionId);
                });
            });
        }
    }

    private String getSessionParameter(Session session, String paramName) {
        Map<String, List<String>> map = session.getRequestParameterMap();
        List<String> values = map.get(paramName);
        return CollectionUtils.isEmpty(values) ? null : values.get(0);
    }

    public void newDataReceived(HelmetData helmetData) {
        if (webSocketSessionMap == null || webSocketSessionMap.size() == 0) {
            return;
        }
        try {
            String dataType = null;
            if (helmetData instanceof HelmetSensor)
                dataType = "sensor";
            if (helmetData instanceof HelmetGps)
                dataType = "gps";
            if (helmetData instanceof HelmetState)
                dataType = "state";
            if (helmetData instanceof HelmetHeartBeat)
                dataType = "heart";
            if (helmetData instanceof HelmetBattery)
                dataType = "battery";
            if (helmetData instanceof HelmetMindWaver)
                dataType = "mind";
            helmetData.setDataType(dataType);

            sendDataToClient(helmetData);
        } catch (Throwable e) {
            logger.error("新数据到达发送给客户端时异常." + e.getMessage(), e);
        }
    }

    protected void sendDataToClient(HelmetData helmetData) {
        String clientId = helmetData.getClientId();
        String dataType = helmetData.getDataType();
        //得到该数据将发往的客户端列表
        String redisKey = SOCKET_CLIENTID_NAME + ":" + clientId + ":" + dataType;
        Set<Object> sessionIdList = jedisTemplate.opsForSet().members(redisKey);
        if (CollectionUtils.isEmpty(sessionIdList)) {
//            logger.debug("头盔传感器数据无客户端关注，丢弃.clientId="+clientId+"，dataType="+dataType);
            return;
        }

        Set<String> lostSessionIdSet = new HashSet<>();
        sessionIdList.stream().forEach(sessionId -> {
            Session session = webSocketSessionMap.get(sessionId);
            if (session == null) {
                //session已丢失
                lostSessionIdSet.add(sessionId.toString());
            } else if (!session.isOpen()) {
                webSocketSessionMap.remove(sessionId);
                sessionHandlerMap.remove(sessionId);
                sessionClientDataTimeMap.remove(sessionId);
                lostSessionIdSet.add(sessionId.toString());
            } else {
                sendText(session, helmetData);
            }
        });

        //缓存更新如有必要
        if (lostSessionIdSet.size() > 0) {
            logger.debug(redisKey + "中包含无效的sessionId，移除:" + lostSessionIdSet.toArray());
            jedisTemplate.opsForSet().remove(redisKey, lostSessionIdSet.toArray());
        }
    }

    //添加synchronized 否则可能报错 The remote endpoint was in state [TEXT_FULL_WRITING] which is an invalid state for called method
    synchronized public void sendText(Session session, HelmetData helmetData) {
        String sessionId = session.getId();
        String clientId = helmetData.getClientId();
        String dataTypeItem = helmetData.getDataType();
        Long lastSendTime = getSessionClientDataTimeMap(sessionId, clientId, dataTypeItem);

        long intervalTime = "gps".equals(helmetData.getDataType()) ? 15000 : 1000; // 定位数据15秒发送1次,其他数据1秒发送1次以降低频率
        long now = System.currentTimeMillis();
        if (lastSendTime == null || now - lastSendTime >= intervalTime) {
            //超过间隔时间才发送，避免太频繁
            /**
             * update by xiayuan 2018/10/10
             */
            updateSessionClientDataTimeMap(sessionId, clientId, dataTypeItem);
            EquipmentLedger helmet = equipmentService.selectByUUID(helmetData.getClientId());
            User user = userService.selectById(helmet.getUserId());
//            helmetData.setNeUsername(helmet == null ? "头盔账号丢失" : user.getNeUserHel());//理论上不可能发生
            helmetData.setNeUsername(helmet == null ? "头盔账号丢失" : helmet.getDeviceNumber()+"-"+user.getName()+"-"+user.getNeUserHel());//理论上不可能发生
            String json = JSON.toJSONString(helmetData);
//            logger.debug("发送数据到客户端." + sessionId + ".json=" + json);
            try {
                SendHandler handler = sessionHandlerMap.get(sessionId);
                session.getAsyncRemote().sendText(json, handler);
            } catch (Exception e) {
                logger.error("发送websocket消息给客户端发生异常.", e);
            }
        } else {
//            logger.debug("准备发送数据到客户端."+sessionId+",因时间不足暂时不发送.dataType="+helmetData.getDataType()+",clientId="+helmetData.getClientId());
        }
    }
}

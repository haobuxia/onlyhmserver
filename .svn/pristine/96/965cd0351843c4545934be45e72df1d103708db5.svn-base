package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.HelmetOnlineStatusDao;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetHeartBeat;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatistics;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatus;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.TagService;
import com.tianyi.helmet.server.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计头盔在线时长
 * Created by tianxujin on 2019/3/14.
 */
@Service
public class HelmetOnlineStatusService {
    @Autowired
    private HelmetOnlineStatusDao helmetOnlineStatusDao;
    @Autowired
    private RedisTemplate jedisTemplate;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;

    private final static String HELMET_ONLINE_SET = "helmet_online_set";

    public void insert(HelmetOnlineStatus helmetOnlineStatus) {
        helmetOnlineStatusDao.insert(helmetOnlineStatus);
    }

    // 加入redis上线列表
    @Transactional
    public void handleOnline(HelmetHeartBeat helmethb) {
//        Boolean isOnline = jedisTemplate.opsForHash().hasKey(HELMET_ONLINE_SET, helmethb.getClientId());
        Map<String, Object> helmetOnlineInfo = (Map<String, Object>) jedisTemplate.opsForHash().get(HELMET_ONLINE_SET, helmethb.getClientId());
        LocalDateTime currentTime = LocalDateTime.now();
        if (helmetOnlineInfo!=null) {
            helmetOnlineInfo.put("currentTime", currentTime.toString());
            //zhouwei 20190846 增加用户id信息，判断id与缓存是否一致，如果不一致更新在线信息
            //tianxj 20190715 用户不一致则下线之前用户，上线当前用户
            if(!helmethb.getUserId().equals(helmetOnlineInfo.get("userid"))){
                helmetOnlineInfo.put("userid", helmethb.getUserId());
                //头盔登陆,注销或切换用户，更新用户信息
                /*Map<String, Object> updateInfo = new HashMap();
                updateInfo.put("userId", helmethb.getUserId());
                updateInfo.put("clientId", helmethb.getClientId());
                if(helmethb.getUserId()!=-1) {
                    //查询用户信息
                    User user = userService.selectById(helmethb.getUserId());
                    updateInfo.put("neUsername", user.getNeUserHel());
                    updateInfo.put("userName", user.getName());
                }else{
                    updateInfo.put("neUsername", null);
                    updateInfo.put("userName", null);
                }
                update(updateInfo);*/
                // 下线之前用户
                setOffLine(helmethb.getClientId(), currentTime);
                // 上线新用户
                insert(helmethb, currentTime);
            }
//            jedisTemplate.opsForHash().put(HELMET_ONLINE_SET, helmethb.getClientId(), currentTime);
            jedisTemplate.opsForHash().put(HELMET_ONLINE_SET, helmethb.getClientId(), helmetOnlineInfo);
        } else {// 设置到上线列表
            helmetOnlineInfo = new HashMap<>();
            helmetOnlineInfo.put("userid", helmethb.getUserId());
            helmetOnlineInfo.put("currentTime", currentTime);
//            jedisTemplate.opsForHash().put(HELMET_ONLINE_SET, helmethb.getClientId(), currentTime);
            jedisTemplate.opsForHash().put(HELMET_ONLINE_SET, helmethb.getClientId(), helmetOnlineInfo);
            // 存储上线节点
            insert(helmethb, currentTime);
        }
    }

    private void insert(HelmetHeartBeat helmethb, LocalDateTime currentTime) {
        HelmetOnlineStatus helmetOnlineStatus = new HelmetOnlineStatus();
        helmetOnlineStatus.setId(helmethb.getId());
        helmetOnlineStatus.setClientId(helmethb.getClientId());
        helmetOnlineStatus.setOnlineTime(currentTime);
//            helmetOnlineStatus.setNeUsername(helmethb.getNeUsername());
        helmetOnlineStatus.setStatus(0);
//            EquipmentLedger equipmentLedger=equipmentService.selectByUUID(helmethb.getClientId());
        User user = userService.selectById(helmethb.getUserId());
        if(user!=null){
            helmetOnlineStatus.setUserId(user.getId());
            helmetOnlineStatus.setUserName(user.getName());
            helmetOnlineStatus.setNeUsername(user.getNeUserHel());
        } else {
            helmetOnlineStatus.setUserId(-1);
            helmetOnlineStatus.setUserName("未知用户");
            helmetOnlineStatus.setNeUsername("未知用户");
        }
        helmetOnlineStatusDao.insert(helmetOnlineStatus);
    }

    private void update(Map<String, Object> updateInfo) {
        //update helmetonlinestatus set neuser=?,userid=?,username=? where status=0 and cliendid=?
        helmetOnlineStatusDao.updateByClientId(updateInfo);
    }

    @Transactional
    public void handleOffline() {
        LocalDateTime currentTime = LocalDateTime.now();
        Cursor<Map.Entry<Object, Object>> curosr = jedisTemplate.opsForHash().scan(HELMET_ONLINE_SET, ScanOptions.NONE);
        while(curosr.hasNext()){
            Map.Entry<Object, Object> entry = curosr.next();
//            System.out.println(entry.getKey()+":"+entry.getValue());
            //zhouwei 20190846 在线状态缓存类型修改成Map
//            LocalDateTime lastTime = LocalDateTime.parse(entry.getValue().toString());
            LocalDateTime lastTime = LocalDateTime.parse((((Map<String, Object>) entry.getValue()).get("currentTime").toString()));
            lastTime = lastTime.plusMinutes(3);
            if(lastTime.isBefore(currentTime)){// 3分钟之前下线
                setOffLine(entry.getKey(), currentTime);
                // 设置下线
                jedisTemplate.opsForHash().delete(HELMET_ONLINE_SET, entry.getKey());//移除
            }
        }
    }

    private void setOffLine(Object clientId, LocalDateTime currentTime) {
        Map params = new HashMap();
        params.put("clientId", clientId);
        List<HelmetOnlineStatus> list = helmetOnlineStatusDao.selectByClientId(params);
        if(list.size() > 0 ) {
            HelmetOnlineStatus hs = list.get(0);
            hs.setOfflineTime(currentTime);
            hs.setStatus(1);
            helmetOnlineStatusDao.update(hs);
        }
    }

    public List<HelmetOnlineStatistics> statisticTimesByDay(Map<String, Object> params) {
        List<Map<String, Object>> list = helmetOnlineStatusDao.getLoginTimesByDay(params);
        List<Map<String, Object>> list1 = helmetOnlineStatusDao.getShootTimesByDay(params);
        List<HelmetOnlineStatistics> result = new ArrayList<HelmetOnlineStatistics>();

        for (Map<String, Object> baseMap : list) {
            HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
            for (Map<String, Object> baseMap1 : list1) {
                if(Integer.parseInt(baseMap.get("userId").toString()) == Integer.parseInt(baseMap1.get("userId").toString())
                        && baseMap.get("clientId").equals(baseMap1.get("clientId"))
                        && baseMap.get("loginDate").equals(baseMap1.get("loginDate"))) {
                    stat.setShootSeconds(Long.parseLong(baseMap1.get("loginSeconds").toString()));
                    stat.setShootNum(Integer.parseInt(baseMap1.get("loginNum").toString()));
                }
            }
//            stat.setUserId(Integer.parseInt(baseMap.get("userId").toString()));
//            User user = userService.selectById(Integer.parseInt(baseMap.get("userId").toString()));
//            stat.setUserName(user.getName());
//            EquipmentLedger equipmentLedger=equipmentService.selectByUUID(baseMap.get("clientId").toString());
//            stat.setDeviceNumber(equipmentLedger.getDeviceNumber());
//            stat.setClientId(baseMap.get("clientId").toString());
            stat.setLoginDate(LocalDate.parse(baseMap.get("loginDate").toString()));
            stat.setLoginNum(Integer.parseInt(baseMap.get("loginNum").toString()));
            stat.setLoginSeconds(Long.parseLong(baseMap.get("loginSeconds").toString()));
            stat.setLoginLongStr(Dates.format(stat.getLoginDate(),"yyyy-MM-dd"));
            result.add(stat);
        }
        return result;
    }

    public List<HelmetOnlineStatistics> getStatisticList(Map<String, Object> params) {
        List<Map<String, Object>> list = helmetOnlineStatusDao.getLoginTimes(params);
        List<Map<String, Object>> list1 = helmetOnlineStatusDao.getShootTimes(params);
        List<HelmetOnlineStatistics> result = new ArrayList<HelmetOnlineStatistics>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        for (Map<String, Object> baseMap : list) {
            HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
            for (Map<String, Object> baseMap1 : list1) {
                if(Integer.parseInt(baseMap.get("userId").toString()) == Integer.parseInt(baseMap1.get("userId").toString()) && baseMap.get("clientId").equals(baseMap1.get("clientId"))) {
                    stat.setShootSeconds(Long.parseLong(baseMap1.get("loginSeconds").toString()));
                    stat.setShootNum(Integer.parseInt(baseMap1.get("loginNum").toString()));
                }
            }
            stat.setUserId(Integer.parseInt(baseMap.get("userId").toString()));
            User user = userService.selectById(Integer.parseInt(baseMap.get("userId").toString()));
            if(user!=null){
                stat.setUserName(user.getName());
            } else {
                stat.setUserName("--");
            }
            EquipmentLedger equipmentLedger=equipmentService.selectByUUID(baseMap.get("clientId").toString());
            if(equipmentLedger == null) {
                continue;
            }
            stat.setDeviceNumber(equipmentLedger.getDeviceNumber());
            stat.setClientId(baseMap.get("clientId").toString());
            stat.setLoginDate(LocalDate.parse(baseMap.get("loginDate").toString(), formatter));
            stat.setLoginNum(Integer.parseInt(baseMap.get("loginNum").toString()));
            stat.setLoginSeconds(Long.parseLong(baseMap.get("loginSeconds").toString()));
            stat.setLoginLongStr(Dates.formatSeconds((int) stat.getLoginSeconds()));
            result.add(stat);
        }
        return result;
    }

    public List<HelmetOnlineStatistics> getStatisticListByArea(Map<String, Object> params) {
        List<Map<String, Object>> list = helmetOnlineStatusDao.getShootTimesByArea(params);
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<HelmetOnlineStatistics> result = new ArrayList<HelmetOnlineStatistics>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        for (Map<String, Object> baseMap : list) {
            HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
            stat.setDepartment(baseMap.get("department").toString());
            stat.setUserId(Integer.parseInt(baseMap.get("userId").toString()));
            User user = userService.selectById(Integer.parseInt(baseMap.get("userId").toString()));
            if(user!=null){
                stat.setUserName(user.getName());
            } else {
                stat.setUserName("--");
            }
            stat.setLoginDate(LocalDate.parse(baseMap.get("loginDate").toString(), formatter));
            stat.setLoginNum(Integer.parseInt(baseMap.get("loginNum").toString()));
            stat.setLoginSeconds(Long.parseLong(baseMap.get("loginSeconds").toString()));
            stat.setLoginLongStr(Dates.formatSeconds((int) stat.getLoginSeconds()));
            params.put("userId", baseMap.get("userId"));
            list1 = helmetOnlineStatusDao.getShootTimeListByUser(params);
            int count = 0;
            LocalDateTime endTime = LocalDateTime.now();
            for (Map<String, Object> baseMap1 : list1) { // 设置工单数量，连续两小时算一个工单。
                LocalDateTime beginTime = LocalDateTime.parse(baseMap1.get("loginDate").toString(), formatter);// 拍摄时间
                if(count==0){
                    endTime = beginTime.plusHours(2);
                    count++;
                    continue;
                }
                if(beginTime.isAfter(endTime)) {
                    count++;
                    endTime = beginTime.plusHours(2);
                }
            }
            stat.setWorkNum(count);
            result.add(stat);
        }
        return result;
    }

    public List<HelmetOnlineStatistics> getStatisticListByAreaUser(Map<String, Object> params) {
        List<Map<String, Object>> list = helmetOnlineStatusDao.getShootTimeListByUserByDay(params);
        List<HelmetOnlineStatistics> result = new ArrayList<HelmetOnlineStatistics>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startTime = LocalDate.parse(params.get("startTime").toString(), formatter1);// 开始时间
        LocalDate endTime = LocalDate.parse(params.get("endTime").toString(), formatter1);// 结束时间
        User user = userService.selectById(Integer.parseInt(params.get("userId").toString()));
        String userName = "--";
        if(user!=null){
            userName = user.getName();
        }
        for (Map<String, Object> baseMap : list) {
            LocalDate actrulDate = LocalDate.parse(baseMap.get("loginDate").toString(),formatter2);
            while(startTime.isBefore(actrulDate)) {
                HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
                stat.setUserName(userName);
                stat.setLoginDate(startTime);
                stat.setLoginNum(0);
                stat.setLoginSeconds(0);
                result.add(stat);
                startTime = startTime.plusDays(1);
            }
            if(startTime.isEqual(actrulDate)) {
                HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
                stat.setUserName(userName);
                stat.setLoginDate(startTime);
                stat.setLoginNum(Integer.parseInt(baseMap.get("loginNum").toString()));
                stat.setLoginSeconds(Long.parseLong(baseMap.get("loginSeconds").toString()));
                stat.setLoginLongStr(Dates.formatSeconds((int) stat.getLoginSeconds()));

                String startTimeStr = Dates.format(startTime, "yyyy-MM-dd")+" 00:00:00";
                params.put("startTime", startTimeStr); //LocalDateTime.parse(startTimeStr, formatter1)
                String endTimeStr = Dates.format(startTime, "yyyy-MM-dd")+" 23:59:59";
                params.put("endTime", endTimeStr); // 设置23.59.59
                params.put("userId", baseMap.get("userId"));
                List<Map<String, Object>> list1 = helmetOnlineStatusDao.getShootTimeListByUser(params);
                int count = 0;
                LocalDateTime endTime1 = LocalDateTime.now();
                for (Map<String, Object> baseMap1 : list1) { // 设置工单数量，连续两小时算一个工单。
                    LocalDateTime beginTime = LocalDateTime.parse(baseMap1.get("loginDate").toString(), formatter);// 拍摄时间
                    if(count==0){
                        endTime1 = beginTime.plusHours(2);
                        count++;
                        continue;
                    }
                    if(beginTime.isAfter(endTime1)) {
                        count++;
                        endTime1 = beginTime.plusHours(2);
                    }
                }
                stat.setWorkNum(count);
                result.add(stat);
                startTime = startTime.plusDays(1);
            }
        }
        while(startTime.isBefore(endTime)) {
            HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
            stat.setUserName(userName);
            stat.setLoginDate(startTime);
            stat.setLoginNum(0);
            stat.setLoginSeconds(0);
            result.add(stat);
            startTime = startTime.plusDays(1);
        }
        if(startTime.isEqual(endTime)) {
            HelmetOnlineStatistics stat = new HelmetOnlineStatistics();
            stat.setUserName(userName);
            stat.setLoginDate(startTime);
            stat.setLoginNum(0);
            stat.setLoginSeconds(0);
            result.add(stat);
        }
        return result;
    }

    public List<Map<String, Object>> getShootNumListByTag(Map<String, Object> params) {
        List<Map<String, Object>> list = helmetOnlineStatusDao.getShootNumListByTag(params);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("groupId", 1);
        List<Tag> tagList = tagService.listBy(param);
        int tempUserId = -99;
        for(Map<String, Object> map : list) {
            int userId = Integer.parseInt(map.get("userId").toString());
            if(tempUserId != userId) {
                tempUserId = userId;
                map.put("tag_"+map.get("tagid").toString(),map.get("shootnum"));
                map.put("totalNum", map.get("shootnum"));
                result.add(map);
            } else {
                Map<String, Object> map1 = result.get(result.size()-1);
                if(map1!=null) {
                    map1.put("tag_"+map.get("tagid").toString(),map.get("shootnum"));
                    map1.put("totalNum", Integer.parseInt(map1.get("totalNum").toString())+Integer.parseInt(map.get("shootnum").toString()));
                }
            }
        }
        return result;
    }

    public List<HelmetOnlineStatus> getOnlineUser(){
        return helmetOnlineStatusDao.getOnlineUser();
    }

    public int countBy(Map<String, Object> param) {
        return helmetOnlineStatusDao.countBy(param);
    }
}

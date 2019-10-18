//package com.tianyi.helmet.server.service.client;
//
//import com.tianyi.helmet.server.dao.client.HelmetDao;
//import com.tianyi.helmet.server.entity.client.Customer;
//import com.tianyi.helmet.server.entity.client.Helmet;
//import com.tianyi.helmet.server.entity.client.NeteaseUser;
//import com.tianyi.helmet.server.service.kmx.MetaDataInitService;
//import com.tianyi.helmet.server.service.support.CacheKeyConstants;
//import com.tianyi.helmet.server.service.support.ConfigService;
//import com.tianyi.helmet.server.util.RelationUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
///**
// * 头盔设备服务
// * <p>
// * Created by liuhanc on 2017/10/12.
// */
//@Service
//public class HelmetService {
//
//    @Autowired
//    private HelmetDao helmetDao;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//    @Autowired
//    private ConfigService configService;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private NeteaseUserService neteaseUserService;
//    @Autowired
//    private MetaDataInitService metaDataInitService;
//
//    private Map<String,Long> willTimeMapCache = new HashMap<>();
//    private Map<String,Long> gpsTimeMapCache = new HashMap<>();
//
//
//    private Logger logger = LoggerFactory.getLogger(HelmetService.class);
//
//    public void addHelmet(Helmet helmet) {
//        helmetDao.insert(helmet);
//        if (!StringUtils.isEmpty(helmet.getImei())) {
//            metaDataInitService.initDeviceHelmetDevice(helmet.getImei());
//        }
//    }
//
//    public Helmet getHelmetById(int id) {
//        return helmetDao.selectById(id);
//    }
//
//    public Helmet getHelmetByNeUserId(int neUserId) {
//        return helmetDao.selectByNeUserId(neUserId);
//    }
//
//    public Helmet getHelmetByTianYuanUserId(int tianYuanUserId) {
//        return helmetDao.selectByTianyiUserId(tianYuanUserId);
//    }
//
//    public Helmet getHelmetByTianyiUserId(int tianyiUserId) {
//        return helmetDao.selectByTianyiUserId(tianyiUserId);
//    }
//
//    @Cacheable(value = CacheKeyConstants.HELMET_IMEI_BY_NEUSERNAME, key = "#neUsername.toLowerCase()", unless = "#result == null")
//    public String getHelmetImeiByNeUsername(String neUsername) {
//        Helmet helmet = helmetDao.selectByNeUsername(neUsername);
//        if (helmet != null)
//            return helmet.getImei();
//        return null;
//    }
//
//    @Cacheable(value = CacheKeyConstants.HELMET_BY_IMEI, key = "#imei.toLowerCase()", unless = "#result == null")
//    public Helmet getHelmetByImei(String imei) {
//        return helmetDao.selectByImei(imei);
//    }
//
//    @CacheEvict(value = CacheKeyConstants.HELMET_BY_IMEI, key = "#helmet.imei.toLowerCase()")
//    public int updateHelmetById(Helmet helmet) {
//        return helmetDao.updateById(helmet);
//    }
//
//    @Transactional
//    @CacheEvict(value = CacheKeyConstants.HELMET_BY_IMEI, key = "#helmet.imei.toLowerCase()")
//    public int deleteHelmet(Helmet helmet) {
//        // 绑定的网易账号如果存在还需要处理
//        Integer neUserId = helmet.getNeUserId();
//        if (neUserId != null && neUserId > 0) {
//            NeteaseUser neUser = neteaseUserService.selectById(neUserId);
//            if (neUser != null) {
//                neUser.setUserType(4);//设置该网易账号无人使用
//                neteaseUserService.updateById(neUser);
//            }
//        }
//        return helmetDao.deleteById(helmet.getId());
//    }
//
//    public List<Helmet> selectBy(Map<String, Object> map) {
//        return helmetDao.selectBy(map);
//    }
//
//    public int countBy(Map<String, Object> map) {
//        return helmetDao.countBy(map);
//    }
//
//    /**
//     * 查询列表
//     * @return
//     */
//    public List<Helmet> selectByType(Boolean isActive) {
//        Map<String, Object> map = new HashMap<>();
//        if (isActive != null) {
//            if (isActive) {
//                map.put("notSaleState", 0);//查询已初始化的
//            } else {
//                map.put("saleState", 0);//查询未初始化的
//            }
//        }
//        return helmetDao.selectBy(map);
//    }
//
//    public void fullfilCustomerNeUser(List<Helmet> helmetList) {
//        RelationUtils.fullfilListRelateProperty(helmetList, Helmet::getCustomerId, customerService::selectByIdList, Customer::getId, Customer::getDisplayName, Helmet::setCustomerName);
//    }
//
//    public List<Helmet> selectByCustomerId(int customerId) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("customerId", customerId);
//        return helmetDao.selectBy(map);
//    }
//
//    public List<Helmet> selectBySalerId(int salerId) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("salerId", salerId);
//        return helmetDao.selectBy(map);
//    }
//
//    /**
//     * 缓存客户端最后发送心跳的时间。可用来判断客户端在线状态
//     *
//     * @param imei
//     * @return
//     */
//    public void cacheWillTime(String imei) {
//        Long time = System.currentTimeMillis();
//        Long previousTime = willTimeMapCache.putIfAbsent(imei,time);
//        if(previousTime == null || time - previousTime > 10*1000){
//            //10秒更新一次redis
//            redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_ONLINE_BY_IMEI + ":" + imei, time.toString(), 120, TimeUnit.SECONDS);
//        }
//    }
//
//    /**
//     * 存储头盔的gps模块定位状态.状态最多保存240
//     *
//     * @param imei
//     * @param stateOk true表示定位成功,false表示定位失败。定位失败后gps数据以网络定位的数据为准.
//     */
//    public void cacheGpsState(String imei, boolean stateOk) {
//        Long time = System.currentTimeMillis();
//        Long previousTime = gpsTimeMapCache.putIfAbsent(imei,time);
//        if(previousTime == null || time - previousTime > 10*1000){
//            //10秒更新一次redis
//            redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_GPS_STATE_BY_IMEI + ":" + imei, (stateOk ? "1" : "0"), 240, TimeUnit.MINUTES);
//        }
//    }
//
//    /**
//     * 头盔的gps模块定位是否成功
//     *
//     * @param imei
//     * @return
//     */
//    public boolean isGpsStateOk(String imei) {
//        String state = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_GPS_STATE_BY_IMEI + ":" + imei);
//        return "1".equals(state);
//    }
//
//    /**
//     * 某个客户端是否在线
//     *
//     * @param imei
//     * @return
//     */
//    public boolean isOnLine(String imei) {
//        String time = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_ONLINE_BY_IMEI + ":" + imei);
//        if (time == null) return false;
//        if (System.currentTimeMillis() - Long.parseLong(time) <= 1000l * configService.getHelmetOnlineIntervalSeconds()) {
//            return true;
//        }
//        return false;
//    }
//
//
//    public Set<String> getEffectHelmetIdSet() {
//        return selectByType(true).stream().map(helmet -> helmet.getImei()).collect(Collectors.toSet());
//    }
//}

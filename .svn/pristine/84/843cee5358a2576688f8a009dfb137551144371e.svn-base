//package com.tianyi.helmet.server.service.client;
//
//import com.tianyi.helmet.server.dao.client.TianyiUserDao;
//import com.tianyi.helmet.server.entity.client.TianyiUser;
//import com.tianyi.helmet.server.entity.device.EquipmentLedger;
//import com.tianyi.helmet.server.service.device.EquipmentService;
//import com.tianyi.helmet.server.service.support.CacheKeyConstants;
//import com.tianyi.helmet.server.service.support.ConfigService;
//import com.tianyi.helmet.server.util.MyConstants;
//import com.tianyi.helmet.server.util.RelationUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//import java.util.function.BiConsumer;
//import java.util.function.Function;
//
///**
// * 田一用户信息服务
// * <p>
// * Created by liuhanc on 2017/11/2.
// */
//@Service
//public class TianyiUserService {
//    @Autowired
//    TianyiUserDao tianyiUserDao;
//    @Autowired
//    private EquipmentService equipmentService;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//    @Autowired
//    private ConfigService configService;
//
//    private Logger logger = LoggerFactory.getLogger(TianyiUserService.class);
//
//    public void insert(TianyiUser user) {
//        tianyiUserDao.insert(user);
//    }
//
//    public List<TianyiUser> listByIdList(List<Integer> userIdList) {
//        Map<String, Object> params = new HashMap(1);
//        params.put("idList", userIdList);
//        List<TianyiUser> userList = tianyiUserDao.listBy(params);
//        return userList;
//    }
//
//    /**
//     * 填充1个列表中的用户id对应的用户名
//     *
//     * @param dataList
//     * @param origUserIdGetter
//     * @param origUserNameSetter
//     * @param <T>
//     */
//    public <T> void fullfilUserName(List<T> dataList, Function<T, Integer> origUserIdGetter, BiConsumer<T, String> origUserNameSetter) {
//        RelationUtils.fullfilListRelateProperty(dataList, origUserIdGetter, this::listByIdList, TianyiUser::getId, TianyiUser::getDisplayName, origUserNameSetter);
//    }
//
//
//    public TianyiUser selectByUsername(String username) {
//        return tianyiUserDao.selectByUsername(username);
//    }
//
//    public TianyiUser selectByNeUsername(String neUsername) {
//        return tianyiUserDao.selectByNeUsername(neUsername);
//    }
//
//    public TianyiUser selectByMobile(String mobile) {
//        return tianyiUserDao.selectByMobile(mobile);
//    }
//
//    public TianyiUser selectByName(String name) {
//        Map<String, Object> params = new HashMap<>(1);
//        params.put("name", name);
//        List<TianyiUser> tianyiUserList = listBy(params);
//        return tianyiUserList.size() == 0 ? null : tianyiUserList.get(0);
//    }
//
//    public List<TianyiUser> searchByNameLike(String name) {
//        Map<String, Object> params = new HashMap<>(1);
//        params.put("nameLike", name);
//        return listBy(params);
//    }
//
//    public List<TianyiUser> listBy(Map<String, Object> params) {
//        List<TianyiUser> userList = tianyiUserDao.listBy(params);
//        userList.stream().forEach(user -> {
//            /**
//             * update by xiayuan 2018/10/9
//             * todo 一个用户绑定多个头盔如何处理
//             */
//            List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
//            EquipmentLedger helmet = new EquipmentLedger();
//            for (EquipmentLedger e : list) {
//                String state = (String) redisTemplate.opsForValue().get(e.getDeviceUUID());
//                if (MyConstants.DEVICE_STATE_ON.equals(state)) {
//                    helmet = e;
//                }
//            }
//            user.setHelmet(helmet);
//        });
//        return userList;
//    }
//
//    public int countBy(Map<String, Object> params) {
//        return tianyiUserDao.countBy(params);
//    }
//
//
//    @Cacheable(value = CacheKeyConstants.TIANYI_USER_BY_ID, key = "#id.toString()", unless = "#result == null")
//    public TianyiUser selectById(int id) {
//        return tianyiUserDao.selectById(id);
//    }
//
//    @CacheEvict(value = CacheKeyConstants.TIANYI_USER_BY_ID, key = "#d.toString()")
//    public int deleteById(int id) {
//        return tianyiUserDao.deleteById(id);
//    }
//
//    @CacheEvict(value = CacheKeyConstants.TIANYI_USER_BY_ID, key = "#id.toString()")
//    public int updateMobileById(String mobile, int id) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("mobile", mobile);
//        map.put("id", id);
//        int cnt = tianyiUserDao.updateMobileById(map);
//        return cnt;
//    }
//
//    @CacheEvict(value = CacheKeyConstants.TIANYI_USER_BY_ID, key = "#user.id.toString()")
//    public int updateById(TianyiUser user) {
//        int cnt = tianyiUserDao.updateById(user);
//        return cnt;
//    }
//
//    public void cacheWillTime(int userId, Long time) {
//        redisTemplate.opsForValue().set(CacheKeyConstants.MOBILE_ONLINE_BY_USERID + ":" + userId, time.toString(), 120, TimeUnit.SECONDS);
//    }
//
//    public boolean isOnLine(int userId) {
//        String time = (String) redisTemplate.opsForValue().get(CacheKeyConstants.MOBILE_ONLINE_BY_USERID + ":" + userId);
//        if (time == null) return false;
//        if (System.currentTimeMillis() - Long.parseLong(time) <= 1000l * configService.getHelmetOnlineIntervalSeconds()) {
//            return true;
//        }
//        return false;
//    }
//
//}

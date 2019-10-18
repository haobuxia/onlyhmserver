package com.tianyi.helmet.server.service.device.impl;

import com.tianyi.helmet.server.dao.client.CompanyDao;
import com.tianyi.helmet.server.dao.client.UserDao;
import com.tianyi.helmet.server.dao.device.EquipmentDao;
import com.tianyi.helmet.server.dao.dictionary.CategoryDao;
import com.tianyi.helmet.server.dao.dictionary.VersionDao;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.dictionary.BatchService;
import com.tianyi.helmet.server.service.dictionary.CategoryService;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import com.tianyi.helmet.server.service.kmx.MetaDataInitService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.util.MyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ConfigService configService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private MetaDataInitService metaDataInitService;

    private Map<String, Long> gpsTimeMapCache = new HashMap<>();
    private Map<String, Long> willTimeMapCache = new HashMap<>();

    @Override
    @Transactional
    public int insert(EquipmentLedger equipmentLedger) {
        EquipmentLedger deviceNew = new EquipmentLedger();
        BeanUtils.copyProperties(equipmentLedger, deviceNew);
        logger.debug("service中insert调用");
        String deviceUUID = equipmentLedger.getDeviceUUID();
        EquipmentLedger equip = selectByUUID(deviceUUID);
        if (equip != null) {
            return MyConstants.DEVICE_FIND_DUPLICATE;
        }
        String now = Dates.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        //存入设备历史表
        EquipmentLedger deviceNoUser = new EquipmentLedger();
        BeanUtils.copyProperties(equipmentLedger, deviceNoUser);
        deviceNoUser.setUserId(-1);
        deviceNoUser.setAffiliationId(-1);
        deviceNoUser.setUpdateTime("");
        deviceNoUser.setCreateTime(now);
        deviceNoUser.setFlag(2);
        deviceNoUser.setStatus(6);
        deviceNoUser.setStartTime(now);
        insertHistory(deviceNoUser);
        //设置入库标记
        equipmentLedger.setFlag(2);
        equipmentLedger.setStatus(1);

        if(equipmentLedger.getAffiliationId() == null) {
            Integer userId = equipmentLedger.getUserId();
            User user = userService.selectById(userId);
            Integer affiliationId = user.getOrganisation();
            equipmentLedger.setAffiliationId(affiliationId);
        }
        equipmentLedger.setUpdateTime(now);
        equipmentLedger.setCreateTime(now);
        equipmentLedger.setStartTime(now);
        equipmentLedger.setUserId(-1);// 20190424扫码入库之后不绑定使用人
        equipmentLedger.setStatus(6);
        deviceNoUser.setEndTime(now);
        updateHistory(deviceNoUser);
        insertHistory(equipmentLedger);
        int r2 = equipmentDao.insert(equipmentLedger);
//        //如果直接选择了其他单位非田一科技
//        if (deviceNew.getAffiliationId() != affiliationId) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("deviceUUID", deviceNew.getDeviceUUID());
//            map.put("affiliationId", deviceNew.getAffiliationId());
//            updateUserId(map);
//            deviceNew.setStartTime(now);
//            deviceNew.setCreateTime(equipmentLedger.getCreateTime());
//            equipmentLedger.setEndTime(now);
//            updateHistory(equipmentLedger);
//            equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_DELIVERY);
//            insertHistory(deviceNew);
//        }
        if (!(r2 > 0)) {
            return MyConstants.DEVICE_FAIL;
        }
        // 新增一个头盔需要在kmx注册信息
        metaDataInitService.initDeviceHelmetDevice(deviceUUID);
        logger.debug("service中insert调用结束");
        return r2;
    }

    public int insertHistory(EquipmentLedger equipmentLedger) {
        return equipmentDao.insertHistory(equipmentLedger);
    }

    @Override
    @Transactional
    public List<EquipmentLedger> select(Map<String, Object> map) {
        logger.debug("service中select调用");
        map.remove("userId");
        List<EquipmentLedger> list = equipmentDao.select(map);
        list = setNames(list);
        logger.debug("service中select调用结束");
        return list;
    }

    @Override
    public List<EquipmentLedger> selectHistory(Map<String, Object> map) {
        logger.debug("service中selectHistory调用");
        //登录用户所属公司的设备列表
        List<EquipmentLedger> list = equipmentDao.selectHistory(map);
        list = setNames(list);
        logger.debug("service中selectHistory调用结束");
        return list;
    }

    @Override
    public List<EquipmentLedger> selectAll(Map<String, Object> map) {
        logger.debug("service中selectAll调用");
        //田一管理员登录可以查看所有已经出库的设备
        List<EquipmentLedger> list = equipmentDao.select(map);
        if (list != null) {
            list = setNames(list);
        }
        logger.debug("service中selectAll调用结束");
        return list;
    }

    @Override
    public List<EquipmentLedger> selectAllHistory(Map<String, Object> map) {
        logger.debug("service中selectAllHistory调用");
        //田一管理员登录可以查看所有已经出库的设备
        List<EquipmentLedger> list = equipmentDao.selectHistory(map);
        list = setNames(list);
        logger.debug("service中selectAllHistory调用结束");
        return list;
    }

    @Override
    public int countBy(Map<String, Object> map) {
        return equipmentDao.countBy(map);
    }

    @Override
    public int countByHistory(Map<String, Object> map) {
        return equipmentDao.countByHistory(map);
    }

    @Override
    @Transactional
    /**
     * 出库登记的时候变更所属单位和持有人
     */
    public int updateUserId(Map<String, Object> map) {
        logger.debug("service中updateUserId调用");
        String deviceUUID = (String) map.get("deviceUUID");
        EquipmentLedger e = selectByUUID(deviceUUID);
        EquipmentLedger history = new EquipmentLedger();
        BeanUtils.copyProperties(e, history);
        if (e == null) {
            return MyConstants.DEVICE_FIND_EMPTY;
        }
        String now = Dates.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("organisation", map.get("affiliationId"));
        List<User> users = userService.listByNoPage(userMap);
        Integer userId = users.get(0).getId();
        map.put("userId", userId);
        map.put("flag", 1);
        int rs = equipmentDao.updateUserId(map);
        e.setEndTime(now);
        updateHistory(e);
        history.setUserId(userId);
        history.setAffiliationId((Integer) map.get("affiliationId"));
        history.setStartTime(now);
        history.setRemark(MyConstants.DEVICE_CHANGE_DELIVERY);
        insertHistory(history);
        if (!(rs > 0)) {
            return MyConstants.DEVICE_FAIL;
        }
        logger.debug("service中updateUserId调用结束");
        return rs;
    }

    @Override
    @Transactional
    /**
     * 变更持有人或者变更头盔状态等
     */
    public int update(EquipmentLedger equipmentLedger) {
        logger.debug("service中update调用");
        String deviceUUID = equipmentLedger.getDeviceUUID();
        EquipmentLedger e = selectByUUID(deviceUUID);
        if (e == null) {
            return MyConstants.DEVICE_FIND_EMPTY;
        }
        String now = Dates.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        //变更状态时变更持有人
        //2  维修状态，持有人变更为田一科技管理员
        //3,4,5,6  丢失报废删除注销，为没有持有人
        if (equipmentLedger.getStatus() != null && equipmentLedger.getStatus() != 0) {
            if (equipmentLedger.getStatus() == 2) {
                equipmentLedger.setAffiliationId(1);
                Map<String, Object> map = new HashMap<>();
                map.put("organisation", 1);
                List<User> users = userService.listByNoPage(map);
                equipmentLedger.setUserId(users.get(0).getId());
                equipmentLedger.setFlag(2);

            } else if (equipmentLedger.getStatus() != 1) {
                equipmentLedger.setUserId(-1);
            } else {// 20190425注销绑定此用户的其他头盔，用户和头盔是一对一的关系
                if(equipmentLedger.getUserId() != null && equipmentLedger.getUserId() != -1) {
                    List<EquipmentLedger> list = this.selectByUserId(equipmentLedger.getUserId());
                    for (EquipmentLedger e1 : list) {
                        if (!e1.getDeviceUUID().equals(equipmentLedger.getDeviceUUID())) {
                            e1.setUserId(-1);
                            e1.setStatus(6);
                            equipmentDao.update(e1);
                        }
                    }
                }
            }
            if(e.getUserId() != equipmentLedger.getUserId()) {
                equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_USER);
            } else {
                equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_STATUS);
            }
        }

        int rs = equipmentDao.update(equipmentLedger);
        if (!(rs > 0)) {
            return MyConstants.DEVICE_FAIL;
        }
        e.setEndTime(now);
        e.setStatus(0);
        updateHistory(e);
        equipmentLedger.setAffiliationId(e.getAffiliationId());
        equipmentLedger.setDeviceNumber(e.getDeviceNumber());
        equipmentLedger.setDeviceUUID(e.getDeviceUUID());
        equipmentLedger.setModel(e.getModel());
        equipmentLedger.setVersionId(e.getVersionId());
        equipmentLedger.setBatch(e.getBatch());
        equipmentLedger.setCategoryId(e.getCategoryId());
        equipmentLedger.setStartTime(now);
        equipmentLedger.setCreateTime(e.getCreateTime());
        insertHistory(equipmentLedger);
        logger.debug("service中update调用结束");
        return rs;
    }

    @Override
    @Transactional
    public int deleteById(String deviceUUID, String reason) {
        logger.debug("service中deleteById调用");
        EquipmentLedger equipmentLedger = selectByUUID(deviceUUID);
        if (equipmentLedger == null) {
            return MyConstants.DEVICE_FIND_EMPTY;
        }
        String now = Dates.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        int rs = equipmentDao.updateById(deviceUUID);
        //插入历史表
        equipmentLedger.setEndTime(now);
        equipmentLedger.setStatus(5);
        equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_DELETE);
        updateHistory(equipmentLedger);
        Map<String, Object> map = new HashMap<>();
        map.put("deviceUUID", deviceUUID);
        map.put("reason", reason);
        //插入原因表
        int raws = equipmentDao.insertReason(map);

        if (!(rs > 0 && raws > 0)) {
            return MyConstants.DEVICE_FAIL;
        }
        logger.debug("service中deleteById调用结束");
        return rs;
    }


    private List<EquipmentLedger> setNames(List<EquipmentLedger> list) {
        for (int i = 0; i < list.size(); i++) {
            List<Map<Integer, String>> versionList = versionService.selectVersion(list.get(i).getVersionId());
            List<Map<Integer, String>> categoryList = categoryService.selectCategory(list.get(i).getCategoryId());
            String userName = "——";
            String possessorName = "——";
            String affiliationName = "——";
            if (list.get(i).getUserId() != null && list.get(i).getUserId() != 0 && list.get(i).getUserId() != -1) {
                User user = userService.selectById(list.get(i).getUserId());
                userName = user.getName();
            }
            if (list.get(i).getPossessorId() != null && list.get(i).getPossessorId() != 0 && list.get(i).getPossessorId() != -1) {
                User user = userService.selectById(list.get(i).getPossessorId());
                possessorName = user.getName();
            }
            if (list.get(i).getAffiliationId() != null && list.get(i).getAffiliationId() != 0 && list.get(i).getAffiliationId() != -1) {
                Company company = companyService.selectById(list.get(i).getAffiliationId());
                affiliationName = company.getCompanyName();
            }
            Map<Integer, String> versionMap = versionList.get(0);
            Map<Integer, String> categoryMap = categoryList.get(0);
            String version = versionMap.get("version");
            String category = categoryMap.get("category");

            list.get(i).setVersion(version);
            list.get(i).setCategory(category);
            list.get(i).setUserName(userName);
            list.get(i).setPossessorName(possessorName);
            list.get(i).setAffiliationName(affiliationName);
        }
        return list;
    }

    /**
     * 某个客户端是否在线
     *
     * @param deviceUUID
     * @return
     */
    public boolean isOnLine(String deviceUUID) {
        String time = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_ONLINE_BY_IMEI + ":" + deviceUUID);
        if (time == null) return false;
        if (System.currentTimeMillis() - Long.parseLong(time) <= 1000l * configService.getHelmetOnlineIntervalSeconds()) {
            return true;
        }
        return false;
    }

    /**
     * 查询列表
     *
     * @return
     */
    public List<EquipmentLedger> selectByType(Boolean isActive) {
        Map<String, Object> map = new HashMap<>();
        if (isActive != null) {
            if (isActive) {
                map.put("flag", 1);//查询已初始化的
                map.put("status", 1);
            } else {
                map.put("flag", 2);//查询未初始化的
            }
        }
        return equipmentDao.select(map);
    }

    // TODO: 2018/10/9 爆红
    public List<EquipmentLedger> fullfilCustomerNeUser(List<EquipmentLedger> list) {
//        RelationUtils.fullfilListRelateProperty(list, EquipmentLedger::getAffiliationId, companyService::selectById, Company::getId, Company::getDisplayName, EquipmentLedger::setAffiliationName);
        return setNames(list);
    }

    @Override
    public List<EquipmentLedger> selectByUserId(int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return equipmentDao.select(map);
    }

    @Override
    public EquipmentLedger selectByUUID(String deviceUUID) {
        return equipmentDao.getByDeviceUUID(deviceUUID);

    }

    @Override
    public EquipmentLedger selectByDeviceNumber(String deviceNumber) {
        return equipmentDao.getByDeviceNumber(deviceNumber);
    }

    /**
     * 存储头盔的gps模块定位状态.状态最多保存240
     *
     * @param imei
     * @param stateOk true表示定位成功,false表示定位失败。定位失败后gps数据以网络定位的数据为准.
     */
    @Override
    public void cacheGpsState(String imei, boolean stateOk) {
        Long time = System.currentTimeMillis();
        Long previousTime = gpsTimeMapCache.putIfAbsent(imei, time);
        if (previousTime == null || time - previousTime > 10 * 1000) {
            //10秒更新一次redis
            redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_GPS_STATE_BY_IMEI + ":" + imei, (stateOk ? "1" : "0"), 240, TimeUnit.MINUTES);
        }
    }

    /**
     * 头盔的gps模块定位是否成功
     *
     * @param imei
     * @return
     */
    @Override
    public boolean isGpsStateOk(String imei) {
        String state = (String) redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_GPS_STATE_BY_IMEI + ":" + imei);
        return "1".equals(state);
    }

    /**
     * 缓存客户端最后发送心跳的时间。可用来判断客户端在线状态
     *
     * @param imei
     * @return
     */
    @Override
    public void cacheWillTime(String imei) {
        Long time = System.currentTimeMillis();
        Long previousTime = willTimeMapCache.putIfAbsent(imei, time);
        if (previousTime == null || time - previousTime > 10 * 1000) {
            //10秒更新一次redis
            redisTemplate.opsForValue().set(CacheKeyConstants.HELMET_ONLINE_BY_IMEI + ":" + imei, time.toString(), 120, TimeUnit.SECONDS);
        }
    }

    @Override
    public Set<String> getEffectHelmetIdSet() {
        return selectByType(true).stream().map(helmet -> helmet.getDeviceUUID()).collect(Collectors.toSet());
    }


    public int updateHistory(EquipmentLedger e) {
        return equipmentDao.updateHistory(e);
    }

    @Override
    @Transactional
    public int unbinded(EquipmentLedger equipmentLedger) {
        logger.debug("service中unbinded调用");
        String deviceUUID = equipmentLedger.getDeviceUUID();
        EquipmentLedger e = selectByUUID(deviceUUID);
        if (e == null) {
            return MyConstants.DEVICE_FIND_EMPTY;
        }
        equipmentLedger.setUserId(-1);
        equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_USER);
        String now = Dates.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        int rs = equipmentDao.update(equipmentLedger);
        if (!(rs > 0)) {
            return MyConstants.DEVICE_FAIL;
        }
        e.setEndTime(now);
        e.setStatus(0);
        updateHistory(e);
        equipmentLedger.setAffiliationId(e.getAffiliationId());
        equipmentLedger.setDeviceNumber(e.getDeviceNumber());
        equipmentLedger.setDeviceUUID(e.getDeviceUUID());
        equipmentLedger.setModel(e.getModel());
        equipmentLedger.setVersionId(e.getVersionId());
        equipmentLedger.setBatch(e.getBatch());
        equipmentLedger.setCategoryId(e.getCategoryId());
        equipmentLedger.setStartTime(now);
        equipmentLedger.setCreateTime(e.getCreateTime());
        insertHistory(equipmentLedger);
        logger.debug("service中unbinded调用结束");
        return rs;
    }

    @Override
    public String selectBlueBoxMac(int id) {
        return equipmentDao.getBlueBoxMac(id);
    }
}

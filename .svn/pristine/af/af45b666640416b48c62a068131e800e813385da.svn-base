package com.tianyi.helmet.server.service.client.impl;

import com.tianyi.helmet.server.dao.client.HelmetUniversalDao;
import com.tianyi.helmet.server.dao.client.UserDao;
import com.tianyi.helmet.server.entity.client.CustomerInfo;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetCharge;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatus;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.service.client.*;
import com.tianyi.helmet.server.service.data.HelmetChargeService;
import com.tianyi.helmet.server.service.data.HelmetOnlineStatusService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/6/13 9:02
 */
@Service
public class HelmetUniversalServiceImpl implements HelmetUniversalService{
    @Autowired
    private HelmetUniversalActralService helmetUniversalActralService;
    @Autowired
    private HelmetUniversalDao helmetUniversalDao;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private HelmetOnlineStatusService helmetOnlineStatusService;
    @Autowired
    private HelmetChargeService helmetChargeService;
    @Autowired
    private UserService userService;
    @Autowired
    private HelmetConfigService helmetConfigService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private UserDao userDao;

    @Override
    public ResponseVo listCustomers(String uuid) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        User user = userService.selectById(equipmentLedger.getUserId());
        ResponseVo responseVo  = helmetUniversalActralService.listActralCustomers(user.getTianyuanAccount(), helmetUniversalInfo.getId(), equipmentLedger.getDeviceNumber());// 调用实际业务系统方法
        if(responseVo.isSuccess()) {
            List<CustomerInfo> customers = (List<CustomerInfo>) responseVo.getData();
            helmetUniversalInfo.setCustomers(customers);
        }
        return ResponseVo.success(helmetUniversalInfo);
    }

    @Override
    public ResponseVo listWorks(String uuid, String id) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.listActralWorks(equipmentLedger.getUserId().toString(), id, helmetUniversalInfo.getId());// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public ResponseVo startOrder(String uuid, String id, String isQuick) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.startOrder(equipmentLedger.getUserId().toString(), id, helmetUniversalInfo.getId(), isQuick);// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public ResponseVo endOrder(String uuid, String id, String isQuick) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.endOrder(equipmentLedger.getUserId().toString(), id, helmetUniversalInfo.getId(), isQuick);// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public ResponseVo startTask(String uuid, String orderNo, String taskid) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.startTask(equipmentLedger.getUserId().toString(), orderNo, taskid, helmetUniversalInfo.getId(), equipmentLedger.getDeviceNumber());// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public ResponseVo endTask(String uuid, String orderNo, String taskid, String pass, String remark) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.endTask(equipmentLedger.getUserId().toString(), orderNo, taskid, pass, remark, helmetUniversalInfo.getId());// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public List<HelmetUniversalInfo> list(Map<String, Object> param) {
        return helmetUniversalDao.listBy(param);
    }

    @Override
    public ResponseVo startCar(String uuid, String id, String isQuick) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.startCar(equipmentLedger.getUserId().toString(), id, helmetUniversalInfo.getId(), isQuick, equipmentLedger.getDeviceNumber());// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public ResponseVo endCar(String uuid, String id, String isQuick) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(uuid);
        ResponseVo responseVo = helmetUniversalActralService.endCar(equipmentLedger.getUserId().toString(), id, helmetUniversalInfo.getId(), isQuick);// 调用实际业务系统方法
        return responseVo;
    }

    @Override
    public HelmetUniversalInfo selectByUserId(int userId) {
        HelmetUniversalInfo info = helmetUniversalDao.selectByUserId(userId);
        return info;
    }

    @Override
    public ResponseVo loginLong(String id, String userid, String onlinetime, String offlinetime, String helmetImei) {
        int userId = -1;
        if (userid != null && userid.length() > 0) {
            userId = Integer.parseInt(userid);
        } else if(!StringUtils.isEmpty(helmetImei)) {
            userId = equipmentService.selectByUUID(helmetImei).getUserId();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("uid", id);
        int count = helmetOnlineStatusService.countBy(param);
        if(count > 0) {
            return ResponseVo.fail("601","头盔在线时长重复上传："+id);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HelmetOnlineStatus helmetOnlineStatus = new HelmetOnlineStatus();
        helmetOnlineStatus.setUid(id);
        helmetOnlineStatus.setClientId(helmetImei);
        LocalDateTime onlineTime = LocalDateTime.parse(onlinetime, formatter);
        helmetOnlineStatus.setOnlineTime(onlineTime);
        LocalDateTime offlineTime = LocalDateTime.parse(offlinetime, formatter);
        helmetOnlineStatus.setOfflineTime(offlineTime);
        helmetOnlineStatus.setStatus(1);
        User user = userService.selectById(userId);
        if(user!=null){
            helmetOnlineStatus.setUserId(user.getId());
            helmetOnlineStatus.setUserName(user.getName());
            helmetOnlineStatus.setNeUsername(user.getNeUserHel());
        } else {
            helmetOnlineStatus.setUserId(-1);
            helmetOnlineStatus.setUserName("未知用户");
            helmetOnlineStatus.setNeUsername("未知用户");
        }
        helmetOnlineStatusService.insert(helmetOnlineStatus);
        return ResponseVo.success("上传成功");
    }

    @Override
    public ResponseVo chargeLong(String id, String userid, String onlinetime, String offlinetime, String helmetImei) {
        int userId = -1;
        if (userid != null && userid.length() > 0) {
            userId = Integer.parseInt(userid);
        } else if(!StringUtils.isEmpty(helmetImei)) {
            userId = equipmentService.selectByUUID(helmetImei).getUserId();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("uid", id);
        int count = helmetChargeService.countBy(param);
        if(count > 0) {
            return ResponseVo.fail("601","头盔充电时长重复上传："+id);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HelmetCharge helmetCharge = new HelmetCharge();
        helmetCharge.setUid(id);
        helmetCharge.setClientId(helmetImei);
        LocalDateTime onlineTime = LocalDateTime.parse(onlinetime, formatter);
        helmetCharge.setOnlineTime(onlineTime);
        LocalDateTime offlineTime = LocalDateTime.parse(offlinetime, formatter);
        helmetCharge.setOfflineTime(offlineTime);
        helmetCharge.setStatus(1);
        User user = userService.selectById(userId);
        if(user!=null){
            helmetCharge.setUserId(user.getId());
            helmetCharge.setUserName(user.getName());
            helmetCharge.setNeUsername(user.getNeUserHel());
        } else {
            helmetCharge.setUserId(-1);
            helmetCharge.setUserName("未知用户");
            helmetCharge.setNeUsername("未知用户");
        }
        helmetChargeService.insert(helmetCharge);
        return ResponseVo.success("上传成功");
    }

    @Override
    public ResponseVo listConfigs(String uuid) {
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(uuid);
        return helmetConfigService.listConfigs(helmetUniversalInfo.getId());
    }

    @Override
    public ResponseVo<List<ContactState>> listContact(String helmetImei) {
        int userId = -1;
        String deviceNum = "";
        if(!StringUtils.isEmpty(helmetImei)) {
            EquipmentLedger equipmentLedger = equipmentService.selectByUUID(helmetImei);
            if(equipmentLedger != null) {
                userId = equipmentLedger.getUserId();
                deviceNum = equipmentLedger.getDeviceNumber();
            } else {
                return ResponseVo.fail("600", "头盔没有入库");
            }
        }
        if(userId == -1) {
            return ResponseVo.fail("601", "头盔没有绑定用户信息");
        }
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalDao.selectByUuid(helmetImei);
        List<ContactState> contactList = new ArrayList<>();
        if(helmetUniversalInfo!= null) {
            List<User> list = userDao.selectUsersByUid(helmetUniversalInfo.getId());
            for(User user : list) {
                ContactState cs = new ContactState(user.getId(), user.getAccount(), user.getName(), user.getNeUserHel(), user.getNeUserWeb(), user.getNeUserPhn());
                int[] states = userComponent.onlineState(user);
                cs.setHelmetOnline(states[0]);
                cs.setMobileOnline(states[1]);
                contactList.add(cs);
            }
        }
        return ResponseVo.success(contactList);

//        User user = userService.selectById(userId);
//        return helmetUniversalActralService.listActralContact(helmetUniversalInfo.getId(), user.getTianyuanAccount(), deviceNum);
    }
}

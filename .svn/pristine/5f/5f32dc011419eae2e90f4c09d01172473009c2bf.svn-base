package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.*;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.log.HelmetLogTypeEnum;
import com.tianyi.helmet.server.service.data.HelmetSensorService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.job.MqttBackendSubscribeJob;
import com.tianyi.helmet.server.service.kmx.MetaDataInitService;
import com.tianyi.helmet.server.service.log.HelmetLogService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *  头盔相关
 *
 * Created by liuhanc on 2018/4/20.
 */
@Component
public class HelmetComponent {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private HelmetLogService helmetLogService;

    public boolean setHelmetSaleInfo(EquipmentLedger helmet, int customerId) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        helmet.setFlag(1);//设置为已售
        helmet.setUpdateTime(Dates.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        helmet.setAffiliationId(customerId);
//        helmet.setSalerId(userInfo.getId());//销售员
        int cnt = equipmentService.update(helmet);
        if (cnt == 1) {
            helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.sale.toString(), "出售头盔给客户" + customerId);
            //设置出厂成功，则开始订阅此头盔的传感器  以后不订阅了  2018/10/9

//            mqttBackendSubscribeJob.addNewHelmetTopic(helmet.getDeviceUUID());
        }
        return cnt == 1 ? true : false;
    }

//    /**
//     * 头盔初始化,设置头盔ClientId=imei
//     *
//     * @param helmet
//     * @param neUser
//     * @return
//     */
//    @Transactional
//    public ResponseVo initHelmetClientId(EquipmentLedger helmet, NeteaseUser neUser) {
//        //绑定
//        helmet.setCreateTime(Dates.format(new Date(),"yyyy-MM-dd hh:mm:ss"));
//        helmet.setFlag(2);//设置为待发售,发售前还需要设置销售员、客户信息
//        int cnt = equipmentService.update(helmet);
//        if (cnt == 1) {
//            neUser.setDeviceId(helmet.getDeviceUUID());
//            neteaseUserService.updateById(neUser);
//            //创建时序数据库元数据信息
//            metaDataInitService.initDeviceHelmetDevice(helmet.getDeviceUUID());
//            helmetSensorService.addEffectHelmet(helmet.getDeviceUUID());//加入到有效的数据缓存中
//
////            helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.init.toString(), "绑定IMEI:" + helmet.getDeviceUUID() + "到头盔:" + helmet.getId() + "-" + helmet.getNeUsername() + "上，网易用户id=" + neUser.getId() + ",name=" + neUser.getUsername());
//            helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.init.toString(), "绑定IMEI:" + helmet.getDeviceUUID() + "到头盔:" + helmet.getId() + "-" + "上，网易用户id=" + neUser.getId() + ",name=" + neUser.getUsername());
//
//            return ResponseVo.success();
//        }
//        return ResponseVo.fail("更新数据失败");
//    }

    /**
     * 设置头盔绑定的天远账号
     *
     * @param helmet
     * @param tianYuanUser
     * @return
     */
//    @Transactional
//    public ResponseVo updateHelmetTianYuanUser(Helmet helmet, TianYuanUser tianYuanUser) {
//        //绑定
//        helmet.setTianYuanUserId(tianYuanUser.getId());
//        int cnt = helmetService.updateHelmetById(helmet);
//        if (cnt == 1) {
//            //创建时序数据库元数据信息
//            helmetLogService.addLog(helmet.getImei(), HelmetLogTypeEnum.bound.toString(), "绑定天远账号" + tianYuanUser.getUsername() + "到头盔:" + helmet.getId() + "-" + helmet.getNeUsername() + "上，天远用户id=" + tianYuanUser.getId() + ",name=" + tianYuanUser.getOprtName());
//            return ResponseVo.success();
//        }
//        return ResponseVo.fail("更新数据失败");
//    }
//
//    @Transactional
//    public void clearHelmetTianYuanUser(Helmet helmet) {
//        helmet.setTianYuanUserId(null);
//        helmetService.updateHelmetById(helmet);
//        helmetLogService.addLog(helmet.getImei(), HelmetLogTypeEnum.bound.toString(), "头盔解除绑定天远账号." + helmet.getId() + "-" + helmet.getNeUsername());
//    }

    /**
     * 设置头盔绑定的田一账号
     *
     * @param helmet
     * @param
     * @return
     */
    @Transactional
    public ResponseVo updateHelmetTianyiUser(EquipmentLedger helmet, User user) {
        //绑定
        /**
         * update by xiayuan 2018/10/10
         */
        helmet.setUserId(user.getId());
        int cnt = equipmentService.update(helmet);
        if (cnt == 1) {
            //创建时序数据库元数据信息
//            helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.bound.toString(), "绑定田一账号." + helmet.getId() + "-" + helmet.getNeUsername());
            helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.bound.toString(), "绑定田一账号." + helmet.getId() + "-" );
            return ResponseVo.success();
        }
        return ResponseVo.fail("更新数据失败");
    }

    @Transactional
    public int clearHelmetTianyiUser(EquipmentLedger helmet) {

        helmet.setFlag(2);
        helmet.setUpdateTime(Dates.format(new Date(),"yyy-MM-dd hh:mm:ss"));
        int rs = equipmentService.update(helmet);
        helmet.setStartTime(Dates.format(new Date(),"yyy-MM-dd hh:mm:ss"));
        helmet.setEndTime("");
        int r = equipmentService.insertHistory(helmet);

//        helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.bound.toString(), "头盔解除绑定田一账号." + helmet.getId() + "-" + helmet.getNeUsername());
        helmetLogService.addLog(helmet.getDeviceUUID(), HelmetLogTypeEnum.bound.toString(), "头盔解除绑定田一账号." + helmet.getId() + "-");
        return rs;
    }
}

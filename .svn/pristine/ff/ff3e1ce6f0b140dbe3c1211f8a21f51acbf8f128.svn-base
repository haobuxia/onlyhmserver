package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.vo.ResponseVo;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/6/12 16:10
 */
public interface HelmetUniversalService {
    /**
     * 头盔统一调用接口获取客户列表
     * @param uuid
     * @return
     */
    ResponseVo listCustomers(String uuid);

    /**
     * 头盔统一调用接口获取工单列表
     * @param uuid
     * @param id
     * @return
     */
    ResponseVo listWorks(String uuid, String id);

    /**
     * 开始工单
     * @param uuid
     * @param id
     * @param isQuick
     * @return
     */
    ResponseVo startOrder(String uuid, String id, String isQuick);

    ResponseVo endOrder(String uuid, String id, String isQuick);

    ResponseVo startTask(String uuid, String orderNo, String taskid);

    ResponseVo endTask(String uuid, String orderNo, String taskid, String pass, String remark);

    List<HelmetUniversalInfo> list(Map<String, Object> param);

    ResponseVo startCar(String uuid, String id, String isQuick);

    ResponseVo endCar(String uuid, String id, String isQuick);

    HelmetUniversalInfo selectByUserId(int userId);

    ResponseVo loginLong(String id, String userid, String onlinetime, String offlinetime, String helmetImei);

    ResponseVo chargeLong(String id, String userid, String onlinetime, String offlinetime, String helmetImei);

    ResponseVo listConfigs(String uuid);

    ResponseVo<List<ContactState>> listContact(String helmetImei);
}

package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.vo.ResponseVo;

import java.util.List;

/**
 * Created by tianxujin on 2019/6/12 16:10
 */
public interface HelmetUniversalActralService {

    /**
     * 业务系统需要实现的获取客户/车辆列表接口
     * @param userId
     * @param project
     * @param deviceNumber
     * @return
     */
    ResponseVo listActralCustomers(String userId, Integer project, String deviceNumber);

    /**
     * 业务系统需要实现的获取客户/车辆工单的接口
     * @param userId
     * @param cid 客户/车辆id
     * @param project
     * @return
     */
    ResponseVo listActralWorks(String userId, String cid, Integer project);

    /**
     * 开始工单
     * @param userId 用户id
     * @param id 检查单ID
     * @param project
     * @param isQuick
     * @return
     */
    ResponseVo startOrder(String userId, String id, Integer project, String isQuick);

    ResponseVo endOrder(String userId, String id, Integer project, String isQuick);

    ResponseVo startTask(String s, String orderNo, String taskid, Integer project, String deviceNumber);

    ResponseVo endTask(String s, String orderNo, String taskid, String pass, String remark, Integer project);

    ResponseVo startCar(String userId, String id, Integer project, String isQuick, String deviceNumber);

    ResponseVo endCar(String userId, String id, Integer project, String isQuick);

    ResponseVo<List<ContactState>> listActralContact(int id, String tianyuanAccount, String deviceNum);
}

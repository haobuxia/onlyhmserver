package com.tianyi.helmet.server.service.client.impl;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.service.client.HelmetUniversalActralService;
import com.tianyi.helmet.server.service.client.UserComponent;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanKomatsuApiHelper;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianxujin on 2019/6/13 9:04
 */
@Service
public class HelmetUniversalActralServiceImpl implements HelmetUniversalActralService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private TianYuanKomatsuApiHelper tianYuanKomatsuApiHelper;
    @Override
    public ResponseVo listActralCustomers(String userId, Integer project, String deviceNumber) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.getPendingCars(userId, project, deviceNumber);
        return ResponseVo.success(jsonObject.get("data"));
    }

    /**
     * 根据用户和客户信息获取工单信息
     * @param userId
     * @param cid 客户/车辆id
     * @param project
     * @return
     */
    @Override
    public ResponseVo listActralWorks(String userId, String cid, Integer project) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.getPendingWorks(userId, cid, project);
        return ResponseVo.success(jsonObject.get("data"));
    }

    @Override
    public ResponseVo startOrder(String userId, String id, Integer project, String isQuick) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.startOrder(userId, id, project, isQuick);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo endOrder(String userId, String id, Integer project, String isQuick) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.endOrder(userId, id, project, isQuick);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo startTask(String userId, String orderNo, String taskid, Integer project, String deviceNumber) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.startTask(userId, orderNo, taskid, project, deviceNumber);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo endTask(String userId, String orderNo, String taskid, String pass, String remark, Integer project) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.endTask(userId, orderNo, taskid, pass, remark, project);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo startCar(String userId, String id, Integer project, String isQuick, String deviceNumber) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.startCar(userId, id, project, isQuick, deviceNumber);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo endCar(String userId, String id, Integer project, String isQuick) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.endCar(userId, id, project, isQuick);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<List<ContactState>> listActralContact(int id, String tianyuanAccount, String deviceNum) {
        JSONObject jsonObject = tianYuanKomatsuApiHelper.listContact(tianyuanAccount, id, deviceNum);
        List<String> userIds = (List<String>) jsonObject.get("data"); //List<tianyuanAccounts>
        List<ContactState> contactList = new ArrayList<>();
        for(String userId : userIds) {
            User user = userService.selectByTianyuanAccount(userId);
            if(user == null) {
                continue;
            }
            ContactState cs = new ContactState(user.getId(), user.getAccount(), user.getName(), user.getNeUserHel(), user.getNeUserWeb(), user.getNeUserPhn());
            int[] states = userComponent.onlineState(user);
            cs.setHelmetOnline(states[0]);
            cs.setMobileOnline(states[1]);
            contactList.add(cs);
        }
        return ResponseVo.success(contactList);
    }
}

package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.angelcomm.azkj.AzkjClient;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.netease.NeteaseApiService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.AppAccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  网易用户相关
 *
 * Created by liuhanc on 2018/4/20.
 */
@Component
public class NeteaseUserComponent {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private NeteaseApiService apiService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private AzkjClient azkjClient;

    /**
     * 注册网易用户
     *
     * @param userId
     * @param userName
     * @param password
     * @param userType 1头盔app用户;2 web/手机app用户 3管理员 4未设置用户类型
     * @param avprovider
     * @return code 200表示成功,601表示网易云信账号注册失败
     */
    @Transactional
    @CacheEvict(value = CacheKeyConstants.NETEASE_USER_BY_ID, key = "#userName.toLowerCase()")
    public AppAccountVo regNeteaseUser(int userId, String userName, String password, int userType, String avprovider) {
        NeteaseUser neteaseUser = neteaseUserService.selectByUsername(userName);
        if (neteaseUser != null) {
            return new AppAccountVo("600", "此帐号已经被注册");
        }

        neteaseUser = new NeteaseUser();
        neteaseUser.setUserType(userType);
        neteaseUser.setUserId(userId);
        neteaseUser.setUsername(userName);
        neteaseUser.setPassword(password);
        neteaseUser.setYunId(userName);
        neteaseUser.setRegTime(LocalDateTime.now());
        neteaseUser.setCompany(avprovider);
        neteaseUserService.insert(neteaseUser);

        /**
         * update by tianxujin
         * 新增安正科技创建音视频账号功能，用户前缀NeteaseUserNamePrefix是1,2,3,4...数值类型；因为安正的账号是数值类型
         */
        String token = "";
        if("netease".equals(avprovider)) {
            token = apiService.userCreate(userName, userName);
        } else if("angelcomm".equals(avprovider)) {
            // 调用安正科技创建用户方式
            token = azkjClient.registerUser(userName);
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("id", neteaseUser.getId());
        if (!StringUtils.isEmpty(token)) {
            neteaseUser.setYunToken(token);
            neteaseUserService.updateById(neteaseUser);
            /**
             * update by xiayuan 2018/10/10
             */
            dataMap.put("userId", userId);
            dataMap.put("username", userName);
            dataMap.put("yun_token", token);
            return new AppAccountVo("200", "注册成功", dataMap);
        } else {
            return new AppAccountVo("601", "账号注册成功，注册云信账号失败");
        }
    }


    /**
     * 重新生成用户的token
     *
     * @param userInfo
     * @return
     */
    public AppAccountVo regetNeteaseToken(NeteaseUser userInfo) {
        String token = userInfo.getYunToken();
        if (StringUtils.isEmpty(token)) {
            //get
            token = apiService.userCreate(userInfo.getUsername(), userInfo.getUsername());
        } else {
            //refresh
            token = apiService.userRefreshToken(userInfo.getUsername());
        }

        if (StringUtils.isEmpty(token)) {
            return new AppAccountVo("601", "云信账号token刷新失败");
        } else {
            userInfo.setYunToken(token);
            neteaseUserService.updateById(userInfo);

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("username", userInfo.getUsername());
            dataMap.put("yun_token", token);
            return new AppAccountVo("200", "重新获取云信token成功", dataMap);
        }
    }
}

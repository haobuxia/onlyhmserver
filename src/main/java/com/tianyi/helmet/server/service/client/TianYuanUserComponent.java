package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanApiBasicHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 *  田一用户相关
 *
 * Created by liuhanc on 2018/4/20.
 */
@Component
public class TianYuanUserComponent {
    private Logger logger = LoggerFactory.getLogger(TianYuanUserComponent.class);
    @Autowired
    private ConfigService configService;
    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private TianYuanApiBasicHelper tianYuanApiBasicHelper;


    /**
     * 天远账号登录
     *
     * @param userName
     * @param password
     * @return
     */
    public TianYuanUser userPassLogin(String userName, String password) {
        TianYuanUser user = tianYuanApiBasicHelper.userPassLogin(userName, password);
        if (user != null) {
            this.saveTianYuanUser(user);
        }
        return user;
    }

    /**
     * 将用户信息入库存储,如果存在则更新
     */
    public boolean saveTianYuanUser(TianYuanUser user) {
        TianYuanUser old = tianYuanUserService.selectByUsername(user.getUsername());
        user.setRefreshTime(LocalDateTime.now());
        if (old != null) {
            user.setId(old.getId());
            user.setRegTime(old.getRegTime());
            user.setAccountId(old.getAccountId());
            user.setOprtId(old.getOprtId());
            user.setOprtName(old.getOprtName());
            tianYuanUserService.clearByUserNameCache(user.getUsername());
            tianYuanUserService.updateById(user);
            return false;
        } else {
            user.setRegTime(LocalDateTime.now());
            fillOprtInfoIfEmpty(user, false);
            tianYuanUserService.clearByUserNameCache(user.getUsername());
            tianYuanUserService.insert(user);
            return true;
        }
    }


    //检查并填充天远用户的oprt信息
    public void fillOprtInfoIfEmpty(TianYuanUser user, boolean save) {
        if (StringUtils.isEmpty(user.getOprtId())) {
            String[] oprtInfo = null;
            try {
                oprtInfo = tianYuanUserService.getTianYuanUserOprtInfo(user);
            } catch (Exception e) {
                logger.error("获取天远用户的oprt信息失败", e);
            }

            if (oprtInfo != null) {
                user.setAccountId(oprtInfo[0]);
                user.setOprtId(oprtInfo[1]);
                user.setOprtName(oprtInfo[2]);
                if (save) {
                    tianYuanUserService.clearByUserNameCache(user.getUsername());
                    tianYuanUserService.updateById(user);
                }
            }
        }
    }

    /**
     * 特殊账号
     *
     * @return
     */
    public TianYuanUser getSpecialUser() {
        //特殊的账号
        String loginName = configService.getTianYuanOAuthProdLoginUser();
        String userName = TianYuanUser.toUserName(loginName);
        TianYuanUser tianYuanUser = tianYuanUserService.selectByUsername(userName);
        if (tianYuanUser == null) {
            synchronized (TianYuanUserComponent.this) {
                tianYuanUser = tianYuanUserService.selectByUsername(userName);
                if (tianYuanUser == null) {
                    logger.debug("天远正式环境特殊账号用户信息不存在,则执行登录.userName=" + userName);
                    String loginPwd = configService.getTianYuanOAuthProdLoginPass();
                    tianYuanUser = this.userPassLogin(userName, loginPwd);
                }
            }
        }
        return tianYuanUser;
    }

}

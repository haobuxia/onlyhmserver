package com.tianyi.helmet.server.service.client;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.dao.client.TianYuanUserDao;
import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.tianyuan.TianYuanApiBasicHelper;
import com.tianyi.helmet.server.util.Commons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天远用户信息服务
 * <p>
 * Created by liuhanc on 2017/11/2.
 */
@Service
public class TianYuanUserService {
    @Autowired
    TianYuanUserDao tianYuanUserDao;
    @Autowired
    private TianYuanApiBasicHelper tianYuanApiBasicHelper;
    @Autowired
    private TianYuanUserComponent tianYuanUserComponent;

    private Logger logger = LoggerFactory.getLogger(TianYuanUserService.class);

    public void insert(TianYuanUser user) {
        tianYuanUserDao.insert(user);
    }

    @Cacheable(value = CacheKeyConstants.TIANYUAN_USER_BY_ID, key = "#id.toString()", unless = "#result == null")
    public TianYuanUser selectById(int id) {
        TianYuanUser user = tianYuanUserDao.selectById(id);
        if (user != null)
            tianYuanUserComponent.fillOprtInfoIfEmpty(user, true);
        return user;
    }

    @Cacheable(value = CacheKeyConstants.TIANYUAN_USER_BY_USERNAME, key = "#username.toLowerCase().toString()", unless = "#result == null")
    public TianYuanUser selectByUsername(String username) {
        TianYuanUser user = tianYuanUserDao.selectByUsername(username);
        if (user != null)
            tianYuanUserComponent.fillOprtInfoIfEmpty(user, true);
        return user;
    }


    @CacheEvict(value = CacheKeyConstants.TIANYI_USER_BY_ID, key = "#user.id.toString()")
    public int updateById(TianYuanUser user) {
        int cnt = tianYuanUserDao.updateById(user);
        return cnt;
    }

    @CacheEvict(value = CacheKeyConstants.TIANYUAN_USER_BY_USERNAME, key = "#username.toLowerCase().toString()")
    public void clearByUserNameCache(String username) {
        //什么都不做,清空缓存
    }

    public List<TianYuanUser> selectByOprtIdList(List<String> oprtIdList) {
        Map<String, Object> mm = new HashMap();
        mm.put("oprtIdList", oprtIdList);
        return listBy(mm);
    }

    public TianYuanUser selectByOprtId(String oprtId) {
        TianYuanUser user = tianYuanUserDao.selectByOprtId(oprtId);
        return user;
    }

    public TianYuanUser selectByOprtName(String oprtName) {
        TianYuanUser user = tianYuanUserDao.selectByOprtName(oprtName);
        return user;
    }

    public String checkOprtNameToOprtId(String maybeOprtName) {
        if (!Commons.isNumeric(maybeOprtName)) {
            //是姓名
            TianYuanUser tianYuanUser = this.selectByOprtName(maybeOprtName);
            if (tianYuanUser != null) {
                return tianYuanUser.getOprtId();
            }
        }
        return maybeOprtName;
    }

    public List<TianYuanUser> listBy(Map<String, Object> params) {
        return tianYuanUserDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return tianYuanUserDao.countBy(params);
    }

    /**
     * 根据token得到token对应的用户信息
     *
     * @return
     */
    public String[] getTianYuanUserOprtInfo(boolean isProd, String fullToken) {
        JSONObject jsonObject = tianYuanApiBasicHelper.getUserInfo(isProd, fullToken);
        if (jsonObject != null) {
            long accountId = jsonObject.getLongValue("Acnt_ID");
            long oprtId = jsonObject.getLongValue("Oprt_ID");
            String oprtName = jsonObject.getString("Oprt_Name");
            if (StringUtils.isEmpty(accountId) || StringUtils.isEmpty(oprtId)) {
                logger.error("接口反馈oprt信息不足." + jsonObject.toJSONString());
            }
            return new String[]{String.valueOf(accountId), String.valueOf(oprtId), oprtName};
        }
        return null;
    }

    /**
     * 根据token得到token对应的用户信息
     *
     * @return
     */
    public String[] getTianYuanUserOprtInfo(TianYuanUser user) {
        //[{"Acnt_ID":1000002122.0,"Acnt_Name":"tianyikeji","Oprt_ID":1000002643.0,"Oprt_Name":"tianyikeji","GroupId":"","IsFlagship":false,"ClientId":"rentalweb","Oprt_Mobile":"17091087800","DictOT_ID":1,"DictOT_Name":"管理员","ClientScope":["openid","profile","api","offline_access"],"GrantType":"password","SysId":0.0,"SysIds":[0.0,1.0],"Dept_Info":[{"Dept_ID":10001.0,"Dept_Name":"系统管理","OrgI_ID":1.0,"OrgI_Name":"默认架构"}],"Acnt_Dept_Info":[],"Acnt_Type":1}]
        return getTianYuanUserOprtInfo(user.isProdUser(), user.getFullToken());
    }

}

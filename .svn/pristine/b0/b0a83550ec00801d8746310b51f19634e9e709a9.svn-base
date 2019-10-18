package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.dao.netease.NeteaseUserDao;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网易用户信息服务
 * <p>
 * Created by liuhanc on 2017/11/2.
 */
@Service
public class NeteaseUserService {
    @Autowired
    NeteaseUserDao neteaseUserDao;

    public void insert(NeteaseUser user) {
        neteaseUserDao.insert(user);
    }

    public NeteaseUser selectById(int id) {
        return neteaseUserDao.selectById(id);
    }

    @Cacheable(value = CacheKeyConstants.NETEASE_USER_BY_ID, key = "#username.toLowerCase()", unless = "#result == null")
    public NeteaseUser selectByUsername(String username) {
        return neteaseUserDao.selectByUsername(username);
    }

    public NeteaseUser selectByUserId(String userId) {
        return neteaseUserDao.selectByUserId(userId);
    }

    public NeteaseUser selectByYunId(String yunId) {
        return neteaseUserDao.selectByYunId(yunId);
    }

    public List<NeteaseUser> listBy(Map<String, Object> params) {
        return neteaseUserDao.listBy(params);
    }

    public List<NeteaseUser> listByIdList(List<Integer> idList) {
        Map<String, Object> mm = new HashMap();
        mm.put("idList", idList);
        return listBy(mm);
    }

    public int countBy(Map<String, Object> params) {
        return neteaseUserDao.countBy(params);
    }

    /**
     * update by tianxujin 2019/09/21
     * @param avprovider
     */
    public int countNull(String avprovider) {
        return neteaseUserDao.countNull(avprovider);
    }

    public String selectNoUser(String avprovider) {
        return neteaseUserDao.selectNoUser(avprovider);
    }

    public int deleteById(int id) {
        return neteaseUserDao.deleteById(id);
    }

    @CacheEvict(value = CacheKeyConstants.NETEASE_USER_BY_ID, key = "#user.username.toLowerCase()")
    public int updateById(NeteaseUser user) {
        return neteaseUserDao.updateById(user);
    }

    public String selectMaxUserName(String namePrefix) {
        return neteaseUserDao.selectMaxUsername(namePrefix);
    }
}

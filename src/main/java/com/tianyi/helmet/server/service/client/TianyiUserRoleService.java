package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.dao.client.TianyiUserRoleDao;
import com.tianyi.helmet.server.entity.client.TianyiUserRole;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 田一用户角色信息服务
 *
 * Created by liuhanc on 2017/11/2.
 */
@Service
public class TianyiUserRoleService {
    @Autowired
    TianyiUserRoleDao tianyiUserRoleDao;


    public void insert(TianyiUserRole userRole){
        tianyiUserRoleDao.insert(userRole);
    }

    public TianyiUserRole selectById(int id){
        return tianyiUserRoleDao.selectById(id);
    }

    @Cacheable(value= CacheKeyConstants.USERROLE_BY_USER_ID,key="#userId.toString()", unless = "#result == null")
    public List<TianyiUserRole> selectByUserId(int userId){
        return tianyiUserRoleDao.selectByUserId(userId);
    }

    public List<TianyiUserRole> selectByRoleCode(String roleCode){
        return tianyiUserRoleDao.selectByRoleCode(roleCode);
    }

    @CacheEvict(value = CacheKeyConstants.USERROLE_BY_USER_ID,key="#userId.toString()")
    public int deleteByUserId(int userId){
        return tianyiUserRoleDao.deleteByUserId(userId);
    }

    @CacheEvict(value = CacheKeyConstants.USERROLE_BY_USER_ID,key="#userId.toString()")
    public int deleteByUserIdRoleCode(int userId,String roleCode){
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("roleCode",roleCode);
        return tianyiUserRoleDao.deleteByUserIdRoleCode(params);
    }
}

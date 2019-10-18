package com.tianyi.helmet.server.service.power.impl;

import com.tianyi.helmet.server.dao.power.RoleApiDao;
import com.tianyi.helmet.server.entity.power.RoleApi;
import com.tianyi.helmet.server.service.power.RoleApiService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色接口权限信息
 * Created by wenxinyan on 2018/10/10.
 */
@Service
public class RoleApiServiceImpl implements RoleApiService {
    @Autowired
    private RoleApiDao roleApiDao;

    @CacheEvict(value = CacheKeyConstants.POWER_ROLE_API, key = "#roleApi.roleId.toString()")
    public int insert(RoleApi roleApi) {
        return roleApiDao.insert(roleApi);
    }

    @CacheEvict(value = CacheKeyConstants.POWER_ROLE_API, key = "#roleApi.roleId.toString()")
    public int deleteById(RoleApi roleApi){
        return roleApiDao.deleteById(roleApi.getId());
    }

    public List<RoleApi> listAll(){
        return roleApiDao.listAll();
    }

    @Cacheable(value = CacheKeyConstants.POWER_ROLE_API, key = "#param.get('roleId').toString()")
    public List<RoleApi> listByNoPage(Map<String, Object> param){
        return roleApiDao.listByNoPage(param);
    }
}

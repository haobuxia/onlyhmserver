package com.tianyi.helmet.server.service.power.impl;

import com.tianyi.helmet.server.dao.power.ApiDao;
import com.tianyi.helmet.server.entity.power.Api;
import com.tianyi.helmet.server.service.power.ApiService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 接口信息
 * Created by wenxinyan on 2018/10/10.
 */
@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiDao apiDao;

    public List<Api> listAll(){
        return apiDao.listAll();
    }

    @Cacheable(value = CacheKeyConstants.POWER_API_BY_ID, key = "#id.toString()", unless = "#result == null")
    public Api selectById(int id){
        return apiDao.selectById(id);
    }

    public List<Api> listByNoPage(Map<String, Object> param){
        return apiDao.listByNoPage(param);
    }
}

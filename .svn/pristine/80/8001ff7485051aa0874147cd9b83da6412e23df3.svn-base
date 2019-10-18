package com.tianyi.helmet.server.service.client.impl;

import com.tianyi.helmet.server.dao.client.CompanyDao;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 单位管理服务
 * Created by wenxinyan on 2018/9/30.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    public int insert(Company company){
        return companyDao.insert(company);
    }

    public int deleteById(int id){
        return companyDao.deleteById(id);
    }

    public int update(Company company){
        return companyDao.update(company);
    }

    public List<Company> listBy(Map<String, Object> param){
        return companyDao.listBy(param);
    }

    public int countBy(Map<String, Object> param){
        return companyDao.countBy(param);
    }

    public Company selectById(int id){
        return companyDao.selectById(id);
    }

    public List<Company> listByNoPage(Map<String, Object> param){
        return companyDao.listByNoPage(param);
    }

    @CacheEvict(value = CacheKeyConstants.CUSTOMER_BY_MOBILE, key = "#mobile")
    public void clearByMobileCache(String mobile){
    }



}

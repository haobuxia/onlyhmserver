package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.CompanyNatureDao;
import com.tianyi.helmet.server.entity.dictionary.CompanyNature;
import com.tianyi.helmet.server.service.dictionary.CompanyNatureSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单位性质 字典表
 * Created by wenxinyan on 2018/9/30.
 */
@Service
public class CompanyNatureServiceImpl implements CompanyNatureSerivce {
    @Autowired
    private CompanyNatureDao companyNatureDao;

    public List<CompanyNature> listAll(){
        return companyNatureDao.listAll();
    }

    public CompanyNature selectById(int id) {
        return companyNatureDao.selectById(id);
    }
}

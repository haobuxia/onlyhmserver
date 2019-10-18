package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.CityDao;
import com.tianyi.helmet.server.entity.dictionary.City;
import com.tianyi.helmet.server.service.dictionary.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市 字典表
 * Created by wenxinyan on 2018/9/30.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    public List<City> listByProvinceId(int id){
        return cityDao.listByProvinceId(id);
    }

    public List<City> listAll() {
        return cityDao.listAll();
    }

    public City selectById(int id) {
        return cityDao.selectById(id);
    }
}

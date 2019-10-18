package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.ProvinceDao;
import com.tianyi.helmet.server.entity.dictionary.Province;
import com.tianyi.helmet.server.service.dictionary.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省 字典表
 * Created by wenxinyan on 2018/9/30.
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceDao provinceDao;

    public List<Province> listAll() {
        return provinceDao.listAll();
    }

    public Province selectById(int id) {
        return provinceDao.selectById(id);
    }
}

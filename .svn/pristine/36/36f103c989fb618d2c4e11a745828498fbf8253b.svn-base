package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.ModelDao;
import com.tianyi.helmet.server.service.dictionary.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 型号 字典表
 * Created by xiayuan on 2018/10/10.
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelDao modelDao;

    @Override
    public List<Map<Integer, String>> selectModel() {
        return modelDao.selectModel();
    }
    @Override
    public int insertModel(String model){
        return modelDao.insertModel(model);
    }
}

package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.BatchDao;
import com.tianyi.helmet.server.dao.dictionary.CategoryDao;
import com.tianyi.helmet.server.service.dictionary.BatchService;
import com.tianyi.helmet.server.service.dictionary.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 批次 字典表
 * Created by xiayuan on 2018/10/10.
 */
@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchDao batchDao;

    @Override
    public List<Map<Integer, String>> selectBatch() {
        return batchDao.selectBatch();
    }
    @Override
    public int insertBatch(String batch){
        return batchDao.insertBatch(batch);
    }
}

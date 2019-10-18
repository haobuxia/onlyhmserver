package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.StatusDao;
import com.tianyi.helmet.server.service.dictionary.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 状态 字典表
 * Created by xiayuan on 2018/9/30.
 */
@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusDao statusDao;

    @Override
    public List<Map<Integer, String>> selectStatus() {
        return statusDao.selectStatus();
    }
}

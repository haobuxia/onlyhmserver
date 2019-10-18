package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.VersionDao;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 版本 字典表
 * Created by xiayuan on 2018/9/30.
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionDao versionDao;
    @Override
    public List<Map<Integer, String>> selectVersion() {
        return versionDao.selectVersion();
    }
    @Override
    public List<Map<Integer, String>> selectVersion(int id) {
        return versionDao.selectVersion(id);
    }
    @Override
    public Integer getVersion(String version) {
        return versionDao.getVersion(version);
    }

    @Override
    public int addVersion(String version){
        return versionDao.addVersion(version);
    }
}

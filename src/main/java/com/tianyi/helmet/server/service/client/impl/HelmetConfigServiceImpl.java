package com.tianyi.helmet.server.service.client.impl;

import com.tianyi.helmet.server.dao.client.HelmetConfigDao;
import com.tianyi.helmet.server.entity.client.HelmetConfigInfo;
import com.tianyi.helmet.server.service.client.HelmetConfigService;
import com.tianyi.helmet.server.util.ConfigTreeUtil;
import com.tianyi.helmet.server.util.TreeUtil;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/8/19 10:53
 */
@Service
public class HelmetConfigServiceImpl implements HelmetConfigService {
    @Autowired
    private HelmetConfigDao helmetConfigDao;

    @Override
    public ResponseVo listConfigs(int uid) {
        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        List<HelmetConfigInfo> list = helmetConfigDao.listBy(param);
        List<HelmetConfigInfo> treeList = ConfigTreeUtil.buildByRecursive(list, -1);
        return ResponseVo.success(treeList);
    }
}

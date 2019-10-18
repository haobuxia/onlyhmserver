package com.tianyi.helmet.server.service.client.impl;

import com.tianyi.helmet.server.dao.client.HelmetUniversalurlDao;
import com.tianyi.helmet.server.entity.client.HelmetUniversalurlInfo;
import com.tianyi.helmet.server.service.client.HelmetUniversalurlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/8/19 10:53
 */
@Service
public class HelmetUniversalurlServiceImpl implements HelmetUniversalurlService {
    @Autowired
    private HelmetUniversalurlDao helmetUniversalurlDao;

    @Override
    public List<HelmetUniversalurlInfo> listUniversalurls(int uid, String urltype) {
        if(StringUtils.isEmpty(urltype) || uid < 0) {
            return new ArrayList<HelmetUniversalurlInfo>();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("urltype", urltype);
        return helmetUniversalurlDao.listBy(param);
    }
}

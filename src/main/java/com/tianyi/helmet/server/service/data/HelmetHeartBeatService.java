package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.HelmetHeartBeatDao;
import com.tianyi.helmet.server.entity.data.HelmetHeartBeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenxinyan on 2018/9/13.
 */
@Service
public class HelmetHeartBeatService {
    @Autowired
    private HelmetHeartBeatDao helmetHeartBeatDao;

    public void insert(HelmetHeartBeat helmetHeartBeat) {
        helmetHeartBeatDao.insert(helmetHeartBeat);
    }
}

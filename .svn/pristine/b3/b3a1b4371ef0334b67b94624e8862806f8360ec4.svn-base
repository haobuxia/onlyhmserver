package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.dao.svc.SvcVideoDao;
import com.tianyi.helmet.server.entity.svc.SvcVideo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务日志-视频服务
 * Created by liuhanc on 2018/3/19.
 */
@Service
public class SvcVideoService {
    @Autowired
    private SvcVideoDao svcVideoDao;

    private Logger logger = LoggerFactory.getLogger(SvcVideoService.class);

    public void insert(SvcVideo video) {
        svcVideoDao.insert(video);
    }

    public SvcVideo selectById(int id) {
        return svcVideoDao.selectById(id);
    }

    public int deleteById(int id){
        return svcVideoDao.deleteById(id);
    }

    public List<SvcVideo> selectByRwhOprtId(String rwh, String oprtId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("rwh", rwh);
        map.put("oprtId", oprtId);
        List<SvcVideo> list = svcVideoDao.listBy(map);
        return list;
    }

    public List<SvcVideo> selectByRwhTypeOprtId(String rwh, String videoType, String oprtId) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("rwh", rwh);
        map.put("videoType", videoType);
        map.put("oprtId", oprtId);
        List<SvcVideo> list = svcVideoDao.listBy(map);
        return list;
    }


}

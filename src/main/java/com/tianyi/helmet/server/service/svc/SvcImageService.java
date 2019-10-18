package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.dao.svc.SvcImageDao;
import com.tianyi.helmet.server.entity.svc.SvcImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务日志-图像服务
 * 
 * Created by liuhanc on 2018/3/19.
 */
@Service
public class SvcImageService {
    @Autowired
    private SvcImageDao svcImageDao;

    public void insert(SvcImage video) {
        svcImageDao.insert(video);
    }

    public SvcImage selectById(int id) {
        return svcImageDao.selectById(id);
    }

    public int deleteById(int id) {
        return svcImageDao.deleteById(id);
    }

    public List<SvcImage> selectByRwhOprtId(String rwh, String oprtId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("rwh", rwh);
        map.put("oprtId", oprtId);
        List<SvcImage> list = svcImageDao.listBy(map);
        return list;
    }

    public List<SvcImage> selectByRwhTypeOprtId(String rwh, String imageType, String oprtId) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("rwh", rwh);
        map.put("imageType", imageType);
        map.put("oprtId", oprtId);
        List<SvcImage> list = svcImageDao.listBy(map);
        return list;
    }

}

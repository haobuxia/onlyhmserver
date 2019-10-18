package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.dao.svc.SvcAudioDao;
import com.tianyi.helmet.server.entity.svc.SvcAudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务日志声音服务
 * 
 * Created by liuhanc on 2018/3/19.
 */
@Service
public class SvcAudioService {
    @Autowired
    private SvcAudioDao svcAudioDao;

    public void insert(SvcAudio audio) {
        svcAudioDao.insert(audio);
    }

    public SvcAudio selectById(int id) {
        return svcAudioDao.selectById(id);
    }

    public int deleteById(int id) {
        return svcAudioDao.deleteById(id);
    }

    public List<SvcAudio> selectByRwhOprtId(String rwh, String oprtId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("rwh", rwh);
        map.put("oprtId", oprtId);
        List<SvcAudio> list = svcAudioDao.listBy(map);
        return list;
    }

    public List<SvcAudio> selectByRwhTypeOprtId(String rwh, String audioType, String oprtId) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("rwh", rwh);
        map.put("audioType", audioType);
        map.put("oprtId", oprtId);
        List<SvcAudio> list = svcAudioDao.listBy(map);
        return list;
    }

}

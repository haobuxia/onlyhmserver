package com.tianyi.helmet.server.dao.svc;

import com.tianyi.helmet.server.entity.svc.SvcAudio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 服务日志-音频
 * Created by liuhanc on 2018/3/19.
 */
@Repository
public interface SvcAudioDao {

    void insert(SvcAudio audio);

    List<SvcAudio> listBy(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    SvcAudio selectById(int id);

    int deleteById(int id);

}

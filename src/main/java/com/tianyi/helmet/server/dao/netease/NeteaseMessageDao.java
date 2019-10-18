package com.tianyi.helmet.server.dao.netease;

import com.tianyi.helmet.server.entity.netease.NeteaseMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 网易消息接口
 *
 * Created by liuhanc on 2017/10/16.
 */
@Repository
public interface NeteaseMessageDao {
    void insert(NeteaseMessage msg);
    NeteaseMessage selectById(int id);
    List<NeteaseMessage> selectBy(Map<String,Object> params);
    int countBy(Map<String,Object> params);
    int updateMessageProcessFlag(Map<String,Object> params);
}

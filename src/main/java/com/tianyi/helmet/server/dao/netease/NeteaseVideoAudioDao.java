package com.tianyi.helmet.server.dao.netease;

import com.tianyi.helmet.server.entity.netease.NeteaseVideoAudio;
import org.springframework.stereotype.Repository;

/**
 * 网易消息接口
 *
 * Created by liuhanc on 2017/10/16.
 */
@Repository
public interface NeteaseVideoAudioDao {
    void insert(NeteaseVideoAudio msg);
    NeteaseVideoAudio selectById(int id);
    NeteaseVideoAudio selectByChannelId(long channelId);
}

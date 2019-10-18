package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.VideoDataExtend;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 视频信息扩展
 */
@Repository
public interface VideoDataExtendDao {
    void insert(VideoDataExtend video);

    VideoDataExtend selectByVideoId(int videoId);

    int updateOrigTextByVideoId(Map<String, Object> params);

    int updateEditTextByVideoId(Map<String, Object> params);

    int deleteByVideoId(int videoId);
}

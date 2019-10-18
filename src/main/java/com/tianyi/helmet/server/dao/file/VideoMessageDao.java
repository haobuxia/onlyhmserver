package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.VideoMessage;
import com.tianyi.helmet.server.entity.file.VideoOrder;
import org.springframework.stereotype.Repository;

/**
 * 视频信息扩展-工单信息
 */
@Repository
public interface VideoMessageDao {
    int insert(VideoMessage videoMessage);
}

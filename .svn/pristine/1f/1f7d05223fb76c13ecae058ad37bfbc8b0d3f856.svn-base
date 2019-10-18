package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.VideoDataExtendDao;
import com.tianyi.helmet.server.entity.file.VideoDataExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  视频信息扩展服务
 *
 * Created by liuhanc on 2018/5/25.
 */
@Service
public class VideoDataExtendService {

    @Autowired
    private VideoDataExtendDao videoDataExtendDao;

    public void insert(VideoDataExtend video) {
        if(videoDataExtendDao.selectByVideoId(video.getVideoId())!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("audioOrigText",video.getAudioOrigText());
            map.put("videoId",video.getVideoId());
            videoDataExtendDao.updateOrigTextByVideoId(map);
        }else{
            videoDataExtendDao.insert(video);
        }

    }

    public VideoDataExtend selectByVideoId(int videoId) {
        return videoDataExtendDao.selectByVideoId(videoId);
    }

    public int updateOrigTextByVideoId(String origText, int videoId) {
        Map<String, Object> params = new HashMap<>();
        params.put("audioOrigText", origText);
        params.put("videoId", videoId);
        return videoDataExtendDao.updateOrigTextByVideoId(params);
    }

    public int updateEditTextByVideoId(String editText, int videoId) {
        Map<String, Object> params = new HashMap<>();
        params.put("audioEditText", editText);
        params.put("videoId", videoId);
        return videoDataExtendDao.updateEditTextByVideoId(params);
    }

    public int deleteByVideoId(int videoId) {
        return videoDataExtendDao.deleteByVideoId(videoId);
    }
}

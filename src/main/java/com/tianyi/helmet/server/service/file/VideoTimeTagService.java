package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.VideoTimeTagDao;
import com.tianyi.helmet.server.entity.file.VideoTimeTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频时间戳
 *
 * Created by liuhanc on 2018/3/2.
 */
@Service
public class VideoTimeTagService {
    @Autowired
    private VideoTimeTagDao videoTimeTagDao;

    public void insert(List<VideoTimeTag> videoTimeTagList){
        videoTimeTagDao.batchInsert(videoTimeTagList);
    }

    public void insert(VideoTimeTag videoTimeTag){
        videoTimeTagDao.insert(videoTimeTag);
    }

    public VideoTimeTag selectById(int id){
        return videoTimeTagDao.selectById(id);
    }

    public int deleteById(int id){
        return videoTimeTagDao.deleteById(id);
    }

    public List<VideoTimeTag> selectByVideoId(int videoId){
        return videoTimeTagDao.selectByVideoId(videoId);
    }

    public List<VideoTimeTag> selectByVideoName(String videoName){
        String newVideoName = processVideoName(videoName);
        return videoTimeTagDao.selectByVideoName(newVideoName);
    }

    public List<VideoTimeTag> listBy(Map<String, Object> map){
        return videoTimeTagDao.listBy(map);
    }

    public int countBy(Map<String, Object> map){
        return videoTimeTagDao.countBy(map);
    }

    public int updateVideoIdByName(int videoId,String videoName){
        String newVideoName = processVideoName(videoName);
        Map<String,Object> map = new HashMap<>();
        map.put("videoId",videoId);
        map.put("videoName",newVideoName);
        return videoTimeTagDao.updateVideoIdByName(map);
    }

    private String processVideoName(String videoName){
        int dotIdx = videoName.indexOf(".");
        if(dotIdx != -1){
            videoName = videoName.substring(0,dotIdx);//去掉扩展名
        }
        return videoName;
    }
}

package com.tianyi.helmet.server.service.job;


import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.file.VideoComponent;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 基于mq的 视频合成带字幕的版本
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class TrackVideoProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(TrackVideoProcessJob.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoComponent videoComponent;
    @Autowired
    private TyBoxDataService tyBoxDataService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("TrackVideoProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer videoId = Integer.parseInt(body);
            Video v = videoService.selectById(videoId);
            processOneData(v);
        } catch (Exception e) {
            logger.error("处理视频队列消息异常.", e);
        }
    }

    /**
     * 处理某个视频
     *
     * @param v
     */
    public boolean processOneData(Video v) {
        //字幕合成
        boolean prepareOk = doPrepare(v);
        if (!prepareOk)
            return false;

        int oldState = v.getState();
        int result = videoService.updateState(v.getId(), VideoStateEnum.TRACKING);
        if (result != 1) {
            logger.debug("设置视频字幕合并中状态失败...id=" + v.getId());
            return false;
        } else {
            logger.debug("设置视频字幕合并中状态成功...id=" + v.getId());
        }

        try {
            boolean success = videoComponent.doTrack(v);
            return success;
        } catch (Exception e) {
            //结束后,视频状态要么是成功，要么是字幕合并失败，不能是字幕合并中，如果是字幕合并中，表明出现了异常
            videoService.updateState(v.getId(), VideoStateEnum.get(oldState));
            return false;
        }
    }


    public boolean doPrepare(Video v) {
        if (v.getHasGpsData() != null && v.getHasGpsData() == 0) {
            logger.debug("视频标志无字幕数据,不生成.id=" + v.getId());
            return false;
        }
        if (!StringUtils.isEmpty(v.getTrackVideoOssPath())) {
            logger.debug("视频已经有字幕版,不生成.id=" + v.getId());
            return false;
        }
        int oldState = v.getState();
        if (oldState == VideoStateEnum.TRACKING.getState()) {
            logger.warn("视频正在生成字幕中,不处理.id=" + v.getId());
            return false;
        }

        DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> doubleVo = tyBoxDataService.selectGpsDataListByVideo(v);
        Map<Integer, List<DoubleVo<Long, Integer>>> typeMapData = doubleVo.getVal();
        if (CollectionUtils.isEmpty(typeMapData)) {
            //没有数据
            logger.warn("视频字幕数据不存在,不处理.id=" + v.getId());
            v.setHasGpsData(0);
            v.setImei(null);
            videoService.updateHasGpsDataById(v);
            return false;
        }

        v.setHasGpsData(1);
        v.setImei(doubleVo.getKey());
        videoService.updateHasGpsDataById(v);
        return true;
    }

}

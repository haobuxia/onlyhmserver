package com.tianyi.helmet.server.service.job;


import com.tianyi.helmet.server.entity.data.GpsData;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基于mq的 视频字幕数据同步到oss
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class SyncVideoGpsDataJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(SyncVideoGpsDataJob.class);

    @Autowired
    VideoService videoService;
    @Autowired
    private TyBoxDataService tyBoxDataService;
    @Autowired
    private KmxService kmxService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("SyncVideoGpsDataJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
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
    public void processOneData(Video v) {
        //字幕合成
        boolean prepareOk = doPrepare(v);
        if (!prepareOk)
            return;

        int oldState = v.getState();
        int result = videoService.updateState(v.getId(), VideoStateEnum.SYNC_DATA);//标志为正在同步中
        if (result != 1) {
            logger.debug("设置视频数据同步中状态失败...id=" + v.getId());
            return;
        } else {
            logger.debug("设置视频数据同步中状态成功...id=" + v.getId());
        }

        try {
           doSync(v);
        } catch (Exception e) {
            logger.error("同步二手机视频的数据异常.v.id="+v.getId(),e);
        }finally {
            videoService.updateState(v.getId(), VideoStateEnum.get(oldState));//同步结束标志状态为原始状态
        }
    }

    public void doSync(Video v){
        LocalDateTime time1 = v.getCreateTime();
        LocalDateTime time2 = time1.plusSeconds(v.getSeconds());
        List<GpsData> list = tyBoxDataService.selectKeyDataFromMysqlBy(v.getClientId(),time1,time2);
        logger.debug("mysql 视频传感器数据量:"+list.size()+",v.id="+v.getId());
        int successCount = list.stream().mapToInt(gpsData -> {
            boolean success = kmxService.insertGpsData(gpsData);
            return success?1:0;
        }).sum();
        logger.debug("结束同步字幕视频数据,v.id="+v.getId()+",原始数据量:"+list.size()+",成功数据量:"+successCount);
        if(successCount > 0 ){
            tyBoxDataService.clearVideoGpsDataCache(v.getId());
        }
    }

    public boolean doPrepare(Video v) {
        if (v.getHasGpsData() != null && v.getHasGpsData() == 0) {
            logger.debug("视频标志无字幕数据,不同步.id=" + v.getId());
            return false;
        }

        int oldState = v.getState();
        if (oldState == VideoStateEnum.SYNC_DATA.getState()) {
            logger.warn("视频正在同步数据中,不处理.id=" + v.getId());
            return false;
        }
        return true;
    }

}

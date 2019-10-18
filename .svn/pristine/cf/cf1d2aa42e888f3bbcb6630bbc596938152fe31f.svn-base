package com.tianyi.helmet.server.service.job;


import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.support.FfmpegService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 合并视频和音频工作
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class MergeVideoAudioJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(MergeVideoAudioJob.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private FfmpegService ffmpegService;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("MergeVideoAudioJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            VideoAudioMergePo mergePo = JSON.parseObject(body, VideoAudioMergePo.class);
            processOneData(mergePo);
        } catch (Exception e) {
            logger.error("处理视频音频合并队列消息异常.", e);
        }
    }

    /**
     * 处理某个合并
     */
    public void processOneData(VideoAudioMergePo merge) {
        File mergeVideo = uploadEntityServiceFactory.getSavedFile(UploadEntityTypeEnum.video, "videoaudio" + File.separator + merge.getOrigFileName());
        boolean success = ffmpegService.mergeVidioAudio(merge.getVideoFilePath(), merge.getAudioFilePath(), mergeVideo.getAbsolutePath());
        if (!success) {
            logger.error("合并视频音频异常." + merge.getOrigFileName());
            return;
        }

        //Object fileInstanceOrFileBytes, String origName, String suffix, String description, LocalDateTime createTimeDT,
        //String imei, String src, String machineCode, String tag,String orderNo
        /**
         * update by xiayuan 2018/10/11
         */
        String imei = merge.getClientId();
        EquipmentLedger equipmentLedger =equipmentService.selectByUUID(imei);
        User user = userService.selectById(equipmentLedger.getUserId());
        ResponseVo vo = videoService.addNewFile(mergeVideo, merge.getOrigFileName(), "mp4", merge.getDescription(), merge.getCreateTime(),equipmentLedger.getUserId(), imei,user.getNeUserHel(), "upload", merge.getMachineCode(), merge.getTag(), merge.getOrderNo(), null, null, "", "", "");
        if (vo.isSuccess()) {
            logger.info("合并视频音频成功并入库存储." + merge.getOrigFileName());
        } else {
            logger.error("合并视频音频成功但入库存储时异常." + merge.getOrigFileName() + ".msg=" + vo.getMessage());
        }
    }


}

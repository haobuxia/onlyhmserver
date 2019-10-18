package com.tianyi.helmet.server.service.job;


import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.support.FfmpegService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * 基于mq的 视频处理工作
 * 0 时长、预览图截取等
 * 1 视频压缩和格式转换
 * 2 视频存储到oss
 * 3 视频合成字幕并保存到oss
 * 4.视频拍摄位置定位信息补全
 * 如果是服务数据视频，则还需要：（20180710改为所有视频都这么干）
 * 1 提取音频文件
 * 2 音频文件识别成文字
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class NewVideoProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(NewVideoProcessJob.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private FfmpegService ffmpegService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private PreviewVideoProcessJob previewVideoProcessJob;
    @Autowired
    private TrackVideoProcessJob trackVideoProcessJob;
    @Autowired
    private VideoAsrKeywordProcessJob videoAsrKeywordProcessJob;
    @Autowired
    private VideoWorkOrderProcessJob videoWorkOrderProcessJob;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("NewVideoProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
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
        boolean prepareOk = doPrepare(v);
        if (!prepareOk)
            return;

        // 发信令通知视频已上传成功
        if(!org.apache.commons.lang.StringUtils.isEmpty(v.getOrderNo())) {
            videoService.noticeToThird(v);
        }
        //视频时长、预览图获取
        previewVideoProcessJob.processOneData(v, true, false);
        //doTranscode(v);

//        if (videoComponent.isSvcDataVideo(v)) {
        //服务数据视频.则提取视频中声音，识别并拆分出关键词.20180709 改为所有视频都自动识别其中的声音
        videoAsrKeywordProcessJob.processOneData(v);

//        }

        //字幕合成
//        try {
//            boolean success = trackVideoProcessJob.processOneData(v);
//            if (!success) {
//                //合成失败或者不必合成字幕则删除临时文件 @todo 定时任务定时清理目录
//            }
//        } catch (Exception e) {
//            logger.error("合成字幕版视频异常.id=" + v.getId(), e);
//        }
        // 根据工作卡id生成工单信息
        videoWorkOrderProcessJob.doProcessVideoAsr(v);
        // 添加拆分关键词并打标任务
        videoService.addToKeyWordProcessQueue(v.getId());

        // 保存视频的地理位置信息
        videoService.saveLocation(v);
    }

    /**
     * 工作准备
     * 锁定视频处理中状态
     *
     * @param v
     * @return
     */
    private boolean doPrepare(Video v) {
        //核查数据
        int oldState = v.getState();
        if (oldState != VideoStateEnum.NOT_PROC.getState()) {
            logger.error("上传视频的状态不是待处理,所以不予处理.id=" + v.getId());
            return false;
        }
        if (StringUtils.isEmpty(v.getOssPath())) {
            logger.error("视频的oss存储路径为空无法处理.id=" + v.getId());
            return false;
        }

        //设置状态
        int result = videoService.updateState(v.getId(), VideoStateEnum.PROCING);
        if (result != 1) {
            logger.debug("设置视频处理中状态失败...id=" + v.getId());
            return false;
        } else {
            logger.debug("设置视频处理中状态成功...id=" + v.getId());
            return true;
        }
    }


    /**
     * 处理处理(转码、加水印等)
     *
     * @param v
     * @return
     */
    public File doVideoTranscode(Video v, boolean deleteTempVideoFile) {
        File waterMarkFile = null;
        if (!StringUtils.isEmpty(configService.getVideoWaterMarkFilePath())) {
            waterMarkFile = new File(configService.getVideoWaterMarkFilePath());
        }

        String destFilePath = v.getFileName() + ".transcode.mp4";
        File destVideo = new File(configService.getUploadFileSaveDir(), configService.getUploadVideoDir() + File.separator + destFilePath);
        logger.debug("开始视频转码处理.id=" + v.getId() + ",destVideo=" + destVideo.getAbsolutePath());
        File destParent = destVideo.getParentFile();
        if (!destParent.exists()) {
            destParent.mkdirs();
        }

        File tempVideoFile = videoService.getVideoFile(v);
        boolean success = false;
        try {
            success = ffmpegService.videoTransCode(tempVideoFile, destVideo, waterMarkFile);
        } catch (Exception e) {
            logger.error("xxxx", e);
        } finally {
            if (deleteTempVideoFile)
                tempVideoFile.delete();
        }

        if (success) {
            if (destVideo.exists()) {
                logger.debug("视频转码处理成功.id=" + v.getId() + ".size=" + destVideo.length());
                return destVideo;
            } else {
                logger.debug("视频转码处理失败.处理后视频文件不存在或者大小为0.id=" + v.getId());
                destVideo.delete();//删除转化失败后生成的文件,可能并不存在
                return null;
            }
        } else {
            logger.debug("视频转码处理失败.id=" + v.getId());
            destVideo.delete();//删除转化失败后生成的文件,可能并不存在
            return null;
        }
    }

}

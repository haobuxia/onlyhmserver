package com.tianyi.helmet.server.service.job;


import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.support.FfmpegService;
import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 基于mq的 视频时长和截取预览图
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class PreviewVideoProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(PreviewVideoProcessJob.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private FfmpegService ffmpegService;
    @Autowired
    private FastDfsClient fastDfsClient;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("PreviewVideoProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer videoId = Integer.parseInt(body);
            Video v = videoService.selectById(videoId);
            processOneData(v, false, true);
        } catch (Exception e) {
            logger.error("处理视频队列消息异常.", e);
        }
    }

    /**
     * 处理某个视频
     *
     * @param v
     */
    public boolean processOneData(Video v, boolean keepVideoTempFileWhenSuccess, boolean restoreOldState) {
        //时长和截图
        int oldState = v.getState();
        boolean prepareOk = doPrepare(v);
        if (!prepareOk)
            return false;

        boolean success = getTimeCreatePreviewImage(v, keepVideoTempFileWhenSuccess);
        if (success) {
            if (restoreOldState && v.getState() != oldState) {
                videoService.updateState(v.getId(), VideoStateEnum.get(oldState));
            }
            v.setState(VideoStateEnum.SUCCESS.getState());
            return true;
        }else{
            v.setState(VideoStateEnum.FAIL.getState());
            return false;
        }


    }


    /**
     * 获取时长、创建预览图
     */
    protected boolean getTimeCreatePreviewImage(Video v, boolean keepVideoTempFileWhenSuccess) {
        //时长、预览图不存在，则需要进行处理
        if (v.getSeconds() >= 0 && !StringUtils.isEmpty(v.getPicOssPath())) {
            return false;
        }

        File tempVideoFile = videoService.getVideoFile(v);
        if (tempVideoFile == null) {
            logger.error("获取视频时长、截图时，临时视频拉取失败.v.id=" + v.getId());
            return false;
        }

        boolean success = true;
        boolean needSaveVideo = false;
        if (v.getSeconds() == -1) {
            //ffmpeg
            long seconds = 0;
            try {
                seconds = ffmpegService.getVideoTime(tempVideoFile);
                logger.info("处理视频文件获得视频时长：" + seconds + ",id=" + v.getId());
                v.setSeconds(seconds);
                needSaveVideo = true;
                success = true;
            } catch (Exception e) {
                logger.error("ffmpeg获取视频时长信息异常", e);
                success = false;
            }
        }

        if (StringUtils.isEmpty(v.getPicOssPath())) {
            File previewImgFile = new File(tempVideoFile.getAbsolutePath() + "-preview.jpg");
            logger.debug("处理视频文件生成截图.videoFilePath=" + tempVideoFile.getAbsolutePath());

            int previewImgPosSecond = v.getSeconds() > 1 ? 1 : 0;//截中间时刻的图.测试发现视频很小时，时长为6秒，截取第3秒会失败。截取0,1秒可以成功
            logger.info("file get previewImage ... ");
            try {
                ffmpegService.previewImage(tempVideoFile, previewImgFile, previewImgPosSecond);
                logger.debug("previewImgFile.length()::::::::::::::"+previewImgFile.length());
                logger.debug("previewImgFile.exists()::::::::::::::"+previewImgFile.exists());
                if (!previewImgFile.exists() || previewImgFile.length() == 0) {
                    logger.error("ffmpeg获取视频预览图片失败.文件不存在或大小为0.v.id=" + v.getId());
                    success = false;
                } else {
                    //获取预览图成功，则把预览图存入oss
                    String picOssPath = fastDfsClient.uploadFile(previewImgFile, "jpg");
                    ByteArrayOutputStream byos = new ByteArrayOutputStream();
                    Streams.copy(new FileInputStream(previewImgFile),byos,false);
                    String thumbOssPath = videoService.makePicThumbToOss(byos.toByteArray());
                    previewImgFile.delete();//删除截取到的预览图本地文件

                    needSaveVideo = true;
                    v.setPicOssPath(picOssPath);
                    v.setThumbOssPath(thumbOssPath);
                    success = true;
                }
            } catch (Exception e) {
                logger.error("ffmpeg获取视频预览图片异常", e);
                success = false;
            }
        }

        if (needSaveVideo) {
            videoService.updateById(v);
        }

        if (success && !keepVideoTempFileWhenSuccess) {
            tempVideoFile.delete();
        }
        return success;
    }

    public boolean doPrepare(Video v) {
        logger.debug("PreviewVideoProcessJob doPrepare start");
        int oldState = v.getState();
        if (oldState != VideoStateEnum.PROCING.getState()) {
            //设置状态
            int result = videoService.updateState(v.getId(), VideoStateEnum.PROCING);
            if (result != 1) {
                logger.debug("设置视频处理中状态失败...id=" + v.getId());
                logger.debug("PreviewVideoProcessJob doPrepare end");
                return false;
            } else {
                logger.debug("设置视频处理中状态成功...id=" + v.getId());
                v.setState(VideoStateEnum.PROCING.getState());
                logger.debug("PreviewVideoProcessJob doPrepare end");
                return true;
            }
        }
        logger.debug("PreviewVideoProcessJob doPrepare end");
        return true;
    }

}

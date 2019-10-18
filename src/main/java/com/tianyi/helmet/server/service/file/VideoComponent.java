package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.entity.file.*;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.support.FfmpegService;
import com.tianyi.helmet.server.service.track.TrackService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 *  视频组件
 *
 * Created by liuhanc on 2018/4/20.
 */
@Component
public class VideoComponent {
    private Logger logger = LoggerFactory.getLogger(VideoComponent.class);
    @Autowired
    private TrackService trackService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoKeywordService videoKeywordService;
    @Autowired
    private FfmpegService ffmpegService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private GetOrCreateComponent getOrCreateComponent;

    /**
     * 检查并生成字幕和内嵌字幕的视频，存入oss记录到数据库
     *
     * @param v
     */
    public boolean doTrack(Video v) {
        File trackVideoFile = new File(configService.getUploadFileSaveDir(), configService.getUploadVideoDir() + File.separator + v.getId() + ".withtrack.mp4");
        if (trackVideoFile.exists() && trackVideoFile.length() > 0 && StringUtils.isEmpty(v.getTrackVideoOssPath())) {
            //直接上传oss即可
            logger.debug("生成字幕版视频时发现字幕版视频已存在,则直接使用.v.id=" + v.getId() + ",file=" + trackVideoFile.getAbsolutePath());
        } else {
            //生成
            boolean success = createNewTrackVideoFile(v, trackVideoFile);
            if (!success) {
                return false;
            }
        }

        String trackVideoOssPath = fastDfsClient.uploadFile(trackVideoFile, "mp4");
        if (!StringUtils.isEmpty(trackVideoOssPath)) {
            logger.debug("更新字幕版视频地址.v.id=" + v.getId() + ",trackVideoOssPath=" + trackVideoOssPath);
            videoService.updateTrackVideoOssPathById(trackVideoOssPath, v.getId());//同时也会更新state
            trackVideoFile.delete();
            return true;
        } else {
            logger.error("上传内嵌字幕视频文件到oss失败.v.id=" + v.getId() + ",带字幕视频文件=" + trackVideoFile.getAbsolutePath());
            videoService.updateState(v.getId(), VideoStateEnum.TRACK_FAIL);
            return false;
        }
    }

    //生成带字幕的视频文件并存储到参数文件里
    protected boolean createNewTrackVideoFile(Video v, File trackVideoFile) {
        File trackFile = new File(configService.getUploadFileSaveDir(), configService.getUploadVideoDir() + File.separator + v.getId() + ".srt");
        if (!trackFile.exists() || trackFile.length() == 0) {
            String trackContent = trackService.make(v, TrackService.SRT_NAME);
            try {
                FileUtils.write(trackFile, trackContent, "UTF-8");
                if (!trackFile.exists() || trackFile.length() == 0) {
                    logger.error("utf-8格式输出字幕文件异常,输出后文件不存在或大小为0." + trackFile.getAbsolutePath() + ".\r\ntrackContent=" + trackContent);
                    videoService.updateState(v.getId(), VideoStateEnum.TRACK_FAIL);
                    return false;
                } else {
                    logger.debug("utf-8格式输出字幕文件成功." + trackFile.getAbsolutePath() + ",文件大小:" + trackFile.length());
                }
            } catch (Exception e) {
                logger.error("utf-8格式输出字幕文件异常", e);
                videoService.updateState(v.getId(), VideoStateEnum.TRACK_FAIL);
                return false;
            }
        } else {
            logger.error("字幕文件已存在." + trackFile.getAbsolutePath() + ",文件大小:" + trackFile.length());
        }

        File noTrackVideoFile = videoService.getVideoFile(v);

        if (!noTrackVideoFile.exists()) {
            logger.info("生成字幕版视频时发现找不到原始可用视频.v.id=" + v.getId());
            videoService.updateState(v.getId(), VideoStateEnum.TRACK_FAIL);
            return false;
        }

        boolean success = ffmpegService.videoAddSubtitlies(noTrackVideoFile, trackFile, trackVideoFile);
        if (!success) {
            //合并字幕失败
            logger.error("合并视频字幕失败.v.id=" + v.getId() + ",原始视频文件=" + noTrackVideoFile.getAbsolutePath() + ",字幕文件=" + trackFile.getAbsolutePath());
            videoService.updateState(v.getId(), VideoStateEnum.TRACK_FAIL);
            return false;
        }

        //合并字幕成功，则删除字幕，删除原始视频
        logger.debug("字幕合并成功.v.id=" + v.getId() + ",trackVideo=" + trackVideoFile.getAbsolutePath());
        trackFile.delete();
        noTrackVideoFile.delete();
        return true;
    }

    /**
     * 判断视频是否是 服务系统数据 视频
     *
     * @param video
     * @return
     */
    public boolean isSvcDataVideo(Video video) {
        if (VideoCategoryEnum.SVCDATA.toString().equalsIgnoreCase(video.getVideoType())) {
            return true;
        }
        return false;
    }


    /**
     * 为视频添加1个关键词
     *
     * @param keywordStr
     * @param videoId
     */
    @Transactional
    public ResponseVo<VideoKeyword> addKeyword(String keywordStr, int videoId) {
        KeyWord keyWord = getOrCreateComponent.getOrCreateByKeywordName(keywordStr);
        List<VideoKeyword> list = videoKeywordService.selectByVideoId(videoId);
        boolean exists = list.stream().filter(vk -> vk.getKeywordId() == keyWord.getId()).findAny().isPresent();
        if (exists) {
            return ResponseVo.fail("视频已具备关键词:" + keywordStr);
        }

        VideoKeyword vk = new VideoKeyword(videoId, keyWord.getId(), keyWord.getKeywordName());
        videoKeywordService.insert(vk);
        return ResponseVo.success(vk);
    }

}

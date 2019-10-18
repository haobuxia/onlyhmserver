package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.svc.SvcVideo;
import com.tianyi.helmet.server.entity.svc.SvcVideoTypeEnum;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.support.FfmpegService;
import com.tianyi.helmet.server.service.svc.SvcMetaDataHelper;
import com.tianyi.helmet.server.service.svc.SvcResUploadHelper;
import com.tianyi.helmet.server.service.svc.SvcVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务日志视频后续处理
 * <p>
 * Created by liuhanc on 2018/4/2.
 */
@Component
public class SvcVideoParseJob implements MessageListener {
    @Autowired
    private SvcVideoService svcVideoService;
    @Autowired
    private SvcResUploadHelper svcResUploadHelper;
    @Autowired
    private FfmpegService ffmpegService;
    @Autowired
    private FastDfsClient fastDfsClient;

    private Logger logger = LoggerFactory.getLogger(SvcVideoParseJob.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("SvcVideoParseJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer id = Integer.parseInt(body);
            SvcVideo video = svcVideoService.selectById(id);
            doParse(video, false);//不强制刷新
        } catch (Exception e) {
            logger.error("处理服务日志视频解析队列消息异常.", e);
        }
    }

    public void doParse(SvcVideo video, boolean force) {
        if (video == null) {
            logger.error("视频信息不存在，不处理");
            return;
        }
        //之前上传的同类型视频要删除掉
        List<SvcVideo> resList = svcVideoService.selectByRwhTypeOprtId(video.getRwh(), video.getVideoType(), video.getOprtId());
        if (!CollectionUtils.isEmpty(resList)) {
            resList.stream().filter(tempVideo -> tempVideo.getId() != video.getId()).forEach(tempVideo -> {
                //其他同类型的资源要删除
                //删除记录、删除视频、删除生成的图片
                boolean success = false;
                try {
                    deleteSvcRes(tempVideo);
                    success = true;
                } catch (Exception e) {
                    success = false;
                    logger.error("删除之前上传的同类型视频.id="+tempVideo.getId()+",rwh=" + video.getRwh() + ",videoType=" + video.getVideoType() + ",oprtId=" + video.getOprtId() + ",异常", e);
                } finally {
                    logger.debug("删除之前上传的同类型视频.id="+tempVideo.getId()+",rwh=" + video.getRwh() + ",videoType=" + video.getVideoType() + ",oprtId=" + video.getOprtId() + ",结果=" + (success ? "成功" : "失败"));
                }
            });
        }

        //site,digger,faultCheck,faultRepaird
        String videoType = video.getVideoType();
        if (SvcVideoTypeEnum.site.toString().equals(videoType) || SvcVideoTypeEnum.faultRepaird.toString().equals(videoType)) {
            logger.debug("工地/故障修复 类型视频不处理.videoType = " + video.getVideoType() + ",video.id=" + video.getId());
            return;//工地视频暂时无用
        }

        File videoFile = svcResUploadHelper.getSvcResFile(video);
        if (videoFile == null || !videoFile.exists()) {
            logger.error("视频文件不存在，不处理.videoType = " + video.getVideoType() + ",video.id=" + video.getId() + ",file=" + videoFile.getAbsolutePath());
            return;
        }

        try {
            extractSvcPic(video, videoFile);
        } catch (Exception e) {
            logger.error("处理上传的服务日志视频异常.video.id=" + video.getId() + ",rwh=" + video.getRwh() + ",svcId=" + video.getSvcId() + ",oprtId=" + video.getOprtId(), e);
        } finally {
            videoFile.delete();
        }
    }

    private void deleteSvcRes(SvcVideo v) {
        String videoType = v.getVideoType();
        if (SvcVideoTypeEnum.site.toString().equals(videoType) || SvcVideoTypeEnum.faultRepaird.toString().equals(videoType)) {
        } else {
            //删除生成的图片
            svcResUploadHelper.deleteSvcPics(v);
        }

        //删除数据
        svcVideoService.deleteById(v.getId());

        //删除文件
        String ossPath = v.getOssPath();
        if (StringUtils.isEmpty(ossPath)) {
            return;
        }
        fastDfsClient.deleteFile(ossPath);
    }


    //提取服务日志需要的照片并上传给服务日志系统
    private void extractSvcPic(SvcVideo video, File videoFile) {
        String videoType = video.getVideoType();//digger,faultCheck,faultRepaird
        if (SvcVideoTypeEnum.digger.toString().equals(videoType)) {
            //挖机视频，作为故障的 故障照片-整机的来源,取第1秒的截图
            File imgFile = previewImage(videoFile, 0);
            SvcMetaDataHelper.SvcFaultPicMeta svcFaultPicMeta = SvcMetaDataHelper.SvcFaultPicMeta.ZHENGJI;
            try {
                createAndSaveSvcPic(video, imgFile, svcFaultPicMeta);
            } catch (Exception e) {
                logger.error("处理生成的服务日志照片异常.rwh=" + video.getRwh() + ",svcId=" + video.getSvcId() + ",oprtId=" + video.getOprtId() + ",imageType=" + svcFaultPicMeta, e);
            }
        } else if (SvcVideoTypeEnum.faultCheck.toString().equals(videoType)) {
            //故障视频，作为故障的 故障照片-检测、故障照片-中、远、近 3张 合计4张照片的来源
            //从远到近拍的故障视频，第0秒作为远景、最后1秒作为近景，中间秒作为中景,倒数第2秒作为检测图
            int time = ffmpegService.getVideoTime(videoFile);
            Map<Integer, SvcMetaDataHelper.SvcFaultPicMeta> secondMap = new HashMap(4);
            secondMap.put(0, SvcMetaDataHelper.SvcFaultPicMeta.YUANJING);//第0秒作为远景
            secondMap.put(time - 1, SvcMetaDataHelper.SvcFaultPicMeta.JINJING);//最后1秒作为近景
            secondMap.put(time / 2, SvcMetaDataHelper.SvcFaultPicMeta.ZHONGJING);//中间秒作为中景
            secondMap.put(time - 2, SvcMetaDataHelper.SvcFaultPicMeta.JIANCE);//倒数第2秒作为检测图

            secondMap.keySet().stream().forEach(second -> {
                File imgFile = previewImage(videoFile, second);
                SvcMetaDataHelper.SvcFaultPicMeta svcFaultPicMeta = secondMap.get(second);
                try {
                    createAndSaveSvcPic(video, imgFile, svcFaultPicMeta);
                } catch (Exception e) {
                    logger.error("处理生成的服务日志照片异常.rwh=" + video.getRwh() + ",svcId=" + video.getSvcId() + ",oprtId=" + video.getOprtId() + ",imageType=" + svcFaultPicMeta, e);
                }
            });
        }
    }

    private void removeSvcPic(String rwh, String svcId, SvcMetaDataHelper.SvcFaultPicMeta svcFaultPicMeta) {

    }

    //生成服务日志图片记录并上传图片
    private void createAndSaveSvcPic(SvcVideo video, File imgFile, SvcMetaDataHelper.SvcFaultPicMeta svcFaultPicMeta) {
        if (imgFile == null) {
            logger.error("视频中截图服务日志照片异常,生成失败.rwh=" + video.getRwh() + ",svcId=" + video.getSvcId() + ",oprtId=" + video.getOprtId() + ",pic=" + svcFaultPicMeta.getCn());
            return;
        }
        if (!imgFile.exists() || imgFile.length() == 0) {
            logger.error("视频中截图服务日志照片异常,文件不存在或大小为0.rwh=" + video.getRwh() + ",svcId=" + video.getSvcId() + ",oprtId=" + video.getOprtId() + ",imgFile=" + imgFile.getAbsolutePath() + ",pic=" + svcFaultPicMeta.getCn());
            return;
        }

        String ossPath = fastDfsClient.uploadFile(imgFile, "jpg");
        imgFile.delete();
        svcResUploadHelper.createSvcCommPic(video.getRwh(), video.getSvcId(), svcFaultPicMeta, ossPath, "");
    }

    //提取某1秒的截图
    public File previewImage(File videoFile, int second) {
        if (videoFile == null || !videoFile.exists()) {
            return null;
        }

        File imageFile = new File(videoFile.getAbsolutePath() + "." + second + ".jpg");
        ffmpegService.previewImage(videoFile, imageFile, second);
        return imageFile;
    }

}

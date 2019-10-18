package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.TianYuanUserHolder;
import com.tianyi.helmet.server.entity.svc.*;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.svc.rest.entity.SvcCommPic;
import com.tianyi.svc.rest.entity.SvcGzclMain;
import com.tianyi.svc.rest.entity.SvcTask;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 服务日志资源上传
 * <p>
 * Created by liuhanc on 2018/3/19.
 */
@Component
public class SvcResUploadHelper {

    @Autowired
    private SvcVideoService svcVideoService;
    @Autowired
    private SvcAudioService svcAudioService;
    @Autowired
    private SvcImageService svcImageService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private SvcMainRestService svcMainRestService;
    @Autowired
    private SvcTaskService svcTaskService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private SvcIdRestService svcIdRestService;

    @Autowired
    private RedisMqPublisher redisMqPublisher;


    private Logger logger = LoggerFactory.getLogger(SvcResUploadHelper.class);

    public void addToProcessQueue(String channelName, int id) {
        redisMqPublisher.sendMessage(channelName, String.valueOf(id));//加入队列
    }

    public void processVideo(SvcVideo newVideo) {
        logger.debug("服务日志视频加入处理队列.id=" + newVideo.getId() + ",type=" + newVideo.getVideoType());
        addToProcessQueue(ChannelNameConstants.channelName_SvcVideo_Parse, newVideo.getId());
    }

    public void processAudio(SvcAudio audio) {
        logger.debug("服务日志音频加入处理队列.id=" + audio.getId() + ",type=" + audio.getAudioType());
        addToProcessQueue(ChannelNameConstants.channelName_SvcAudio_Parse, audio.getId());
    }

    public void processImage(SvcImage image) {
        logger.debug("服务日志图像加入处理队列.id=" + image.getId() + ",type=" + image.getImageType());
        addToProcessQueue(ChannelNameConstants.channelName_SvcImage_Parse, image.getId());
    }

    /**
     * 获得1个工单对应的所有头盔摄录的资源
     *
     * @param rwh
     * @param oprtId
     * @return
     */
    public List<SvcResAbstract> getSvcResList(String rwh, String oprtId) {
        List<? extends SvcResAbstract> imgList = svcImageService.selectByRwhOprtId(rwh, oprtId);
        List<? extends SvcResAbstract> vdoList = svcVideoService.selectByRwhOprtId(rwh, oprtId);
        List<? extends SvcResAbstract> adoList = svcAudioService.selectByRwhOprtId(rwh, oprtId);
        List<SvcResAbstract> resList = new ArrayList<>(imgList.size() + vdoList.size() + adoList.size());
        resList.addAll(imgList);
        resList.addAll(vdoList);
        resList.addAll(adoList);
        resList.stream().forEach(this::setResPathPrefix);
        return resList;
    }

    /**
     * 查询上传的资源中最新的那个
     *
     * @param rwh
     * @param resType
     * @param resInnerType
     * @param oprtId
     * @return
     */
    public SvcResAbstract getLatestSvcRes(String rwh, SvcResTypeEnum resType, String resInnerType, String oprtId) {
        List<? extends SvcResAbstract> resList = null;
        if (SvcResTypeEnum.image.equals(resType)) {
            resList = svcImageService.selectByRwhTypeOprtId(rwh, resInnerType, oprtId);
        } else if (SvcResTypeEnum.video.equals(resType)) {
            resList = svcVideoService.selectByRwhTypeOprtId(rwh, resInnerType, oprtId);
        } else if (SvcResTypeEnum.audio.equals(resType)) {
            resList = svcAudioService.selectByRwhTypeOprtId(rwh, resInnerType, oprtId);
        }

        if (resList != null && resList.size() > 0) {
            SvcResAbstract res = resList.get(0);//时间逆序，取第1个就是最新的
            setResPathPrefix(res);
            return res;
        }
        return null;
    }

    public void setResPathPrefix(SvcResAbstract svcRes) {
        if (svcRes != null)
            svcRes.setResPathPrefix(configService.getFastdfsServerUrl());
    }

    public ResponseVo<SvcVideo> uploadVideo(String rwh, String svcId, String videoType, MultipartFile file) {
        SvcVideo video = new SvcVideo();
        video.setSvcId(svcId);

        boolean persent = Arrays.stream(SvcVideoTypeEnum.values()).filter(ve -> ve.toString().equals(videoType)).findAny().isPresent();
        if (!persent) {
            return ResponseVo.fail("上传视频时视频类型无法识别." + videoType, video);
        }

        video.setVideoType(videoType);
        ResponseVo<SvcVideo> vo = checkBeforeUpload(rwh, file, video, videoType);
        if (!vo.isSuccess()) {
            return vo;
        }

        boolean success = false;
        try {
            svcVideoService.insert(video);
            success = true;
            return ResponseVo.success(video);
        } catch (Exception e) {
            logger.error("保存上传的视频发生异常.工单号:" + rwh, e);
            return ResponseVo.fail("保存视频信息失败." + e.getMessage(), video);
        } finally {
            if (success) {
                try {
                    processVideo(video);
                } catch (Exception e) {
                    logger.error("将上传的服务日志视频加入处理队列异常.video.id=" + video.getId(), e);
                }
            }
        }
    }

    public ResponseVo<SvcAudio> uploadAudio(String rwh, String svcId, String audioType, MultipartFile file) {
        SvcAudio audio = new SvcAudio();
        audio.setSvcId(svcId);
        boolean persent = Arrays.stream(SvcAudioTypeEnum.values()).filter(ve -> ve.toString().equals(audioType)).findAny().isPresent();
        if (!persent) {
            return ResponseVo.fail("上传音频时音频类型无法识别." + audioType, audio);
        }

        audio.setAudioType(audioType);
        ResponseVo<SvcAudio> vo = checkBeforeUpload(rwh, file, audio, audioType);
        if (!vo.isSuccess()) {
            return vo;
        }

        boolean success = false;
        try {
            svcAudioService.insert(audio);
            success = true;
            return ResponseVo.success(audio);
        } catch (Exception e) {
            logger.error("保存上传的音频发生异常.工单号:" + rwh, e);
            return ResponseVo.fail("保存音频信息失败." + e.getMessage(), audio);
        } finally {
            if (success) {
                try {
                    processAudio(audio);
                } catch (Exception e) {
                    logger.error("将上传的服务日志音频加入处理队列异常.audio.id=" + audio.getId(), e);
                }
            }
        }
    }

    public ResponseVo<SvcImage> uploadImage(String rwh, String svcId, String imageType, MultipartFile file) {
        SvcImage image = new SvcImage();
        image.setSvcId(svcId);

        boolean persent = Arrays.stream(SvcImageTypeEnum.values()).filter(ve -> ve.toString().equals(imageType)).findAny().isPresent();
        if (!persent) {
            return ResponseVo.fail("上传图片时图片类型无法识别." + imageType, image);
        }

        image.setImageType(imageType);
        ResponseVo<SvcImage> vo = checkBeforeUpload(rwh, file, image, imageType);
        if (!vo.isSuccess()) {
            return vo;
        }

        boolean success = false;
        try {
            svcImageService.insert(image);
            success = true;
            return ResponseVo.success(image);
        } catch (Exception e) {
            logger.error("保存上传的图片发生异常.工单号:" + rwh, e);
            return ResponseVo.fail("保存图片信息失败." + e.getMessage(), image);
        } finally {
            if (success) {
                try {
                    processImage(image);
                } catch (Exception e) {
                    logger.error("将上传的服务日志照片加入处理队列异常.image.id=" + image.getId(), e);
                }
            }
        }
    }

    /**
     * 文件上传前的检查
     *
     * @param rwh
     * @param file
     * @param svcRes
     * @return
     */
    private ResponseVo checkBeforeUpload(String rwh, MultipartFile file, SvcResAbstract svcRes, String innerResType) {
        svcRes.setRwh(rwh);

        if (file.isEmpty()) {
            logger.error("上传的文件是空的.type=" + svcRes.getResType() + ",rwh=" + rwh);
            return ResponseVo.fail("文件是空的");
        }

        String originalFilename = file.getOriginalFilename();
        int dotIdx = originalFilename.lastIndexOf(".");
        if (dotIdx == -1) {
            return ResponseVo.fail("文件扩展名为空");
        }


        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        SvcTask task = svcTaskService.getSvcTask(rwh, oprtId);
        if (task == null) {
            return ResponseVo.fail("工单任务数据不存在");
        }

        String suffix = originalFilename.substring(dotIdx + 1).toLowerCase();
        //获得文件内容
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (Throwable e) {
            logger.error("接收上传的文件内容失败.type=" + svcRes.getResType() + ",rwh=" + rwh);
            return ResponseVo.fail("获取上传文件内容失败.");
        }

        String ossPath = fastDfsClient.uploadFile(bytes, suffix);
        if (StringUtils.isEmpty(ossPath)) {
            return ResponseVo.fail("保存文件失败,请稍候重试.");
        }

        svcRes.setRwh(rwh);
        svcRes.setClientId(HelmetImeiHolder.get());
        svcRes.setMachineCode(task.getJh());
        svcRes.setUploadTime(LocalDateTime.now());
        svcRes.setOprtId(oprtId);
        svcRes.setUserName(TianYuanUserHolder.getTianYuanOprtName());
        svcRes.setOssPath(ossPath);
        svcRes.setResPath(configService.getFastdfsServerUrl() + ossPath);
        return ResponseVo.success(svcRes);
    }

    /**
     * 获得上传的文件在本地的临时存储
     *
     * @param v
     * @return
     */
    public File getSvcResFile(SvcResAbstract v) {
        String ossPath = v.getOssPath();
        if (StringUtils.isEmpty(ossPath)) {
            logger.debug("下载服务日志资源文件失败:ossPath为空.type=" + v.getResType() + ",id=" + v.getId());
            return null;
        }

        SvcResTypeEnum resType = v.getResType();
        return downloadFdfsFile(ossPath, resType);
    }

    public byte[] getSvcResFileContent(SvcResAbstract v) {
        String ossPath = v.getOssPath();
        if (StringUtils.isEmpty(ossPath)) {
            logger.debug("下载服务日志资源文件失败:ossPath为空.type=" + v.getResType() + ",id=" + v.getId());
            return null;
        }
        return fastDfsClient.downloadFile(ossPath);
    }

    //下载服务日志对应的资源文件到本地目录
    public File downloadFdfsFile(String ossPath, SvcResTypeEnum resType) {
        String dirName = SvcResTypeEnum.image.equals(resType) ? configService.getUploadImageDir() : (SvcResTypeEnum.video.equals(resType) ? configService.getUploadVideoDir() : configService.getUploadAudioDir());
        String fileExt = SvcResTypeEnum.image.equals(resType) ? "jpg" : (SvcResTypeEnum.video.equals(resType) ? "mp4" : "wav");//@todo 音频文件扩展名

        File downloadFile = new File(configService.getUploadFileSaveDir(), dirName + File.separator + UUID.randomUUID().toString() + "." + fileExt);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(downloadFile);
        } catch (Exception e) {
            logger.error("构造文件输出流异常:" + downloadFile.getAbsolutePath(), e);
            if (downloadFile.exists())
                downloadFile.delete();

            logger.debug("下载服务日志资源文件失败:构造文件输出流异常.ossPath=" + ossPath + ",fileExt=" + fileExt + ",filePath=" + downloadFile.getAbsolutePath());
            return null;
        }

        boolean success = fastDfsClient.downloadFile(ossPath, fos);
        if (success && downloadFile.exists()) {
            return downloadFile;
        }
        downloadFile.delete();

        logger.debug("下载服务日志资源文件失败:下载失败.ossPath=" + ossPath + ",fileExt=" + fileExt + ",filePath=" + downloadFile.getAbsolutePath());
        return null;
    }

    public void deleteSvcPics(SvcResAbstract res) {
        SvcResTypeEnum resType = res.getResType();
        switch (resType) {
            case video: {
                SvcVideo v = (SvcVideo) res;
                String videoType = v.getVideoType();//digger,faultCheck,faultRepaird
                if (SvcVideoTypeEnum.digger.toString().equals(videoType)) {
                    //挖机视频，作为故障的 故障照片-整机的来源,取第1秒的截图
                    SvcMetaDataHelper.SvcFaultPicMeta svcFaultPicMeta = SvcMetaDataHelper.SvcFaultPicMeta.ZHENGJI;
                    this.removeSvcCommPic(v.getRwh(), v.getSvcId(), svcFaultPicMeta);
                } else if (SvcVideoTypeEnum.faultCheck.toString().equals(videoType)) {
                    //故障视频，作为故障的 故障照片-检测、故障照片-中、远、近 3张 合计4张照片的来源
                    //从远到近拍的故障视频，第0秒作为远景、最后1秒作为近景，中间秒作为中景,倒数第2秒作为检测图
                    this.removeSvcCommPic(v.getRwh(), v.getSvcId(), SvcMetaDataHelper.SvcFaultPicMeta.YUANJING);
                    this.removeSvcCommPic(v.getRwh(), v.getSvcId(), SvcMetaDataHelper.SvcFaultPicMeta.JINJING);
                    this.removeSvcCommPic(v.getRwh(), v.getSvcId(), SvcMetaDataHelper.SvcFaultPicMeta.ZHONGJING);
                    this.removeSvcCommPic(v.getRwh(), v.getSvcId(), SvcMetaDataHelper.SvcFaultPicMeta.JIANCE);
                }
                break;
            }
            case image: {
                SvcImage i = (SvcImage) res;
                SvcMetaDataHelper.SvcFaultPicMeta jhMeta = SvcMetaDataHelper.SvcFaultPicMeta.JIHAO;
                if (jhMeta.name().equalsIgnoreCase(i.getImageType())) {
                    this.removeSvcCommPic(i.getRwh(), i.getSvcId(), jhMeta);
                }
                break;
            }
            case audio:
                break;
            default:
                break;
        }
    }

    public void removeSvcCommPic(String rwh, String svcId, SvcMetaDataHelper.SvcFaultPicMeta meta) {
        svcIdRestService.deleteSvcCommPic(rwh, svcId, meta.getEn());
    }

    //构造pic实例
    public boolean createSvcCommPic(String rwh, String svcId, SvcMetaDataHelper.SvcFaultPicMeta meta, String imageUrl, String imageName) {
        SvcCommPic pic = new SvcCommPic();
        pic.setRwh(rwh);
        pic.setSvcId(svcId);
        pic.setFieldNameCn(meta.getCn());
        pic.setFieldNameEn(meta.getEn());
        pic.setPhotoUrl(imageUrl);//存储文件名或网络路径
        pic.setScbz("0");//0未上传  1正在上传 2已上传
        pic.setPhotoMemo("田一头盔上传");
        pic.setPhotoName(imageName);

        try {
            svcIdRestService.addOne(SvcCommPic.class, pic);
            return true;
        } catch (Exception e) {
            logger.error("保存服务日志照片异常.rwh=" + rwh + ",svcId=" + svcId + ",imageType=" + meta, e);
            return false;
        }
    }

    /**
     * 更新工单主记录中的照片上传信息
     *
     * @param mainEntity
     * @param meta
     * @return
     */
    public boolean updateSvcPhotoUploadInfo(SvcMainEntity mainEntity, SvcMetaDataHelper.SvcFaultPicMeta meta) {
        if (mainEntity instanceof SvcGzclMain) {
            SvcGzclMain gzclMain = (SvcGzclMain) mainEntity;
            meta.getFieldSetter().accept(gzclMain, "已上传");
            svcMainRestService.updateSvcMainEntity(SvcGzclMain.class, SvcGzclMain.class.getSimpleName(), gzclMain);
            return true;
        }
        return false;
    }
}

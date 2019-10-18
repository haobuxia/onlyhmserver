package com.tianyi.helmet.server.controller.helmetinterface;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.job.VideoAudioMergePo;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.service.support.FfmpegService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 视频音频同时上传功能
 */
@Controller
@RequestMapping("videoaudio")
public class VideoAudioController {

    private Logger logger = LoggerFactory.getLogger(VideoAudioController.class);

    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private RedisMqPublisher redisMqPublisher;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo upload(@RequestParam("videofile") MultipartFile videoFile,
                             @RequestParam("audiofile") MultipartFile audioFile,
                             @RequestParam(value = "createTime") Date createTime, @RequestParam(value = "machineCode", required = false) String machineCode,
                             @RequestParam(value = "orderNo", required = false) String orderNo,
                             @RequestParam(value = "description", required = false) String description, @RequestParam(value = "tag", required = false) String tag) {
        String helmetImei = HelmetImeiHolder.get();

        logger.info("video-audiuo upload post.clientId=" + helmetImei + ",description=" + description + ",createTime=" + createTime + ",machineCode=" + machineCode + ",tag=" + tag + ",orderNo=" + orderNo);

        File video = uploadEntityServiceFactory.getSavedFile(UploadEntityTypeEnum.video, "videoaudio" + File.separator + videoFile.getOriginalFilename());
        File audio = uploadEntityServiceFactory.getSavedFile(UploadEntityTypeEnum.audio, "videoaudio" + File.separator + audioFile.getOriginalFilename());

        try{
            IOUtils.write(videoFile.getBytes(),new FileOutputStream(video));
            IOUtils.write(audioFile.getBytes(),new FileOutputStream(audio));
        }catch(Exception e){
            logger.error("保存上传的视频和音频文件失败",e);
            return ResponseVo.fail("保存上传的视频和音频文件失败.");
        }

        VideoAudioMergePo mergeInfo = new VideoAudioMergePo();
        mergeInfo.setDescription(description);
        mergeInfo.setOrigFileName(videoFile.getOriginalFilename()+".merge.mp4");
        mergeInfo.setCreateTime(Dates.toLocalDateTime(createTime));
        mergeInfo.setClientId(helmetImei);
        mergeInfo.setTag(tag);
        mergeInfo.setMachineCode(machineCode);
        mergeInfo.setOrderNo(orderNo);
        mergeInfo.setVideoFilePath(video.getAbsolutePath());
        mergeInfo.setAudioFilePath(audio.getAbsolutePath());

        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_VideoAudio_Merge, JSON.toJSONString(mergeInfo));//加入视频音频合并队列

        return ResponseVo.success("已加入合并队列");
    }

}

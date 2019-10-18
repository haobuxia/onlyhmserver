package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.svc.SvcAudio;
import com.tianyi.helmet.server.entity.svc.SvcAudioTypeEnum;
import com.tianyi.helmet.server.service.baidu.BaiduSpeechService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.svc.*;
import com.tianyi.svc.rest.entity.SvcCommJgnr;
import com.tianyi.svc.rest.entity.SvcCommYhyj;
import com.tianyi.svc.rest.entity.SvcGzclMain;
import com.tianyi.svc.rest.entity.SvcTask;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;

/**
 * 服务日志音频处理
 * <p>
 * Created by liuhanc on 2018/4/2.
 */
@Component
public class SvcAudioParseJob implements MessageListener {
    @Autowired
    private SvcAudioService svcAudioService;
    @Autowired
    private BaiduSpeechService baiduSpeechService;
    //    @Autowired
//    private KdxfSpeechService kdxfSpeechService;
    @Autowired
    private SvcResUploadHelper svcResUploadHelper;
    @Autowired
    private SvcTaskService svcTaskService;
    @Autowired
    private SvcMainRestService svcMainRestService;
    @Autowired
    private SvcCommRestService svcCommRestService;
    @Autowired
    private SvcMetaDataHelper svcMetaDataHelper;
    @Autowired
    private FastDfsClient fastDfsClient;

    private Logger logger = LoggerFactory.getLogger(SvcAudioParseJob.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("SvcAudioParseJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer id = Integer.parseInt(body);
            SvcAudio audio = svcAudioService.selectById(id);
            doParse(audio, false);//不强制刷新
        } catch (Exception e) {
            logger.error("处理服务日志音频解析队列消息异常.", e);
        }
    }


    private void deleteSvcRes(SvcAudio v) {
        String audioType = v.getAudioType();
        //删除数据
        svcAudioService.deleteById(v.getId());

        //删除文件
        String ossPath = v.getOssPath();
        if (org.springframework.util.StringUtils.isEmpty(ossPath)) {
            return;
        }
        fastDfsClient.deleteFile(ossPath);
    }

    public void doParse(SvcAudio audio, boolean force) {
        if (audio == null) {
            logger.error("音频信息不存在，不处理");
            return;
        }

        SvcTask task = svcTaskService.getSvcTask(audio.getRwh(), audio.getOprtId());
        if (task == null) {
            logger.error("音频对应任务信息不存在，不处理.rwh=" + audio.getRwh() + ",oprtId=" + audio.getOprtId());
            return;
        }

        //之前上传的同类型视频要删除掉
        List<SvcAudio> resList = svcAudioService.selectByRwhTypeOprtId(audio.getRwh(), audio.getAudioType(), audio.getOprtId());
        if (!CollectionUtils.isEmpty(resList)) {
            resList.stream().filter(tempAudio -> tempAudio.getId() != audio.getId()).forEach(tempAudio -> {
                //其他同类型的资源要删除
                //删除记录、删除音频、删除生成的图片
                boolean success = false;
                try {
                    deleteSvcRes(tempAudio);
                    success = true;
                } catch (Exception e) {
                    success = false;
                    logger.error("删除之前上传的同类型音频.id=" + tempAudio.getId() + ",rwh=" + audio.getRwh() + ",audioType=" + audio.getAudioType() + ",oprtId=" + audio.getOprtId() + ",异常", e);
                } finally {
                    logger.debug("删除之前上传的同类型音频.id=" + tempAudio.getId() + ",rwh=" + audio.getRwh() + ",audioType=" + audio.getAudioType() + ",oprtId=" + audio.getOprtId() + ",结果=" + (success ? "成功" : "失败"));
                }
            });
        }

        //获得主数据

        Class cls = svcMetaDataHelper.getMainClassByFwlb(task.getFwlb());
        SvcMainEntity mainEntity = svcMainRestService.getOrderByRwhOprtId(cls, cls.getSimpleName(), audio.getRwh(), audio.getOprtId());
        if (mainEntity == null) {
            logger.error("处理服务日志语音时发现服务日志主记录不存在.rwh=" + audio.getRwh() + ",oprtId=" + audio.getOprtId());
            return;
        }

        if (!String.valueOf(mainEntity.getPkValue()).equals(audio.getSvcId())) {
            //理论上不会发生
            logger.error("处理服务日志语音时发现服务日志主记录id和语音关联id不一致.rwh=" + audio.getRwh() + ",oprtId=" + audio.getOprtId() + ",svcId=" + audio.getSvcId() + ",pkValue=" + mainEntity.getPkValue());
            return;
        }

        String text = resorveAudioToText(audio);
        //更新语音内容
        try {
            updateAudioText(audio, mainEntity, text);
        } catch (Exception e) {
            logger.error("处理上传的服务日志语音异常.audio.id=" + audio.getId() + ",rwh=" + audio.getRwh() + ",svcId=" + audio.getSvcId() + ",oprtId=" + audio.getOprtId(), e);
        }
    }

    //解析语音
    private String resorveAudioToText(SvcAudio audio) {
        boolean useKdxf = true;
        if (useKdxf) {
            //解析声音得到文字
            File audioFile = svcResUploadHelper.getSvcResFile(audio);
            if (audioFile == null || !audioFile.exists()) {
                logger.error("音频文件不存在，不处理.audioType = " + audio.getAudioType() + ",audio.id=" + audio.getId() + ",file=" + audioFile.getAbsolutePath());
                return null;
            }

            String text = null;
            try {
                text = baiduSpeechService.asrAudioToText(audioFile, "wav");
//                text = kdxfSpeechService.asrAudioToText(audioFile, 10, 10 * 60);
                if (StringUtils.isEmpty(text)) {
                    text = "语音识别结果为空";
                }
            } catch (Exception e) {
                logger.error("语音识别异常.audio.id=" + audio.getId(), e);
            } finally {
                audioFile.delete();//删除文件
            }
            return text;
        } else {

            byte[] bytes = svcResUploadHelper.getSvcResFileContent(audio);
            baiduSpeechService.asrAudioToText(bytes, "wav"); //@todo 解析结果得到文字
            return null;
        }
    }

    //将识别的语音结果记录到数据库
    private void updateAudioText(SvcAudio audio, SvcMainEntity mainEntity, String audioText) {
        String audioType = audio.getAudioType();
        //faultContent,notHandleReason,userOpinion
        if (SvcAudioTypeEnum.faultContent.toString().equals(audioType)) {
            SvcGzclMain gzclMain = (SvcGzclMain) mainEntity;
            if (audioText.length() > 250) {//天远数据库500个长度250个汉字
                audioText = audioText.substring(0, 240) + "...";
            }
            gzclMain.setGznr(audioText);//故障内容
            gzclMain.setGzjcGzyy(audioText);//检测过程及故障原因

            if (audioText.length() > 100) {
                audioText = audioText.substring(0, 90) + "...";
            }
            gzclMain.setGzjcBy8(audioText);//服务内容
            gzclMain.setGzjcTxbz("1");
            svcMainRestService.updateSvcMainEntity(SvcGzclMain.class, SvcGzclMain.class.getSimpleName(), gzclMain);
        } else if (SvcAudioTypeEnum.notHandleReason.toString().equals(audioType)) {
            SvcGzclMain gzclMain = (SvcGzclMain) mainEntity;

            if (audioText.length() > 25) {
                //长度超过50则截断
                audioText = audioText.substring(0, 20) + "...";
            }
            gzclMain.setGzclWxfyy(audioText);//未修复原因,数据库长度50
            gzclMain.setGzclTxbz("1");
            svcMainRestService.updateSvcMainEntity(SvcGzclMain.class, SvcGzclMain.class.getSimpleName(), gzclMain);

            SvcCommJgnr jgnr = (SvcCommJgnr) svcCommRestService.getCommDataByRwhSvcId(SvcCommJgnr.class, SvcCommJgnr.class.getSimpleName(), audio.getRwh(), String.valueOf(mainEntity.getPkValue()));
            if (jgnr == null) {
                logger.error("保存结果内容语音对应文字时发现结果内容数据不存在.rwh=" + audio.getRwh() + ",svcId=" + audio.getSvcId() + ",oprtId=" + audio.getOprtId() + ",audioType=" + audioType);
                return;
            }
            jgnr.setWwcyy(audioText);//未完成原因,数据库长度50
            jgnr.setJgnr("未完成");
            jgnr.setTxbz("1");
            svcCommRestService.updateSvcCommEntity(SvcCommJgnr.class, SvcCommJgnr.class.getSimpleName(), jgnr);
        } else if (SvcAudioTypeEnum.userOpinion.toString().equals(audioType)) {
            //用户意见
            SvcCommYhyj yhyj = (SvcCommYhyj) svcCommRestService.getCommDataByRwhSvcId(SvcCommYhyj.class, SvcCommYhyj.class.getSimpleName(), audio.getRwh(), String.valueOf(mainEntity.getPkValue()));
            if (yhyj == null) {
                logger.error("保存用户意见语音对应文字时发现用户意见数据不存在.rwh=" + audio.getRwh() + ",svcId=" + audio.getSvcId() + ",oprtId=" + audio.getOprtId() + ",audioType=" + audioType);
                return;
            }
            if (audioText.length() > 25) {//天远数据库50个长度25个汉字
                //长度超过50则截断
                audioText = audioText.substring(0, 20) + "...";
            }
            yhyj.setJyhyj(audioText);//用户建议和意见
            yhyj.setTxbz("1");
            svcCommRestService.updateSvcCommEntity(SvcCommYhyj.class, SvcCommYhyj.class.getSimpleName(), yhyj);
        }
    }
}

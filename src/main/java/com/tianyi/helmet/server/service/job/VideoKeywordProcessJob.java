package com.tianyi.helmet.server.service.job;


import com.tianyi.helmet.server.entity.file.KeyWord;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.file.VideoKeyword;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.file.KeyWordService;
import com.tianyi.helmet.server.service.file.VideoKeywordService;
import com.tianyi.helmet.server.service.file.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *  视频扩展操作job
 *
 * 1 识别关键词打标
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class VideoKeywordProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(VideoKeywordProcessJob.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoKeywordService videoKeywordService;
    @Autowired
    private KeyWordService keyWordService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("VideoKeywordProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer videoId = Integer.parseInt(body);
            Video v = videoService.selectById(videoId);
            processOneData(v);
        } catch (Exception e) {
            logger.error("处理视频语音识别队列消息异常.", e);
        }
    }

    /**
     * 处理某个视频
     *
     * @param v
     */
    public void processOneData(Video v) {
        int oldState = v.getState();
        boolean prepareOk = doPrepare(v);
        if (!prepareOk)
            return;

        //服务数据视频.则提取视频中声音，识别并拆分出关键词.20180709 改为所有视频都自动识别其中的声音
        try {
            doProcessVideoAsr(v);
        } catch (Exception e) {
            logger.error("服务数据视频文字提取关键词并打标发生异常.id=" + v.getId(), e);
        } finally {
            videoService.updateState(v.getId(), VideoStateEnum.get(oldState));
        }
    }

    /**
     * 提取视频声音，再解析声音文件得到文字
     *
     * @param v
     */
    public boolean doProcessVideoAsr(Video v) {
        List<VideoKeyword> vkList = videoKeywordService.selectByVideoId(v.getId());
        vkList.forEach(vk -> {
            try {
                int keyWordId = asrKeyWord(vk);
                vk.setKeywordId(keyWordId);
                KeyWord kw = keyWordService.selectById(keyWordId);
                if(kw!=null) {
                    vk.setKeywordName(kw.getKeywordName());
                    videoKeywordService.updateKeyWord(vk);
                }
            } catch (Exception e) {
                logger.error("记录自动识别出的视频关键词异常.v.id=" + v.getId(), e);
            }
        });

        return true;
    }
    // 分析关键词任务
    private int asrKeyWord(VideoKeyword vk) {
        for(KeyWord kw : keyWordService.selectKeyWordList()){
            try {
                String[] participles = kw.getParticiple().split(",");
                if(participles.length > 0){
                    double i = 0;
                    for (String participle : participles) {
                        if(vk.getPerspeech().contains(participle)){
                            i++;
                        }
                    }
                    if(i/participles.length >= 1.0){
                        return kw.getId();
                    }
                }
            } catch (Exception e) {
                logger.error("记录自动识别出的视频关键词异常.vk.id=" + vk.getId() + ",kw.id=" + kw.getId() + ",keyword=" + kw.getKeywordName(), e);
            }
        };
        return 0;
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
        if (oldState == VideoStateEnum.PROCING.getState()) {
            logger.error("视频的状态是正在处理,所以不予处理.id=" + v.getId());
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
}

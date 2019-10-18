package com.tianyi.helmet.server.service.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.dao.netease.NeteaseVideoAudioDao;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.netease.FileInfo;
import com.tianyi.helmet.server.entity.netease.NeteaseMessage;
import com.tianyi.helmet.server.entity.netease.NeteaseVideoAudio;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.file.AudioService;
import com.tianyi.helmet.server.service.file.UploadEntityService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.netease.NeteaseApiService;
import com.tianyi.helmet.server.service.netease.NeteaseMessageService;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于mq的 网易视频抄送消息处理
 * <p>
 * Created by liuhanc on 2017/10/17.
 */
@Component
public class NeteaseVideoMessageProcesssJob implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(NeteaseVideoMessageProcesssJob.class);

    @Autowired
    private NeteaseMessageService neteaseMessageService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private NeteaseVideoAudioDao neteaseVideoAudioDao;
    @Autowired
    private NeteaseApiService apiService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private AudioService audioService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("NeteaseVideoMessageProcesssJob 收到消息：patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);

            Integer msgId = Integer.parseInt(body);
            NeteaseMessage msg = neteaseMessageService.selectById(msgId);
            processOneData(msg);
        } catch (Exception e) {
            logger.error("处理队列消息异常.", e);
        }
    }

    /**
     * 处理视频消息
     *
     * @param message
     */
    protected void processOneData(NeteaseMessage message) {
        String oldFlag = message.getProcessFlag();
        if (!NeteaseMessage.ProcessFlag_Not.equals(oldFlag)) {
            logger.error("网易视频消息的标志不是待处理,所以不予处理.id=" + message.getId());
            return;
        }
        String processResult = null;
        String failReason = null;
        String json = message.getFullMsg();
        JSONObject jo = JSON.parseObject(json);
        String fileinfo = jo.getString("fileinfo");
        JSONArray fileinfoArray = JSON.parseArray(fileinfo);
        if (fileinfoArray == null || fileinfoArray.size() == 0) {
            processResult = NeteaseMessage.ProcessFlag_IGNORE;
            failReason = "并无文件";
        } else {
            //[{\"caller\":true,\"channelid\":\"6290737000999815988\",\"filename\":\"xxxxxx.type\",\"md5\":\"a9b248e29669d588dd0b10259dedea1a\",\"mix\":false,\"size\":\"2167\",\"type\":\"gz\",\"vid\":\"1062591\",\"url\":\"http://xxxxxxxxxxxxxxxxxxxx.type\",\"user\":\"zhangsan\"}]
            int size = fileinfoArray.size();
            for (int i = 0; i < size; i++) {
                JSONObject fileInfoJo = fileinfoArray.getJSONObject(i);
                FileInfo fileInfo = JSON.toJavaObject(fileInfoJo, FileInfo.class);
                if (!fileInfo.isMix()) {
                    //混合制文件的user是0,无法识别视频对应人
                    try {
                        DoubleVo<String, String> vo = processOneFile(fileInfo, message.getId());
                        processResult = vo.getKey();
                        failReason = vo.getVal();
                        if (NeteaseMessage.ProcessFlag_BREAK.equalsIgnoreCase(processResult)) {
                            break;
                        }
                    } catch (Exception e) {
                        logger.error("下载并删除网易音视频文件异常.msgId=" + message.getId() + ",vid=" + fileInfo.getVid(), e);
                        processResult = NeteaseMessage.ProcessFlag_FAIL;
                        failReason = "发生意外." + e.getMessage();
                    }
                } else {
                    logger.info("视频文件是混合制,不下载.id=" + message.getId());
                    processResult = NeteaseMessage.ProcessFlag_IGNORE;
                    failReason = "混合制文件不下载";
                }
            }
        }

        neteaseMessageService.updateMessageProcessFlag(message.getId(), processResult, failReason);
    }

    protected DoubleVo<String, String> processOneFile(FileInfo fileInfo, int msgId) {
        String processResult = null;
        String failReason = null;
        long channelId = fileInfo.getChannelid();
        NeteaseVideoAudio nva = neteaseVideoAudioDao.selectByChannelId(channelId);
        if (nva == null) {
            logger.warn("音视频文件对应基本信息不存在,无法处理.channelId=" + channelId);
            processResult = NeteaseMessage.ProcessFlag_BREAK;
            failReason = "音视频文件对应基本信息不存在,无法处理";
        } else {
            Date createTime = new Date(nva.getCreateTime());
            String fileType = fileInfo.getType();
            //@todo 如果1个消息多个文件，则可能成功、失败、忽略并存
            boolean success = false;
            JSONObject jo = new JSONObject();
            jo.put("channelId", channelId);
            jo.put("msgId", msgId);
            jo.put("vId", fileInfo.getVid());
            String descJson = jo.toJSONString();
            if ("mp4".equalsIgnoreCase(fileType)) {
                success = downloadFile(fileInfo, createTime, descJson, true);
                processResult = success ? NeteaseMessage.ProcessFlag_SUCCESS : NeteaseMessage.ProcessFlag_FAIL;
                failReason = success ? "视频下载成功" : "视频下载入库失败";
            } else if ("aac".equalsIgnoreCase(fileType)) {
                success = downloadFile(fileInfo, createTime, descJson, false);
                processResult = success ? NeteaseMessage.ProcessFlag_SUCCESS : NeteaseMessage.ProcessFlag_FAIL;
                failReason = success ? "音频下载成功" : "音频下载入库失败";
            } else {
                processResult = NeteaseMessage.ProcessFlag_IGNORE;
                failReason = "忽略下载";
            }

            //调用接口将文件删除，避免网易收费
            if (success) {
                try {
                    success = apiService.deleteVideo(fileInfo.getVid());
                    logger.debug("网易音视频处理完毕，删除网易服务器上的音视频，结果:" + success + ".msgId=" + msgId + ",vid=" + fileInfo.getVid() + ",url=" + fileInfo.getUrl());
                } catch (Exception e) {
                    logger.error("删除网易服务器上的音视频发生异常.msgId=" + msgId + ",vid=" + fileInfo.getVid() + ",url=" + fileInfo.getUrl(), e);
                }
            } else {
                logger.debug("网易视频处理失败,暂时不从网易服务器删除.msgId=" + msgId + ",vid=" + fileInfo.getVid() + ",url=" + fileInfo.getUrl());
            }
        }
        return new DoubleVo<>(processResult, failReason);
    }

    /**
     * 下载音视频存入本地服务器，并记录信息到数据库
     *
     * @param fileInfo
     * @param createTime
     */
    protected boolean downloadFile(FileInfo fileInfo, Date createTime, String descJson, boolean isVideo) {
        String suffix = fileInfo.getType();
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        boolean success = apiService.fileDownload(fileInfo.getUrl(), byos);
        if (success) {
            //save to db
            UploadEntityService service = isVideo ? videoService : audioService;
            String user = fileInfo.getUser();//网易的userId就是头盔对应的网易用户id
            NeteaseUser neteaseUser = neteaseUserService.selectByUsername(user);
            if (neteaseUser == null) {
                logger.error("网易视频对应网易用户信息丢失.username=" + user);
                return false;
            }
            /**
             * update by xiayuan 2018/10/11
             */
            Map<String,Object> map = new HashMap<>();
            map.put("neUserHel",neteaseUser.getUsername());
            List<User> users = userService.listByNoPage(map);

            service.addNewFile(byos.toByteArray(), fileInfo.getFilename(), suffix, descJson, createTime, users.get(0).getId(),"", neteaseUser.getUsername(), "netease", null, null, null, null, null, "", "", "");
            return true;
        } else {
            return false;
        }
    }

}

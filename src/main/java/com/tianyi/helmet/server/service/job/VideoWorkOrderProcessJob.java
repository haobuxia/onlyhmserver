package com.tianyi.helmet.server.service.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.file.VideoOrder;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanIntesrvApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *  视频扩展操作job
 *
 * 1 根据工作卡id获取工单相关信息
 * 2 存储到数据库中
 * 3、方便后续视频中心查询使用
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class VideoWorkOrderProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(VideoWorkOrderProcessJob.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;

    @Autowired
    private TianYuanIntesrvApiHelper tianYuanIntesrvApiHelper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("VideoWorkOrderProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
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

        //服务数据视频.根据工作卡id获取工单相关信息
        try {
            doProcessVideoAsr(v);
        } catch (Exception e) {
            logger.error("服务数据视频声音提取出文字发生异常.id=" + v.getId(), e);
        } finally {
            videoService.updateState(v.getId(), VideoStateEnum.get(oldState));
        }
    }

    /**
     * 根据工作卡id获取工单相关信息
     *
     * @param v
     */
    public boolean doProcessVideoAsr(Video v) {
        JSONObject param = new JSONObject();
        if(v.getOrderNo()==null){
            return false;
        }
        User user = userService.selectById(v.getUserId());
        TianyiUserHolder.set(user);
        param.put("workoid", v.getOrderNo());
        JSONObject jsonObj = tianYuanIntesrvApiHelper.getJson("VideoWorkInfoByWorkOid", param);
        JSONObject jsonResult = jsonObj.getJSONObject("data");
        if(jsonResult == null) {
            return false;
        }
        // 保存工单相关信息
        VideoOrder videoOrder = new VideoOrder(v.getId(),
                jsonResult.getString("deviceid"),
                jsonResult.getString("workorderoid"),
                jsonResult.getString("workoid"),
                jsonResult.getString("devicebrand"),
                jsonResult.getString("devicetype"),
                jsonResult.getString("devicemodel"),
                jsonResult.getString("workpattern"));
        videoService.insertVideoOrder(videoOrder);
        return true;
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

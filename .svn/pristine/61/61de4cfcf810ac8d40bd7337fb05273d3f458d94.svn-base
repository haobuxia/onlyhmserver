package com.tianyi.helmet.server.controller.netease;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.netease.NeteaseMessage;
import com.tianyi.helmet.server.service.netease.CheckSumBuilder;
import com.tianyi.helmet.server.service.netease.NeteaseMessageService;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 接收网易推送消息
 *
 * Created by liuhanc on 2017/10/12.
 */
@Controller
@RequestMapping("netease")
public class NeteaseController {
    private Logger logger = LoggerFactory.getLogger(NeteaseController.class);

    @Autowired
    private ConfigService configService;
    @Autowired
    private NeteaseMessageService neteaseMessageService;

    /**
     * http://dev.netease.im/docs/product/IM%E5%8D%B3%E6%97%B6%E9%80%9A%E8%AE%AF/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3?#音视频/白板文件下载信息抄送
     * 接收网易消息抄送
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "receiveMessage",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject receiveMessage(HttpServletRequest request){
        logger.debug("netease receiveMessage.....");

        JSONObject result = new JSONObject();
        try {
            // 获取请求体
            byte[] body = null;
            if (request.getContentLength() > 0) {
                body = new byte[request.getContentLength()];
                IOUtils.readFully(request.getInputStream(), body);
            } else
                body = null;

            if (body == null) {
                logger.warn("request wrong, empty body!");
                result.put("code", 414);
                return result;
            }
            // 获取部分request header，并打印
//            String contentType = request.getContentType();
//            String appKey = request.getHeader("AppKey");
            String curTime = request.getHeader("CurTime");
//            String md5 = request.getHeader("MD5");
            String checkSum = request.getHeader("CheckSum");
//            logger.debug("request headers: ContentType = {}, AppKey = {}, CurTime = {}, " +
//                    "MD5 = {}, CheckSum = {}", contentType, appKey, curTime, md5, checkSum);

            // 将请求体转成String格式，并打印
            String requestBody = new String(body, "utf-8");
            logger.debug("request body = {}", requestBody);
            // 获取计算过的md5及checkSum
            String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
            String verifyChecksum = CheckSumBuilder.getCheckSum(configService.getNeteaseAppSecret(), verifyMD5, curTime);
//            logger.debug("verifyMD5 = {}, verifyChecksum = {}", verifyMD5, verifyChecksum);
            if(!Objects.equals(verifyChecksum,checkSum)){
                logger.warn("request checksum failed! checkSum = "+checkSum+",verifyChecksum="+verifyChecksum);
                result.put("code", 414);
            }

            //收到消息校验通过，进行处理
            processMessage(requestBody);

            result.put("code", 200);
            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.put("code", 414);
            return result;
        }
    }


    /**
     * 消息入库。
     *
     * 目前包含12类：
     1). "eventType"="1", 表示CONVERSATION消息，即会话类型的消息（目前包括P2P聊天消息，群组聊天消息，群组操作，好友操作）
     2). "eventType"="2", 表示LOGIN消息，即用户登录事件的消息
     3). "eventType"="3", 表示LOGOUT消息，即用户登出事件的消息
     4). "eventType"="4", 表示CHATROOM消息，即聊天室中聊天的消息
     5). "eventType"="5", 表示AUDIO/VEDIO/DataTunnel消息，即汇报实时音视频通话时长、白板事件时长的消息
     6). "eventType"="6", 表示音视频/白板文件存储信息，即汇报音视频/白板文件的大小、下载地址等消息
     7). "eventType"="7", 表示单聊消息撤回抄送
     8). "eventType"="8", 表示群聊消息撤回抄送
     9). "eventType"="9", 表示CHATROOM_INOUT信息，即汇报主播或管理员进出聊天室事件消息
     10). "eventType"="10", 表示ECP_CALLBACK信息，即汇报专线电话通话结束回调抄送的消息
     11). "eventType"="11", 表示SMS_CALLBACK信息，即汇报短信回执抄送的消息
     12). "eventType"="12", 表示SMS_REPLY信息，即汇报短信上行消息
     */
    protected void processMessage(String messageJson){
        //1 去重。去重算法？？
        JSONObject jo = JSON.parseObject(messageJson);
        NeteaseMessage msg = new NeteaseMessage();
        msg.setTime(LocalDateTime.now());
        msg.setProcessFlag(NeteaseMessage.ProcessFlag_Not);
        msg.setEventType(jo.getString("eventType"));
        msg.setConvType(jo.getString("convType"));
        msg.setFromAccount(jo.getString("fromAccount"));
        msg.setToAccount(jo.getString("to"));
        msg.setMsgTimestamp(jo.getString("msgTimestamp"));
        msg.setMsgType(jo.getString("msgType"));
        msg.setMsgIdClient(jo.getString("msgidClient"));
        msg.setMsgIdServer(jo.getString("msgidServer"));
        msg.setFullMsg(messageJson);
        //2 入库
        neteaseMessageService.insert(msg);
    }
}

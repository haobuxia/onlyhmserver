package com.tianyi.helmet.server.entity.netease;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * 网易抄送消息
 *
 * Created by liuhanc on 2017/10/15.
 */
public class NeteaseMessage extends IdEntity {

    public static final String ProcessFlag_Not="0";//待处理
    public static final String ProcessFlag_SUCCESS="1";//处理成功
    public static final String ProcessFlag_FAIL="2";//处理失败
    public static final String ProcessFlag_IGNORE="3";//忽略不需处理
    public static final String ProcessFlag_BREAK="4";//处理中断,下次继续处理

    private LocalDateTime time;//消息接收时间
    private String processFlag = ProcessFlag_Not;//处理标记 0未处理，1处理成功，2处理失败,3忽略 4处理中断
    private String failReason;
    private String eventType;//值为1，表示是会话类型的消息,5时长数据，6音视频下载数据

    /**
     * 会话具体类型：
     PERSON（二人会话数据）、TEAM（群聊数据）、
     CUSTOM_PERSON（个人自定义系统通知）、CUSTOM_TEAM（群组自定义系统通知），字符串类型
     */
    private String convType;
    /**
     * 若convType为PERSON或CUSTOM_PERSON，则to为消息接收者的用户账号，字符串类型；
     若convType为TEAM或CUSTOM_TEAM，则to为tid，即群id，可转为Long型数据
     */
    private String toAccount;
    /**
     * 消息发送者的用户账号，字符串类型
     */
    private String fromAccount;
    /**
     * 消息发送时间，字符串类型
     */
    private String msgTimestamp;
    /**
     * 会话具体类型PERSON、TEAM对应的通知消息类型：
     TEXT、
     PICTURE、
     AUDIO、
     VIDEO、
     LOCATION 、
     NOTIFICATION、
     FILE、 //文件消息
     NETCALL_AUDIO、 //网络电话音频聊天
     NETCALL_VEDIO、 //网络电话视频聊天
     DATATUNNEL_NEW、 //新的数据通道请求通知
     TIPS、 //提示类型消息
     CUSTOM //自定义消息

     会话具体类型CUSTOM_PERSON对应的通知消息类型：
     FRIEND_ADD、 //加好友
     FRIEND_DELETE、 //删除好友
     CUSTOM_P2P_MSG、 //个人自定义系统通知

     会话具体类型CUSTOM_TEAM对应的通知消息类型：
     TEAM_APPLY、 //申请入群
     TEAM_APPLY_REJECT、 //拒绝入群申请
     TEAM_INVITE、 //邀请进群
     TEAM_INVITE_REJECT、 //拒绝邀请
     TLIST_UPDATE、 //群信息更新
     CUSTOM_TEAM_MSG、 //群组自定义系统通知
     */
    private String msgType;
    /**
     * 客户端生成的消息id，仅在convType为PERSON或TEAM含此字段，字符串类型
     */
    private String msgIdClient;
    /**
     * 服务端生成的消息id，可转为Long型数据
     */
    private String msgIdServer;
    /**
     * 完整的消息内容json串
     */
    private String fullMsg;

    public String getProcessFlagStr() {
        if(ProcessFlag_Not.equals(processFlag))
            return "待处理";
        if(ProcessFlag_SUCCESS.equals(processFlag))
            return "成功";
        if(ProcessFlag_FAIL.equals(processFlag))
            return "失败";
        if(ProcessFlag_IGNORE.equals(processFlag))
            return "忽略";
        if(ProcessFlag_BREAK.equals(processFlag))
            return "中断";
        return processFlag;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }

    public String getTimeStr(){
        return time == null ? "" : Dates.format(Dates.toDate(time),"yyyy-MM-dd HH:mm:ss");
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getConvType() {
        return convType;
    }

    public void setConvType(String convType) {
        this.convType = convType;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(String msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgIdClient() {
        return msgIdClient;
    }

    public void setMsgIdClient(String msgIdClient) {
        this.msgIdClient = msgIdClient;
    }

    public String getMsgIdServer() {
        return msgIdServer;
    }

    public void setMsgIdServer(String msgIdServer) {
        this.msgIdServer = msgIdServer;
    }

    public String getFullMsg() {
        return fullMsg;
    }

    public void setFullMsg(String fullMsg) {
        this.fullMsg = fullMsg;
    }
}

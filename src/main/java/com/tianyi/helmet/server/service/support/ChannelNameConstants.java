package com.tianyi.helmet.server.service.support;

/**
 * redis 消息队列的通道名称常量定义
 * <p>
 * Created by liuhanc on 2018/4/20.
 */
public class ChannelNameConstants {
    public static final String channelName_Tsinghua_Video = "tsinghuaVideo";//清华需要的视频处理工作
    public static final String channelName_VideoAudio_Merge = "videoAudioMerge";//视频音频合并
    public static final String channelName_Video_Process = "toProcessVideo";//视频处理队列
    public static final String channelName_Video_Asr_Process = "toAsrKeywordVideo";//视频中语音识别处理队列
    public static final String channelName_Video_KeyWord_Process = "toAsrVideoKeyword";//视频中关键词打标
    public static final String channelName_Video_TimePreview = "toTimePreviewVideo";//时间时长队列
    public static final String channelName_Video_AddTrack = "toTrackVideo";//视频合并字幕队列
    public static final String channelName_File_Process = "toProcessFile";//文件处理队列
    public static final String channelName_SyncGpsData = "toSyncGpsData";//gps蓝牙盒子数据同步队列

    public static final String channelName_SvcVideo_Parse = "toSvcVideoParse";//队列名 视频截取照片
    public static final String channelName_SvcAudio_Parse = "toSvcAudioParse";//队列名 音频解析
    public static final String channelName_SvcImage_Parse = "toSvcImageParse";//队列名 照片处理

    public static final String channelName_SvcTask_Refresh = "toSvcTaskRefresh";//队列名-服务日志任务刷新
    public static final String channelName_TyUserToekn_Refresh = "toUserTokenRefresh";//队列名-天远账号token刷新

    public static final String channelName_BigFile_Merge = "bigFileMerge";//队列名-大文件合并

    public static final String channelName_NeteaseVideo_Download = "toDownloadNeteaseVideo";//队列名-网易视频下载
    public static final String channelName_NeUser_Create = "toNeUserCreate";//队列名-网易用户创建
}

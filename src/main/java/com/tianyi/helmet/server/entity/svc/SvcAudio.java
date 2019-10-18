package com.tianyi.helmet.server.entity.svc;

/**
 * 服务工单 音频
 * Created by liuhanc on 2018/3/16.
 */
public class SvcAudio extends SvcResAbstract {
    //@see SvcAudioTypeEnum
    private String audioType;//audioType faultContent:故障内容,notHandleReason:故障未处理原因,userOpinion 用户意见建议

    public SvcAudio() {
    }

    @Override
    public SvcResTypeEnum getResType() {
        return SvcResTypeEnum.audio;
    }

    @Override
    public String getResInnerTypeName() {
        if (audioType == null || audioType.length() == 0) return null;
        return SvcAudioTypeEnum.valueOf(audioType).getCnName();
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    @Override
    public String toString() {
        return "rwh=" + rwh + ",audioType=" + audioType + ",audioPath=" + getOssPath();
    }
}

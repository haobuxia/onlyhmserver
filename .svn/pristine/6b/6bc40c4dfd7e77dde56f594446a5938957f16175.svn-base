package com.tianyi.helmet.server.entity.svc;

/**
 * 服务工单视频
 * Created by liuhanc on 2018/3/16.
 */
public class SvcVideo extends SvcResAbstract {
    /**
     * @see SvcVideoTypeEnum
     */
    private String videoType;//videoType site：工地，digger：车辆，faultCheck：故障前，faultRepaird：故障修复

    public SvcVideo() {
    }

    @Override
    public SvcResTypeEnum getResType() {
        return SvcResTypeEnum.video;
    }

    @Override
    public String getResInnerTypeName() {
        if(videoType == null || videoType.length() == 0) return null;
        return SvcVideoTypeEnum.valueOf(videoType).getCnName();
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    @Override
    public String toString() {
        return "rwh=" + rwh + ",videoType=" + videoType + ",videoPath=" + getOssPath();
    }
}

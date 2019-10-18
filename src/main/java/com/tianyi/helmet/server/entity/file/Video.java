package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.scene.VideoAction;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 视频信息
 */
public class Video extends HelmetShot {
    private long seconds;
    private String picOssPath;//视频截个图oss存储路径
    private int state = 0;//@see VideoStateEnum
    private String src;//来源 upload,netease
    private Integer hasGpsData;//1有,0没有
    private String imei;//针对二手机,如果hasGpsData==1则imei表示对应车辆唯一识别号
    private String trackVideoOssPath;//带字幕的视频的oss路径
    private String videoType;//视频类型 @see VideoCategoryEnum
    private String caller;
    private String called;
    private String source;

    private VideoAction videoAction;//视频的动作标记信息

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 各个指标在时间序列对应的数据值
     * key表示指标id，list表示按8个动作发生的时间顺序得到的指标的值
     */
    private Map<Integer, List<Integer>> typeValListMap;

    public VideoAction getVideoAction() {
        return videoAction;
    }

    public void setVideoAction(VideoAction videoAction) {
        this.videoAction = videoAction;
    }

    public Map<Integer, List<Integer>> getTypeValListMap() {
        return typeValListMap;
    }

    public void setTypeValListMap(Map<Integer, List<Integer>> typeValListMap) {
        this.typeValListMap = typeValListMap;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    @Override
    public UploadEntityTypeEnum getType() {
        return UploadEntityTypeEnum.video;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getHasGpsData() {
        return hasGpsData;
    }

    public int getIntHasGpsData() {
        return hasGpsData == null ? 0 : hasGpsData.intValue();
    }

    public void setHasGpsData(Integer hasGpsData) {
        this.hasGpsData = hasGpsData;
    }

    public String getTrackVideoOssPath() {
        return trackVideoOssPath;
    }

    public void setTrackVideoOssPath(String trackVideoOssPath) {
        this.trackVideoOssPath = trackVideoOssPath;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public String getPicOssPath() {
        return picOssPath;
    }

    public void setPicOssPath(String picOssPath) {
        this.picOssPath = picOssPath;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

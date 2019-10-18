package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 视频关键词信息
 * <p>
 * Created by liuhanc on 2018/5/25.
 */
public class VideoKeyword extends IdEntity {
    private int videoId;
    private int keywordId;
    private String keywordName;//关键词名称 不入库
    private String perspeech;
    private int timeseconds;

    public VideoKeyword() {
    }

    public VideoKeyword(int videoId, String perspeech, int timeseconds) {
        this.videoId = videoId;
        this.perspeech = perspeech;
        this.timeseconds = timeseconds;
    }

    public VideoKeyword(int videoId, int keywordId, String keywordName) {
        this.setVideoId(videoId);
        this.setKeywordId(keywordId);
        this.setKeywordName(keywordName);
    }

    public String getPerspeech() {
        return perspeech;
    }

    public void setPerspeech(String perspeech) {
        this.perspeech = perspeech;
    }

    public int getTimeseconds() {
        return timeseconds;
    }

    public void setTimeseconds(int timeseconds) {
        this.timeseconds = timeseconds;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }
}

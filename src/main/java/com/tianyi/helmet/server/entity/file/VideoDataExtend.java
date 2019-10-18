package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 服务数据视频的扩展数据
 * 服务数据视频指的是视频类型video.videoType='服务数据'的这一类视频。这类视频里的语音要识别出来，并进行进一步的分析
 * <p>
 * Created by liuhanc on 2018/5/25.
 */
public class VideoDataExtend extends IdEntity {
    private int videoId;
    private String audioOssPath;//音频存储路径
    private String audioOrigText;//音频原始文字
    private String audioEditText;//音频修改后的文字

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getAudioOssPath() {
        return audioOssPath;
    }

    public void setAudioOssPath(String audioOssPath) {
        this.audioOssPath = audioOssPath;
    }

    public String getAudioOrigText() {
        return audioOrigText;
    }

    public void setAudioOrigText(String audioOrigText) {
        this.audioOrigText = audioOrigText;
    }

    public String getAudioEditText() {
        return audioEditText;
    }

    public void setAudioEditText(String audioEditText) {
        this.audioEditText = audioEditText;
    }
}

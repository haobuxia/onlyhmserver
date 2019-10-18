package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 视频的时间标志信息.每个视频有多个时间戳，在这些时间戳可能发生了1些关键动作。具体动作是什么，暂时不确定，未来可能由头盔传入也可能由人在后台手工指定。
 * <p>
 * Created by liuhanc on 2018/3/2.
 */
public class VideoTimeTag extends IdEntity {
    private Integer videoId;
    private String videoName;
    private LocalDateTime time;

    public VideoTimeTag() {
    }

    public VideoTimeTag(String videoName, LocalDateTime time) {
        this.videoName = videoName;
        this.time = time;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

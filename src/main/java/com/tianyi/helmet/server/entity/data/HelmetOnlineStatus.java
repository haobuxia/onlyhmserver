package com.tianyi.helmet.server.entity.data;

import java.time.LocalDateTime;

/**
 *
 * 头盔在线状态
 * Created by tianxujin on 2019/3/14.
 */
public class HelmetOnlineStatus extends HelmetData {
    private Integer status;//0上线、1离线

    private LocalDateTime onlineTime;

    private LocalDateTime offlineTime;

    private Integer userId;

    private String userName;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(LocalDateTime onlineTime) {
        this.onlineTime = onlineTime;
    }

    public LocalDateTime getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(LocalDateTime offlineTime) {
        this.offlineTime = offlineTime;
    }
}

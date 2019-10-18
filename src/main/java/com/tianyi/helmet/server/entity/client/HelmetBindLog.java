package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 头盔绑定日志
 *
 *
 * Created by liuhanc on 2017/10/12.
 */
public class HelmetBindLog extends IdEntity {
    private int helmetId;//头盔id
    private LocalDateTime bindTime;
    private String userName;
    private String userPhone;//用户手机号

    public int getHelmetId() {
        return helmetId;
    }

    public void setHelmetId(int helmetId) {
        this.helmetId = helmetId;
    }

    public LocalDateTime getBindTime() {
        return bindTime;
    }

    public void setBindTime(LocalDateTime bindTime) {
        this.bindTime = bindTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}

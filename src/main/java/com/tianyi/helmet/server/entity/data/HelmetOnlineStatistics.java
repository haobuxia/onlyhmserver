package com.tianyi.helmet.server.entity.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * 头盔在线时长统计
 * Created by tianxujin on 2019/3/16.
 */
public class HelmetOnlineStatistics {
    private String clientId;//clientId

    private String deviceNumber;

    private int userId;
    private String userName;

    private Integer loginNum;//登录次数

    private LocalDate loginDate;

    private long loginSeconds;// 在线时长

    private long shootSeconds;// 拍摄时长

    private Integer shootNum;

    private String loginLongStr;// 时:分:秒

    private Integer workNum;

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public long getShootSeconds() {
        return shootSeconds;
    }

    public void setShootSeconds(long shootSeconds) {
        this.shootSeconds = shootSeconds;
    }

    public Integer getShootNum() {
        return shootNum;
    }

    public void setShootNum(Integer shootNum) {
        this.shootNum = shootNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        this.loginDate = loginDate;
    }

    public long getLoginSeconds() {
        return loginSeconds;
    }

    public void setLoginSeconds(long loginSeconds) {
        this.loginSeconds = loginSeconds;
    }

    public String getLoginLongStr() {
        return loginLongStr;
    }

    public void setLoginLongStr(String loginLongStr) {
        this.loginLongStr = loginLongStr;
    }
}

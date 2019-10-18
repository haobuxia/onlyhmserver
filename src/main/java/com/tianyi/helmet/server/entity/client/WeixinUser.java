package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 微信用户信息
 *
 * Created by liuhanc on 2017/11/29.
 */
public class WeixinUser extends IdEntity{
    private String wxId;
    private String nickName;
    private int wxSex;
    private String area;
    private LocalDateTime subscribeTime;
    private int appUserId;
    private LocalDateTime bindAppTime;

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getWxSex() {
        return wxSex;
    }

    public void setWxSex(int wxSex) {
        this.wxSex = wxSex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDateTime getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(LocalDateTime subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public LocalDateTime getBindAppTime() {
        return bindAppTime;
    }

    public void setBindAppTime(LocalDateTime bindAppTime) {
        this.bindAppTime = bindAppTime;
    }
}

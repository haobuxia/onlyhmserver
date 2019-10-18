package com.tianyi.helmet.server.entity.scene.svc;

import java.util.List;

/**
 * 头盔工单模块欢迎页数据
 * <p>
 * Created by liuhanc on 2018/6/30.
 */
public class WorkOrderWelcome {
    private String address;
    private String serverTime;
    private int userId;
    private String userRealName;
    private Weather weather;
    private List<WorkOrder> workOrderList;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public List<WorkOrder> getWorkOrderList() {
        return workOrderList;
    }

    public void setWorkOrderList(List<WorkOrder> workOrderList) {
        this.workOrderList = workOrderList;
    }
}

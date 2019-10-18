package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 视频工单信息
 * <p>
 * Created by tianxujin on 2019/2/15.
 */
public class VideoOrder extends IdEntity {
    private int videoId;
    private String deviceid; //机号
    private String workorderoid;//工单号
    private String workoid;//工作卡oid
    private String devicebrand;//品牌
    private String devicetype;//机种
    private String devicemodel;//机型
    private String workpattern;//场景

    public VideoOrder() {
    }

    public VideoOrder(int videoId, String deviceId, String workorderoid, String workoid, String devicebrand, String devicetype, String devicemodel, String workpattern) {
        this.videoId = videoId;
        this.deviceid = deviceId;
        this.workorderoid = workorderoid;
        this.workoid = workoid;
        this.devicebrand = devicebrand;
        this.devicetype = devicetype;
        this.devicemodel = devicemodel;
        this.workpattern = workpattern;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getWorkorderoid() {
        return workorderoid;
    }

    public void setWorkorderoid(String workorderoid) {
        this.workorderoid = workorderoid;
    }

    public String getWorkoid() {
        return workoid;
    }

    public void setWorkoid(String workoid) {
        this.workoid = workoid;
    }

    public String getDevicebrand() {
        return devicebrand;
    }

    public void setDevicebrand(String devicebrand) {
        this.devicebrand = devicebrand;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getWorkpattern() {
        return workpattern;
    }

    public void setWorkpattern(String workpattern) {
        this.workpattern = workpattern;
    }
}

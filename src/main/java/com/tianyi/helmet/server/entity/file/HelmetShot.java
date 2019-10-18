package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.scene.Site;

/**
 * 头盔拍摄的资源基础类
 *
 * Created by liuhanc on 2018/1/15.
 */
public abstract class HelmetShot extends UploadEntity {
    private String machineCode;//机号
    private float lon;
    private float lat;
    private Integer siteId;//工地号
    private Site site;//工地信息
    private String thumbOssPath;//缩略图oss存储路径。对视频来说就是视频截图的缩略图，对图片就是图片本身的缩略图
    private String orderNo;//工单号

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getThumbOssPath() {
        return thumbOssPath;
    }

    public void setThumbOssPath(String thumbOssPath) {
        this.thumbOssPath = thumbOssPath;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

}

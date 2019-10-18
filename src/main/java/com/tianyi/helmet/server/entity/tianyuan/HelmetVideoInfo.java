package com.tianyi.helmet.server.entity.tianyuan;

import com.tianyi.helmet.server.entity.file.VideoKeyword;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 头盔视频信息
 * 返回给智能服务
 * Created by tianxujin on 2019/3/13 15:28
 */
public class HelmetVideoInfo {
    private int id;
    private long sizeKb;
    private long seconds;
    private String thumbOssPath;//缩略图oss存储路径。对视频来说就是视频截图的缩略图，对图片就是图片本身的缩略图
    private String ossPath;//文件存储oss路径
    private List<VideoKeyword> keywords;
    private LocalDateTime createTime;
    private String location;
    private String tyaccount;
    private int azvideo; // 0拍摄视频1安正通话录制视频
    private float lon;
    private float lat;
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public int getAzvideo() {
        return azvideo;
    }

    public void setAzvideo(int azvideo) {
        this.azvideo = azvideo;
    }

    public String getTyaccount() {
        return tyaccount;
    }

    public void setTyaccount(String tyaccount) {
        this.tyaccount = tyaccount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<VideoKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<VideoKeyword> keywords) {
        this.keywords = keywords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSizeKb() {
        return sizeKb;
    }

    public void setSizeKb(long sizeKb) {
        this.sizeKb = sizeKb;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public String getThumbOssPath() {
        return thumbOssPath;
    }

    public void setThumbOssPath(String thumbOssPath) {
        this.thumbOssPath = thumbOssPath;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }
}

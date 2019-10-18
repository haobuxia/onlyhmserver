package com.tianyi.helmet.server.service.scene;

import java.util.Date;

/**
 * 视频excel 的数据存储
 *
 * <p>
 * Created by liuhanc on 2018/7/25.
 */
public class VideoExcelDataPo {
    private String neUsername;//头盔账号
    private String jh;//机号
    private Date beginTime;//开始时间
    private Date endTime;//结束时间

    public VideoExcelDataPo() {
    }

    public VideoExcelDataPo(String neUsername, String jh, Date beginTime, Date endTime) {
        this.neUsername = neUsername;
        this.jh = jh;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public String getJh() {
        return jh;
    }

    public void setJh(String jh) {
        this.jh = jh;
    }

    public String getNeUsername() {
        return neUsername;
    }

    public void setNeUsername(String neUsername) {
        this.neUsername = neUsername;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

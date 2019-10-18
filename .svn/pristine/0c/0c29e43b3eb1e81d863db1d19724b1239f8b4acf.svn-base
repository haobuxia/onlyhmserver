package com.tianyi.helmet.server.entity.svc.po;

/**
 * 服务工单故障基本信息
 * Created by liuhanc on 2018/3/16.
 */
public class SvcFault extends SvcRwhAbstract {
    private boolean youchang;
    private double gongshifei;
    private boolean tingji;
    private String faultContent;
//    private String audioPath;

    public SvcFault() {
    }

    public SvcFault(String rwh, boolean youchang, float gongshifei, boolean tingji, String faultContent) {
        this.rwh = rwh;
        this.youchang = youchang;
        this.gongshifei = gongshifei;
        this.tingji = tingji;
//        this.audioPath = audioPath;
        this.faultContent = faultContent;
    }

    public String getFaultContent() {
        return faultContent;
    }

    public void setFaultContent(String faultContent) {
        this.faultContent = faultContent;
    }

    public boolean isYouchang() {
        return youchang;
    }

    public void setYouchang(boolean youchang) {
        this.youchang = youchang;
    }

    public double getGongshifei() {
        return gongshifei;
    }

    public void setGongshifei(double gongshifei) {
        this.gongshifei = gongshifei;
    }

    public boolean isTingji() {
        return tingji;
    }

    public void setTingji(boolean tingji) {
        this.tingji = tingji;
    }

//    public String getAudioPath() {
//        return audioPath;
//    }
//
//    public void setAudioPath(String audioPath) {
//        this.audioPath = audioPath;
//    }

    @Override
    public String toString() {
        return "rwh=" + rwh + ",youchang=" + youchang + ",gongshifei=" + gongshifei + ",tingji=" + tingji + ",faultContent=" + faultContent;
    }
}

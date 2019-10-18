package com.tianyi.helmet.server.entity.device;

import java.io.Serializable;

/**
 * Created by tianxujin on 2019/6/4 15:11
 */
public class TmnlInfo implements Serializable {
    private String tmnlID;// 终端ID
    private String tmnlProductNo;// 产品编号
    private String tmnlSatelliteType;// 生产批次号
    private String tmnlSatelliteNo;// 型号
    private String tmnlIMEI;// IMEI号
    private String tmnlSoftwareEdition; // 软件版本
    private Integer status; // 数据状态0未处理，3; //"设备编号已存在."2;// 入库重复-1;// 入库失败 1成功

    public String getTmnlSoftwareEdition() {
        return tmnlSoftwareEdition;
    }

    public void setTmnlSoftwareEdition(String tmnlSoftwareEdition) {
        this.tmnlSoftwareEdition = tmnlSoftwareEdition;
    }

    public String getTmnlSatelliteNo() {
        return tmnlSatelliteNo;
    }

    public void setTmnlSatelliteNo(String tmnlSatelliteNo) {
        this.tmnlSatelliteNo = tmnlSatelliteNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTmnlID() {
        return tmnlID;
    }

    public void setTmnlID(String tmnlID) {
        this.tmnlID = tmnlID;
    }

    public String getTmnlProductNo() {
        return tmnlProductNo;
    }

    public void setTmnlProductNo(String tmnlProductNo) {
        this.tmnlProductNo = tmnlProductNo;
    }

    public String getTmnlSatelliteType() {
        return tmnlSatelliteType;
    }

    public void setTmnlSatelliteType(String tmnlSatelliteType) {
        this.tmnlSatelliteType = tmnlSatelliteType;
    }

    public String getTmnlIMEI() {
        return tmnlIMEI;
    }

    public void setTmnlIMEI(String tmnlIMEI) {
        this.tmnlIMEI = tmnlIMEI;
    }
}

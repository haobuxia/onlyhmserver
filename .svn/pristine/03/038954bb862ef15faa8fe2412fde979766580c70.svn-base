package com.tianyi.helmet.server.entity.data;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * gps的每行数据基本信息
 * <p>
 * Created by liuhanc on 2017/11/1.
 */
public class GpsLineData extends IdEntity {

    private int fileId;
    private int lineNo;
    private String imei;
    private int byteLength;
    private LocalDateTime baseTime;
    private LocalDateTime realBaseTime;
    private int dataCount;
    private String dataIds;
    private String dataPart;//本行的数据部分
    private String dataParts;//数据部分解析后，带有id的数据集合

    public String getBaseTimeString() {
        return baseTime == null ? "" : Dates.format(Dates.toDate(baseTime), "yyyy-MM-dd HH:mm:ss");
    }

    public LocalDateTime getRealBaseTime() {
        return realBaseTime;
    }

    public void setRealBaseTime(LocalDateTime realBaseTime) {
        this.realBaseTime = realBaseTime;
    }

    public String getDataPart() {
        return dataPart;
    }

    public void setDataPart(String dataPart) {
        this.dataPart = dataPart;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getByteLength() {
        return byteLength;
    }

    public void setByteLength(int byteLength) {
        this.byteLength = byteLength;
    }

    public LocalDateTime getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(LocalDateTime baseTime) {
        this.baseTime = baseTime;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public String getDataIds() {
        return dataIds;
    }

    public void setDataIds(String dataIds) {
        this.dataIds = dataIds;
    }

    public String getDataParts() {
        return dataParts;
    }

    public void setDataParts(String dataParts) {
        this.dataParts = dataParts;
    }
}

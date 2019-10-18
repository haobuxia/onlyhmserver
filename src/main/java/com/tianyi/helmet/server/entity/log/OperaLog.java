package com.tianyi.helmet.server.entity.log;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 操作日志
 *
 * Created by yujiawei on 2018/07/27.
 */
public class OperaLog extends IdEntity {
    
	private String clientId;
    private LocalDateTime createTime;
    private String logContent;
    private String UUID;
    private String deviceType;
    private String logType;
    private String logflowId;
    private Integer orderNo;
    private LocalDate logDate;
    private LocalDateTime logTime;
    private Integer logNature;

	public String getCreateTimeString(){
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime),"yyyy-MM-dd HH:mm:ss");
    }

    public String getLogDateString(){
	    return logDate == null ? "" : Dates.format(Dates.toDate(logDate),"yyyy-MM-dd");
    }

    public String getLogTimeString(){
        return logTime == null ? "" : Dates.format(Dates.toDate(logTime),"HH:mm:ss.SSS");
        //return logTime == null ? "" : logTime.getHour() + ":" + logTime.getMinute() + ":" + logTime.getSecond() + "." + logTime.getNano()/1000000;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogflowId() {
        return logflowId;
    }

    public void setLogflowId(String logflowId) {
        this.logflowId = logflowId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public Integer getLogNature() {
        return logNature;
    }

    public void setLogNature(Integer logNature) {
        this.logNature = logNature;
    }

    @Override
	public String toString() {
		return "OperaLog [clientId=" + clientId + ", createTime=" + createTime + ", logContent=" + logContent + ", UUID=" + UUID + ", deviceType=" + deviceType
                + ", logType=" + logType + ", logflowId=" + logflowId + ", orderNo=" + orderNo + ", logDate=" + logDate + ", logTime=" + logTime
				+ "]";
	}
    
    
    
}

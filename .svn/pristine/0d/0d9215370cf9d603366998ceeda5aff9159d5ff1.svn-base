package com.tianyi.helmet.server.entity.log;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 头盔日志
 *
 * Created by liuhanc on 2017/12/13.
 */
public class HelmetLog extends IdEntity {
    private String clientId;
    private LocalDateTime createTime;
    private String logType;
    private int LogUserId;
    private String logContent;
    private String logUserName;//不入库

    public String getLogUserName() {
        return logUserName;
    }

    public void setLogUserName(String logUserName) {
        this.logUserName = logUserName;
    }

    public String getCreateTimeString(){
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime),"yyyy-MM-dd HH:mm:ss");
    }
    public String getLogTypeName(){
        return StringUtils.isEmpty(logType) ? "" : HelmetLogTypeEnum.valueOf(logType).getName();
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

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public int getLogUserId() {
        return LogUserId;
    }

    public void setLogUserId(int logUserId) {
        LogUserId = logUserId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}

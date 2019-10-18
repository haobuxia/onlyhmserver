package com.tianyi.helmet.server.entity.log;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 *  用户日志
 *
 * Created by liuhanc on 2017/12/13.
 */
public class UserLog extends IdEntity {
    private int userId;
    private String userType;//田一还是天远还是其他
    private LocalDateTime createTime;
    private String logType;
    private String logContent;
    private String userName;//用户姓名 不入库存储

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTimeString(){
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime),"yyyy-MM-dd HH:mm:ss");
    }

    public String getLogTypeName(){
        return StringUtils.isEmpty(logType) ? "" : UserLogTypeEnum.valueOf(logType).getName();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}

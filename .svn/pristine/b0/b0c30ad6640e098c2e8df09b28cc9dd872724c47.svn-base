package com.tianyi.helmet.server.entity.data;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 *  头盔数据基础类
 *
 * Created by liuhanc on 2017/12/12.
 */
public class HelmetData extends IdEntity {
    private String clientId;//clientId
    private String neUsername;//头盔的账号
    private LocalDateTime createTime;
    private String dataType;//数据分类，属性不入库 gps\sensor\state
    private Integer userId;//登陆用户id

    public String getNeUsername() {
        return neUsername;
    }

    public void setNeUsername(String neUsername) {
        this.neUsername = neUsername;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCreateTimeString(){
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime),"yyyy-MM-dd HH:mm:ss");
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

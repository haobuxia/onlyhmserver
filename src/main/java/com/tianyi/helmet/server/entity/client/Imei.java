package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 车辆蓝夜盒子信息
 *
 * Created by liuhanc on 2017/11/7.
 */
public class Imei extends IdEntity {
    private String imei;
    private LocalDateTime createTime;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}

package com.tianyi.helmet.server.entity.data;

/**
 * 头盔心跳数据
 * Created by wenxinyan on 2018/9/13.
 */
public class HelmetHeartBeat extends HelmetData {
    private Integer onlineType;//0无法获得工作状态、1表示正在视频通话、2表示正在拍报、3表示正在查看工单、4表示已黑屏待机、5表示离线

    public Integer getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(Integer onlineType) {
        this.onlineType = onlineType;
    }
}

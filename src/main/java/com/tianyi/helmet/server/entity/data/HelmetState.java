package com.tianyi.helmet.server.entity.data;

/**
 *  头盔传感器状态数据报文
 *
 * Created by liuhanc on 2017/10/9.
 */
public class HelmetState extends HelmetData {
    private Integer onlineState;//0在线 1忙碌 2离线
    private Integer netType;//0未知 1wifi 2wwan 3.2g 4.3g 5 4g

    public int getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    public int getNetType() {
        return netType;
    }

    public void setNetType(Integer netType) {
        this.netType = netType;
    }
}

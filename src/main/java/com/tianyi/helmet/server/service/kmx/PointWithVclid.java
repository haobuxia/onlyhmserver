package com.tianyi.helmet.server.service.kmx;

import com.sagittarius.bean.common.ValueType;
import com.sagittarius.bean.result.AbstractPoint;

/**
 * 带设备号的传感器某个时间点的数据值信息
 * <p>
 * Created by WangWei@TYKJ on 2017/10/23.
 *
 * wenxinyan 2018-8-16 修改了PointWithVclid()
 */
public class PointWithVclid {


    public PointWithVclid() {
    }

    //修改Point类型为AbstractPoint以便调用时传入不同类型的参数
    //2018-08-16 wxy
    public PointWithVclid(AbstractPoint point, String vclId, ValueType valueType, String value) {
        this.metric = point.getMetric();
        this.primaryTime = point.getPrimaryTime();
        this.secondaryTime = point.getSecondaryTime();
        this.valueType = valueType;
        this.value = value;
        this.vclId = vclId;
    }

    private String metric;//传感器
    private long primaryTime;//时间
    private long secondaryTime;//存储时间
    private ValueType valueType;//数据类型
    private String value;//数据值
    private String vclId;//设备号

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public long getPrimaryTime() {
        return primaryTime;
    }

    public void setPrimaryTime(long primaryTime) {
        this.primaryTime = primaryTime;
    }

    public long getSecondaryTime() {
        return secondaryTime;
    }

    public void setSecondaryTime(long secondaryTime) {
        this.secondaryTime = secondaryTime;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVclId() {
        return vclId;
    }

    public void setVclId(String vclId) {
        this.vclId = vclId;
    }
}

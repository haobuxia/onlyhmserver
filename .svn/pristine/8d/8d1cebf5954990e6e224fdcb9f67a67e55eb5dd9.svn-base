package com.tianyi.helmet.server.entity.data;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsCatagoryEnum;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * gps数据基础类
 *
 * Created by liuhanc on 2017/10/25.
 */
public abstract class AbstractGpsData extends IdEntity{
    private String clientId;//头盔识别号
    private String imei;//车辆识别号
    private LocalDateTime createTime;//时间
    private String[] dataFullPart;//完整原始数据.不入库.长度为4，分别是 idPart,relateTimePart,dataLengthPart,dataDataPart

    public String getCreateTimeString(){
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime),"yyyy-MM-dd HH:mm:ss");
    }

    public String[] getDataFullPart() {
        return dataFullPart;
    }

    public void setDataFullPart(String[] dataFullPart) {
        this.dataFullPart = dataFullPart;
    }

    public abstract GpsCatagoryEnum getGpsCatagoryEnum();

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

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

    @Override
    public String toString(){
        return "imei:"+imei+",时间:"+createTime+",原始数据:"+ Arrays.toString(dataFullPart);
    }
}

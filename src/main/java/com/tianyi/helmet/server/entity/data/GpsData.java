package com.tianyi.helmet.server.entity.data;

import com.tianyi.helmet.server.entity.data.gpsenum.GpsCatagoryEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;

import java.math.BigDecimal;

/**
 * 车载gps终端数据
 *
 * Created by liuhanc on 2017/10/23.
 */
public class GpsData extends AbstractGpsData{
    private int dataType;//数据类型
    private int val;

    public GpsCatagoryEnum getGpsCatagoryEnum(){
        return GpsCatagoryEnum.DATA;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getVal() {
        return val;
    }

    public float getRatioValue(){
        GpsDataTypeItemEnum itemEnum = GpsDataTypeItemEnum.get(this.dataType);
        double ratio = itemEnum.getRatio();
        //保留最多3位小数
        return new BigDecimal(ratio*val).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString(){
        return GpsDataTypeItemEnum.get(dataType).getName()+"数据:"+super.toString()+".值="+val+",处理分辨率后="+getRatioValue();
    }
}

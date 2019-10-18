package com.tianyi.helmet.server.service.track;

import com.google.common.base.Strings;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;
import java.util.List;

/**
 * webvtt类型字幕制作
 * <p>
 * Created by liuhanc on 2017/11/1.
 */
public class WebVttMaker extends Maker {

    public WebVttMaker(List<GpsDataTypeItemEnum> typeEnumList, LocalDateTime baseTime, LocalDateTime endTime) {
        super(typeEnumList, baseTime, endTime);
    }

    public void preContent() {
        super.append("WEBVTT");
        super.append(CHANGE_LINE);
        super.append(CHANGE_LINE);
    }
    public String curPreContent(){
        return "";
    }

    public String curAfterContent(){
        return "";
    }

    public String formatCueTime(long timeMillies) {
        return Dates.formatMillieseconds(timeMillies, '.');
    }

    public boolean outputCueCounter(){
        return false;
    }

    public int dataChangeLineCount(){
        return -1;
    }

    /**
     * 数据间的分隔符
     * @return
     */
    public String dataSpliter(){
        return "|";
    }

    public String valueFormat(GpsDataTypeItemEnum item,String val){
        if(GpsDataTypeItemEnum.ACTION.equals(item)||
                GpsDataTypeItemEnum.GPS_LOCATION.equals(item)||
                GpsDataTypeItemEnum.GYRO.equals(item)){
            return val;
        }

        int length = item.getDataLength();
        return Strings.padStart(val, length, '0');
    }

}

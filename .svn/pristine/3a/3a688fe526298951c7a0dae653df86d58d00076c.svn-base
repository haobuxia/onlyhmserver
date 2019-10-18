package com.tianyi.helmet.server.service.track;

import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.util.Dates;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * .sub类型字幕制作
 * <p>
 * Created by liuhanc on 2017/11/1.
 */
public class SubtitlesMaker extends Maker {


    public SubtitlesMaker(List<GpsDataTypeItemEnum> typeEnumList, LocalDateTime baseTime, LocalDateTime endTime) {
        super(typeEnumList, baseTime, endTime);
    }

    public void preContent() {
    }
    public String curPreContent(){
        return "<font size=8>";//face="微软雅黑"
    }

    public String curAfterContent(){
        return "</font>";
    }

    public String formatCueTime(long timeMillies) {
        return Dates.formatMillieseconds(timeMillies, ',');
    }

    public boolean outputCueCounter(){
        return true;
    }

    public int dataChangeLineCount(){
        return 1;
    }

    /**
     * 数据间的分隔符
     * @return
     */
    public String dataSpliter(){
        return "";
    }

    public String valueFormat(GpsDataTypeItemEnum item,String val){
        return StringUtils.isEmpty(val)?"0":val;
    }
}

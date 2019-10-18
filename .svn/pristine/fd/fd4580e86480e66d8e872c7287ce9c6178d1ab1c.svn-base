package com.tianyi.helmet.server.service.track;

import com.mchange.v2.lang.ObjectUtils;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;

import java.time.LocalDateTime;
import java.util.*;

/**
 *  字幕制作生成器
 *  
 * Created by liuhanc on 2017/12/21.
 */
public abstract class Maker {
    public static final String CHANGE_LINE = "\n";

    private List<GpsDataTypeItemEnum> typeEnumList;
    private int typeEnumListSize = 0;
    private Map<Integer, List<DoubleVo<Long, String>>> timeValListMap = null;
    private Long baseTime;
    private Long endTime;
    private int cueCounter = 1;
    private StringBuffer subtitlesBuffer = new StringBuffer();

    public Maker(List<GpsDataTypeItemEnum> typeEnumList, LocalDateTime baseTime, LocalDateTime endTime) {
        this.typeEnumList = typeEnumList;
        this.typeEnumListSize = typeEnumList.size();
        this.timeValListMap = new HashMap<>(typeEnumList.size());
        for (GpsDataTypeItemEnum te : typeEnumList) {
            timeValListMap.put(te.getId(), new ArrayList<DoubleVo<Long, String>>());
        }
        this.baseTime = Dates.toDate(baseTime).getTime();
        this.endTime = Dates.toDate(endTime).getTime();
    }

    /**
     * 字幕输出前的内容
     */
    public abstract void preContent();

    public abstract String curPreContent();
    public abstract String curAfterContent();
    /**
     * 时间格式化
     *
     * @param timeMillies
     * @return
     */
    public abstract String formatCueTime(long timeMillies);

    /**
     * 几条数据换行 -1表示不换行
     *
     * @return
     */
    public abstract int dataChangeLineCount();

    /**
     * 数据间的分隔符
     *
     * @return
     */
    public abstract String dataSpliter();

    /**
     * 是否在每节字母前增加序号
     *
     * @return
     */
    public abstract boolean outputCueCounter();

    /**
     * 数据项的值格式化
     *
     * @param val
     * @return
     */
    public abstract String valueFormat(GpsDataTypeItemEnum item, String val);

    public void updateValue(Integer dataTypeId, long time, String val) {
        timeValListMap.get(dataTypeId).add(new DoubleVo(time, val));
    }

    public void append(String str) {
        subtitlesBuffer.append(str);
    }

    public void make() {
        preContent();

        int vttCueCount = (int) (endTime - baseTime) / 1000 + ((endTime - baseTime) % 1000 == 0 ? 0 : 1);

        long oldEndTime = baseTime;

        String[] oldVals = new String[typeEnumList.size()];
        int j = 0;
        for (GpsDataTypeItemEnum te : typeEnumList) {
            String val = getDataVal(te.getId(), oldEndTime);
            oldVals[j] = val;
            j++;
        }

        for (int i = 0; i < vttCueCount; i++) {
            long time = baseTime + (i + 1) * 1000;
            String[] newVals = new String[typeEnumList.size()];
            j = 0;
            for (GpsDataTypeItemEnum te : typeEnumList) {
                String val = getDataVal(te.getId(), time);
                newVals[j] = val;
                j++;
            }

            if (!equals(oldVals, newVals)) {
                output(oldEndTime + 1, time, oldVals);//+1毫秒
                oldEndTime = time;
                oldVals = newVals;
            }

            if (i == vttCueCount - 1) {
                output(oldEndTime + 1, time + 1000, newVals);//+1毫秒
                oldEndTime = time;
                oldVals = newVals;
            }
        }
    }

    private boolean equals(String[] oldVals, String[] newVals) {
        boolean equals = true;
        for (int i = 0; i < oldVals.length; i++) {
            if (!ObjectUtils.eqOrBothNull(oldVals[i], newVals[i])) {
                equals = false;
                break;
            }
        }
        return equals;
    }

    private String getDataVal(Integer typeId, long time) {
        Comparator<DoubleVo<Long, String>> comparator = Comparator.comparing(DoubleVo::getKey);
        Optional<DoubleVo<Long, String>> kvOptional = timeValListMap.get(typeId).stream().filter(kv -> kv.getKey() <= time).max(comparator);
        if (kvOptional.isPresent()) {
            return kvOptional.get().getVal();
        }
        return null;
    }

    public String getResult() {
        String str = subtitlesBuffer.toString();
        subtitlesBuffer.setLength(0);
        return str;
    }

    protected void output(long beginDateTime, long endDateTime, String[] vals) {
        long startMillies = beginDateTime - baseTime;
        long endMillies = endDateTime - baseTime;
        if (outputCueCounter()) {
            subtitlesBuffer.append(cueCounter).append(CHANGE_LINE);
        }
        subtitlesBuffer.append(formatCueTime(startMillies)).append(" --> ").append(formatCueTime(endMillies)).append(CHANGE_LINE);
        subtitlesBuffer.append(dataFormat(vals)).append(CHANGE_LINE).append(CHANGE_LINE);
        cueCounter++;
    }

    protected String dataFormat(String[] vals) {
        StringBuffer temp = new StringBuffer();
        temp.append(curPreContent());
        int changeLineCount = dataChangeLineCount();
        String dataSpliter = dataSpliter();

        int dataCounter = 0;
        for (GpsDataTypeItemEnum te : typeEnumList) {
            dataCounter++;

            String name = te.getName();
            String value = vals[dataCounter - 1] == null ? "" : String.valueOf(vals[dataCounter - 1]);
            String str = name + "：" + valueFormat(te, value);
            temp.append(str);

            if (changeLineCount > 0 && dataCounter % changeLineCount == 0) {
                //换行
                temp.append(CHANGE_LINE);
            } else {
                if (dataCounter - 1 < typeEnumListSize - 1)
                    temp.append(dataSpliter);
            }
        }
        temp.append(curAfterContent());
        return temp.toString();
    }

}

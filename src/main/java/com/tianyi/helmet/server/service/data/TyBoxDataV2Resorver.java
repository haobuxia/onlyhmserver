package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.data.AbstractGpsData;
import com.tianyi.helmet.server.entity.data.GpsActionData;
import com.tianyi.helmet.server.entity.data.GpsData;
import com.tianyi.helmet.server.entity.data.GpsLineData;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.EncoderUtil;
import com.tianyi.helmet.server.vo.MainDetailVo;
import decode.InputParameter;
import decode.OutParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import translate.Translator;
import ty.pub.RawDataPacket;
import ty.pub.TransPacket;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 车载gps数据解析组件-版本2 CT100D
 * <p>
 * Created by liuhanc on 2018/05/21.
 */
@Component
public class TyBoxDataV2Resorver {
    private Logger logger = LoggerFactory.getLogger(TyBoxDataV2Resorver.class);
    private Translator mTranslator;

    private String doLineCut(String line, AtomicInteger startPos, int partLength, String partName) {
        int startIdx = startPos.get();
        int endIdx = startIdx + partLength;
        String part = Commons.subString(line, startPos.get(), endIdx);//2个长度1个字节
        if (part == null) {
            part = "长度不足无法拆分.总长度=" + line.length();
        }
        System.out.println(partName + ",startIdx=" + startIdx + ",partLength=" + partLength + ",endIdx=" + endIdx + ".hex数据=" + part);
        startPos.set(endIdx);
        return part;
    }

    //执行校验 校验码之前（标识位除外）信息内容相加之和，取低字节
    private boolean doDataVerify(String verifyDataPart, String verifyCodePart) {
        int dataLength = verifyDataPart.length();
        if (dataLength % 2 == 1) {
            logger.error("车载数据有误.计算校验码时数据长度不是偶数.length=" + dataLength);
            return false;
        }

        int size = dataLength / 2;
        long sum = 0l;
        for (int i = 0; i < size; i++) {
            //逐个字节的取出数据
            String oneByte = Commons.subString(verifyDataPart, i * 2, (i + 1) * 2);
            long part = Long.parseLong(oneByte, 16);
            sum += part;
        }

        String hex = Long.toHexString(sum);
        String lowerByteHex = hex.substring(hex.length() - 2);//取低字节
        if (!verifyCodePart.equalsIgnoreCase(lowerByteHex)) {
            logger.error("车载数据有误.校验码验证失败.计算得到=" + hex + ",低字节=" + lowerByteHex + ",传入=" + verifyCodePart);
            return false;
        }
        return true;
    }

    private TransPacket analysis(byte[] sourceDatab) {
        mTranslator = new Translator();

        InputParameter input = new InputParameter();
        RawDataPacket rawDataPacket = new RawDataPacket();
        rawDataPacket.setPacketData(sourceDatab);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("VclBrand", 1);
        input.setInfoJson(jsonObject.toJSONString());
        input.setRawDataPacket(rawDataPacket);
        OutParameter outParameter = mTranslator.execute(input);
        if (outParameter == null) {
            logger.error("DataAnalysisManager","数据解析错误");
            return null;
        }
        TransPacket transPacket = outParameter.getTransPacket();
        return transPacket;
    }
    public GpsData createGpsData(int dataId, int intVal) {
        GpsData gd = new GpsData();
        gd.setDataType(dataId);
        if ((dataId == 10 || dataId == 11) && intVal > 1200) {
            //电流,值超过1200就是有问题的,处理掉
            intVal = 1200;//暂时写成最大值
        }
        GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.get(dataId);
        double ratio = typeItemEnum.getRatio();
        gd.setVal((int) (intVal/ratio));
        return gd;
    }
    private AbstractGpsData getWorkData(TransPacket.WorkStatusRecord record){
        AbstractGpsData workData = null;

        String statusId = record.getWorkStatusId();
        String strValue = record.getValue();

        DataTypeItemEnum dataType = DataTypeItemEnum.get(statusId);
        if (dataType == null) {
            return null;
        }

        double dValue = Double.parseDouble(strValue);
        int value = (int)dValue;
        logger.info("DataAnalysisManager","statusId:" + statusId + "--value:" + value);
        switch (dataType) {
            case OIL_PRESSURE_100D://机油压力
                workData = createGpsData(WorkData.OIL_PRESSURE, value);
                break;
            case WATER_TEMPERATURE_100D://水温
                workData = createGpsData(WorkData.WATER_TEMPERATURE, value);
                break;
            case FUEL_CONSUMPTION_100D://燃料瞬间消耗
                workData = createGpsData(WorkData.FUEL_CONSUMPTION, value);
                break;
            case REVOLUTION_SPEED_100D://转速rpm
                workData = createGpsData(WorkData.REVOLUTION_SPEED, value);
                break;
            case TORQUE_PERCENT_100D://扭矩百分比
                workData = createGpsData(WorkData.TORQUE_PERCENT, value);
                break;
            case TORQUE_100D://扭矩
                workData = createGpsData(WorkData.TORQUE, value);
                break;
            case PUMP_PRESSURE_F_100D://F泵泵压Kg/cm2
                workData = createGpsData(WorkData.PUMP_PRESSURE_F, value);
                break;
            case PUMP_PRESSURE_R_100D://R泵泵压Kg/cm2
                workData = createGpsData(WorkData.PUMP_PRESSURE_R, value);
                break;
            case ELECTRIC_CURRENT_F_100D://F泵PC-EPC电流mA
                workData = createGpsData(WorkData.ELECTRIC_CURRENT_F, value);
                break;
            case ELECTRIC_CURRENT_R_100D://R泵PC-EPC电流mA
                workData = createGpsData(WorkData.ELECTRIC_CURRENT_R, value);
                break;
            case ACTION_TYPE_100D://动作类型
                workData = createActionData(WorkData.ACTION, strValue);
                break;
            case ACTION_100D://动作
                GpsActionData gad1 = new GpsActionData();
                gad1.setAction(value);
                gad1.setId(999);// 临时存储
                workData = gad1;
                break;
            case WORK_MODE://工作模式
                workData = resorveIntegerData(strValue,WorkData.WORK_MODE, true);
                break;
            case MODE_E://E模式调整
                workData = resorveIntegerData(strValue,WorkData.MODE_E, true);
                break;
        }
        return workData;
    }

    private AbstractGpsData createActionData(Integer action, String dataDataPart) {
        GpsActionData gad = new GpsActionData();
        String actionVal = "";
        if(dataDataPart == null || dataDataPart.length() < 16){
            return null;
        }
        actionVal = dataDataPart.substring(8, 16);//byte4
        StringBuffer sb = new StringBuffer(actionVal);
        if (!StringUtils.isEmpty(action) && !actionVal.equals("00000000")) {
            gad.setAction(action);//动作指示
        } else {
            gad.setAction(-99);//没有数据
        }
        String walk = dataDataPart.substring(5, 6);//byte3
        if (!StringUtils.isEmpty(actionVal)) {
            gad.setActionVal(sb.reverse().toString());//动作类型 8个字节，每1位表示1个动作的状态
        } else {
            gad.setActionVal("");//无数据
        }
        if (!StringUtils.isEmpty(walk) && dataDataPart.charAt(8) != 0) {
            gad.setWalk(Integer.parseInt(walk));//行走类型
        } else {
            gad.setWalk(-99);//没有数据
        }
        return gad;
    }
    /**
     * 解析基本的简单单一数据的传感器数据
     *
     * @param dataDataPart
     * @param dataId
     * @return
     */
    public GpsData resorveIntegerData(String dataDataPart, int dataId, boolean hexMode) {
        GpsData gd = new GpsData();
        gd.setDataType(dataId);
        if (hexMode) {//16进制的进行转换
            int intVal = Integer.parseInt(dataDataPart, 16);
            if ((dataId == 10 || dataId == 11) && intVal > 1200) {
                //电流,值超过1200就是有问题的,处理掉
                intVal = 1200;//暂时写成最大值
            }
            gd.setVal(intVal);
        } else {
            //工作模式\E模式 的值不转成10进制
            gd.setVal(Integer.parseInt(dataDataPart));
        }
        return gd;
    }
    /**
     * 解析gps数据
     *
     * @param line
     */
    public MainDetailVo<GpsLineData, AbstractGpsData> resorveGpsData(String line, String clientId) {
        TransPacket transPacket = analysis(hexStringToByte(line));
        Iterator<TransPacket.WorkStatusRecord> iterator = transPacket.getWorkStatusMapIter();
        int action = 0;
        List<AbstractGpsData> list = new ArrayList<>();
        while (iterator.hasNext()) {
            TransPacket.WorkStatusRecord record = iterator.next();
            AbstractGpsData workData = getWorkData(record);
            if (workData != null) {
                if(workData instanceof GpsActionData && workData.getId()==999) {
                    action = ((GpsActionData) workData).getAction();
                } else {
                    list.add(workData);
                }
            }
        }
        // 解析行数据
        GpsLineData lineData = resorveLineData1(transPacket);
        LocalDateTime baseTime = lineData.getBaseTime();
        if (list != null && list.size() > 0) {
            for(AbstractGpsData gpsData : list){
                gpsData.setClientId(clientId);
                gpsData.setCreateTime(baseTime);
                gpsData.setImei(transPacket.getBaseInfoMap().get("TY_0001_00_4@string"));//imei最后1个字符F忽略
//                gpsData.setDataFullPart(new String[]{newIdPart, relateTimePart, onePgnDataContentLengthHex, contentPart});
//                dataParts.append(dataCount.incrementAndGet()).append("-").append(Arrays.toString(gpsData.getDataFullPart()));
                if(gpsData instanceof GpsActionData){
                    ((GpsActionData) gpsData).setAction(action);
                }
            }
        } else {
//            logger.error("车载数据有误,无法解析出具体数据." + contentPart + ",pgnId=" + newIdPart);
        }
        MainDetailVo<GpsLineData, AbstractGpsData> md = new MainDetailVo();
        if (lineData == null)
            return md;

        lineData.setRealBaseTime(baseTime);
        md.setMain(lineData);
        md.setList(list);

        System.out.println("解析完毕-----------------");
        System.out.println("总数据量=" + list.size());
        System.out.println("============================================================================");
        return md;
    }

    private GpsLineData resorveLineData1(TransPacket transPacket) {
//        int dataLength = Integer.parseInt(transPacket.getBaseInfoMap().get("TY_0001_02_08@string"), 16) / 8 * 2;
//        System.out.println("消息体字符串的长度(10进制)=" + dataLength);
        //返回初步解析结果
        GpsLineData lineData = new GpsLineData();
        lineData.setImei(transPacket.getBaseInfoMap().get("TY_0001_00_4@string"));
//        lineData.setByteLength(dataLength);
        String baseTimeStr = transPacket.getBaseInfoMap().get("TY_0001_02_10@long");
        if(baseTimeStr!=null && baseTimeStr.length() > 0){
            long baseTimeLong = Long.parseLong(baseTimeStr);
            LocalDateTime baseTime = LocalDateTime.ofEpochSecond(baseTimeLong/1000,0, ZoneOffset.ofHours(8));
            lineData.setBaseTime(baseTime);
        }
        return lineData;
    }

    /**
     * 把16进制字符串转换成字节数组
     * @param hex
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }
    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static void main(String[] args) {
        TyBoxDataV2Resorver resorver = new TyBoxDataV2Resorver();
        MainDetailVo<GpsLineData, AbstractGpsData> md = resorver.resorveGpsData("7E0F861477034336949FD410682B4000130314091E3404680A02BB0000013F0F0FF2F3F4F5F6F702CC0000023F3F0200000100000002CE000001210000000009C4330002D40000021F3C023C550095014F02E80000023F3F0524252627282902ED0000011F20930000780B000003090000013F0F0FF2F3F4F5F6F7032E0000023F400118052D0011040330000001210000000009C4330003380000021F3D01021316E616E512007E", "12312");
        List<AbstractGpsData> list = md.getList();
        GpsLineData ld = md.getMain();
    }
}

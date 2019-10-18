package com.tianyi.helmet.server.service.data;

import cn.edu.thu.tianyuan.LZ77Decompression;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.SgGpsDataTypeItemEnum;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * tybox数据解析组件-版本3 600s
 * <p>
 * Created by tianxujin on 2019/02/26.
 */
@Component
public class TyBoxDataV3Resorver {
    private Logger logger = LoggerFactory.getLogger(TyBoxDataV3Resorver.class);
    private Translator mTranslator;

    public TyBoxLineData resorveLineData(String signal) {

        if (StringUtils.isEmpty(signal))
            return null;

        //返回初步解析结果
        TyBoxLineData lineData = new TyBoxLineData();
        lineData.setDataPart(signal);

        LZ77Decompression datatransfor = new LZ77Decompression();

        // 消息体长度
        int length = hexToDecimal(subString(signal, 44, 4));
        int byte1 = length / 8;// 整字节的长度
        int byte2 = length % 8;// 最后一个字节的位数
        // 先取到byte1的长度
        String str1 = subString(signal, 48, byte1 * 2);
        // 如果余数不等于0，则取下一个字节的位长度，取完以后转换成整字节
        String str2 = "";
        if (byte2 != 0) {
            str2 = subString(signal, 48 + byte1 * 2, 2);
        }
        // 拼接两个长度
        String content = str1 + str2;

        String decompress = datatransfor.decompress(content, length);
        String s1 = signal.substring(0, 48);
        String s2 = signal.substring(signal.length() - 6);

        signal = s1 + decompress + s2;

        lineData.setDataParts(signal);

        return lineData;
    }
    // region Net版本SubString方法

    /**
     * Net版本SubString方法
     *
     * @param s
     *            源字符串
     * @param index
     *            字符串开始索引
     * @param length
     *            截取长度
     * @return 截取的字符串
     */
    public static String subString(String s, int index, int length) {
        int Endposition = length + index;
        return s.substring(index, Endposition);
    }
    // endregion
    // region 十六进制转十进制

    /**
     * 十六进制转十进制（int）
     */
    public static int hexToDecimal(String hexString) {

        return Integer.parseInt(hexString, 16);
    }
    private TyBoxLineData resorveLineData1(TyBoxLineData lineData, TransPacket transPacket) {
        //返回初步解析结果
        lineData.setImei(transPacket.getBaseInfoMap().get("TY_0001_00_4@string"));
        String baseTimeStr = transPacket.getBaseInfoMap().get("TY_0001_02_10@long");
        if(baseTimeStr!=null && baseTimeStr.length() > 0){
            long baseTimeLong = Long.parseLong(baseTimeStr);
            LocalDateTime baseTime = LocalDateTime.ofEpochSecond(baseTimeLong/1000,0, ZoneOffset.ofHours(8));
            lineData.setCreateTime(baseTime);
        }
        return lineData;
    }
    /**
     * 解析gps数据
     *
     * @param line
     */
    public MainDetailVo<TyBoxLineData, AbstractGpsData> resorveTyBoxData(String line, String clientId) {
        TyBoxLineData lineData = resorveLineData(restitute(line));// 解压缩
        if (lineData == null)
            return null;
        Date date = new Date();
        lineData.setCreateTime(LocalDateTime.now());
        lineData.setClientId(clientId);

        // line 解析出gpsdata的集合元素 ----待解析
        String lineString = lineData.getDataParts();// 解压之后的行数据
        TransPacket transPacket = analysis(hexStringToByte(lineString));
        resorveLineData1(lineData, transPacket);
        Iterator<TransPacket.WorkStatusRecord> iterator = transPacket.getWorkStatusMapIter(); // 通过api获取一行数据的解析结果
        int action = 0;
        List<AbstractGpsData> list = new ArrayList<>();
        while (iterator.hasNext()) {
            TransPacket.WorkStatusRecord record = iterator.next();
            AbstractGpsData workData = getWorkData(record);
            if (workData != null) {
                list.add(workData);
            }
        }
        // 解析行数据
        LocalDateTime baseTime = lineData.getCreateTime();
        if (list != null && list.size() > 0) {
            for(AbstractGpsData gpsData : list){
                gpsData.setClientId(clientId);
                gpsData.setCreateTime(baseTime);
                gpsData.setImei(transPacket.getBaseInfoMap().get("TY_0001_00_4@string"));//imei最后1个字符F忽略
            }
        } else {
//            logger.error("车载数据有误,无法解析出具体数据." + contentPart + ",pgnId=" + newIdPart);
        }
        MainDetailVo<TyBoxLineData, AbstractGpsData> md = new MainDetailVo();
        if (lineData == null)
            return md;
        md.setMain(lineData);
        md.setList(list);
        System.out.println("解析完毕-----------------");
        System.out.println("总数据量=" + list.size());
        System.out.println("============================================================================");
        return md;

    }

    private AbstractGpsData getWorkData(TransPacket.WorkStatusRecord record) {
        String statusId = record.getWorkStatusId();
        String strValue = record.getValue();
        DataTypeItemSgEnum dataType = DataTypeItemSgEnum.get(statusId); // 将api的key映射到枚举类型
        if (dataType == null) {
            return null;
        }

        double dValue = Double.parseDouble(strValue);
        int value = (int)dValue;
        logger.info("DataAnalysisManager","statusId:" + statusId + "--value:" + value);
        AbstractGpsData workData = createGpsData(dataType.getGpsId(), value);
        return workData;
    }

    public GpsData createGpsData(int dataId, int intVal) {
        GpsData gd = new GpsData();
        gd.setDataType(dataId);
        SgGpsDataTypeItemEnum typeItemEnum = SgGpsDataTypeItemEnum.get(dataId);
        double ratio = typeItemEnum.getRatio();
        gd.setVal((int) (intVal/ratio));
        return gd;
    }

    /**
     * 新协议转义还原
     * @param s
     * @return
     * 新协议是"7E"开头，"7E"结尾
     * 0x7e<-------->0x7d后紧跟一个0x02；
     * 0x7d<-------->0x7d后紧跟一个0x01；
     */
    public static String restitute(String s){
        if (s.startsWith("7E") || s.endsWith("7E")) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            while (i<s.length()-1) {
                String sub = s.substring(i, i+2);
                if (sub.equals("7D")) {
                    String sub1 = s.substring(i+2, i+4);
                    if (sub1.equals("02")) {
                        sub = "7E";
                        i = i + 4;
                    } else if (sub1.equals("01")){
                        i = i + 4;
                    } else {
                        i = i + 2;
                    }
                } else {
                    i = i + 2;
                }
                sb.append(sub);
            }
            s = sb.toString();
        }
        return s;
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
    private TransPacket analysis(byte[] sourceDatab) {
        mTranslator = new Translator();
        InputParameter input = new InputParameter();
        RawDataPacket rawDataPacket = new RawDataPacket();
        rawDataPacket.setPacketData(sourceDatab);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("VclBrand", 12);// 这个是品牌，12代表神钢，1代表是小松的
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

    public static void main(String[] args) {
        TyBoxDataV3Resorver resorver = new TyBoxDataV3Resorver();
        MainDetailVo<TyBoxLineData, AbstractGpsData> md = resorver.resorveTyBoxData("7E0F866323033366344FD410682F4000130402131D0E18D4BA0020C300DE0000D0D491F9F75F01F10F801B0EFFEE005400C5A74000A83B80200020DEB66D936E524000F4C1E0AF1D005A57462AA0FE4620EC00706120003A3D5B0A7D0380F803A44D9BE71390E501145805D4E516A406E2190000400078F8E880E0FC01BA760DFA09C8150678005200EBAB00220320F607285B36E52720E249B000385130C7231071003C285108801E600FF400403E800AAB809A1E4194FD03840D9BC85290F71D22F673B09EA5A0FD5744FCCB680204AC86D92CC50480D882344224560097442606D204A83807933A1242F901AA564DF44868E52A68752C2A40CB01945798D4CD089A003D3F40D6AC8D1E090DDDA10900752AC67B246405037B0704080E0008FC1BE70F40802CF86D134A40540030E0DFCAE26470647D7344C03A02101A34F0F89C123081E078A243C38C247468009B1B9C06C42B8623C0D701D0BAAD3029B00D45653F40D2A4BD1F09D16DA10970F7A628EF07881A35F52321C641B20B3949328F5741DD06A3A522EFA7B8C2A436F715810E9275BC1299CE2CA27D219B7825D285513A099C74C90DDF2F22DE5094FC03346D5A879C2499ED3745DD3F40D3A6BDC8499281FF018206CD4C4E928CFC55D03CC16829FA03A0755A6152FE23680410FC004993062427054480C2941A010B77680460FC004993D622270544C0070CEB1D10025D62024C0EA0B02A8004A02CA311B0021FBB7F4A40540030E0DF8A7F4A04ACC0B7EE9F920002D61180D0A08107012BF0ADFBA744C00402022674681040C00A7CEBFE2919C0E606A701F18AE110D0F203040DDA949C1410013F03690400FD004993B62227054400550F90346932722A40045CE5048909CC0912805507A5150224602C278D00B410A069D33CE484800870EB81680780D69591102001701D6904F085004993465E0723601086F50B8801FF80112540B221042CD641790D0009600C48F1E19F80C802A06AD58A0B600488C2000F400A607D2340640494F60055ABE6DA0323003704881AB5DE101801BB211071003CF81408801E600FF408E8ED014AD7D50324C018877F7711A011D00BC43F01CC05100400C4A3468D160E80000801D338FCB303C0858100E8F46C29F40D47007E", "12312");
        List<AbstractGpsData> list = md.getList();
        TyBoxLineData ld = md.getMain();

    }
}

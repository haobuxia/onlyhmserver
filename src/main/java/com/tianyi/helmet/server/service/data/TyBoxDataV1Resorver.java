package com.tianyi.helmet.server.service.data;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.data.*;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.EncoderUtil;
import com.tianyi.helmet.server.vo.MainDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 车载gps数据解析组件-版本1 CT100B
 * <p>
 * Created by liuhanc on 2017/10/19.
 */
@Component
public class TyBoxDataV1Resorver {
    private Logger logger = LoggerFactory.getLogger(TyBoxDataV1Resorver.class);


    public GpsLineData resorveLineData(String line) {

        if (StringUtils.isEmpty(line))
            return null;

        if (line.length() < 46) {
            //46是报文的前面部分 42个长度 + 结尾4个长度
            logger.error("车载数据有误.长度不足46位,无效." + line);
            return null;
        }

        String startPart = Commons.subString(line, 0, 2);//2个长度1个字节
        String endPart = Commons.subString(line, line.length() - 2, line.length());//结束符 2个长度1个字节
        if (!"0F".equals(startPart) || !"00".endsWith(endPart)) {
            logger.error("车载数据有误.不是以0F开头以00结尾.line=" + line);
            return null;
        }

        String imeiPart = Commons.subString(line, 2, 18);//16个长度8个字节
        String lengthPart = Commons.subString(line, 18, 22);//4个长度2个字节
        int length = Integer.parseInt(lengthPart, 16);
        if (line.length() - 22 - 4 != length * 2) {//-22表示不考虑前面的imei+length这22个字符的长度
            //lengthPart对应长度表示 信息类型到结束符总长度
            logger.error("车载数据有误.数据帧长度验证失败.计算得到=" + (line.length() - 22 - 4) + ",传入=" + (length * 2));
//            return Collections.emptyList();
        }
        String typePart = Commons.subString(line, 22, 24);//2个长度1个字节  固定值22，表示是APP消息，即通过蓝牙发给头盔的消息类型
        if (!"22".equals(typePart)) {
            logger.error("车载数据有误.类型验证失败.期望是22,传入=" + typePart);
        }
//        String versionPart = Commons.subString(line,24,30);//6个长度3个字节
        String timePart = Commons.subString(line, 30, 42);//12个长度6个字节
        String dataPart = Commons.subString(line, 42, line.length() - 4);//
        String verifyPart = Commons.subString(line, line.length() - 4, line.length() - 2);//校验码 2个长度1个字节

        LocalDateTime baseTime = calcuteTime(timePart);
        //verify
        int size = (line.length() - 4) / 2;
        long sum = 0;
        for (int i = 0; i < size; i++) {
            String str = Commons.subString(line, i * 2, (i + 1) * 2);
            long part = Long.parseLong(str, 16);
            sum += part;
        }
        String hex = Long.toHexString(sum);
        if (!verifyPart.equalsIgnoreCase(hex.substring(hex.length() - 2))) {
            logger.error("车载数据有误.校验码验证失败.计算得到=" + hex + ",传入=" + verifyPart);
//            return Collections.emptyList();
        }


        GpsLineData lineData = new GpsLineData();
        lineData.setImei(imeiPart.substring(0, 15));
        lineData.setByteLength(length);
        lineData.setBaseTime(baseTime);
        lineData.setDataPart(dataPart);

        return lineData;
    }

    /**
     * 解析gps数据
     * <p>
     * 规范：
     * gps车载终端数据是1个字符串。字符串根据一定的规则进行切分后，每个小的部分都是16进制字符串，可以转换成10进制，来表示一定的数据定义
     * 以下为数据规范（每个数据项的顺序是固定的）：
     * 1、字符串的头两位（第1个字节）肯定是0F,最后两位（最后1个字节）肯定是00；
     * 2、字符串的接下来16位（8个字节）表示IMEI，其中字符串的最后1位是F可忽略，因为IMEI是15位的长度，此字符串不需要转10进制；
     * 3、消息帧长度（2个字节），转成10进制后表示：信息类型到数据内容结束，不包括结束符和校验码两个字节
     * 4、消息类型（1个字节），固定值22，表示是APP消息，即通过蓝牙发给头盔的消息类型
     * 5、版本号信息（3个字节）
     * 6、信息生成时间（6个字节），每个字节表示1个时间，转成10进制后分别是年月日时分秒，表示当前数据的基准生成时间；
     * 7、数据内容（可变个字节），具体解释见下面；
     * 8、校验码（1个字节），是用来校验前面1-7这7个部分数据的。算法：将前面所有数据逐个字节按16进制相加，得到结果16进制字符串后，取最后两位(1个字节)与当前校验码对比，一致则通过；
     * 9、结束符00，固定值。
     * <p>
     * 其中 数据内容（可变个字节） 的规范是：
     * 数据内容中可以包含多个数据项，每个数据项由3部分组成，分别是：
     * 1、数据id：标志了当前数据表示的是什么数据，数据id定义参见 GpsDataItemEnum 枚举； 2个字节
     * 2、相对时间：表示此数据的采集时间，相对于上述 信息生成时间 ，即数据实际采集时间为：信息生成时间+相对时间；  2个字节
     * 3、数据长度：表示当前数据内容的长度，用来为下面数据内容切割提供支持；2个字节
     * 4、数据内容：实际数据内容。截取的长度需根据数据长度的定义来截取；
     *
     * @param line
     */
    public MainDetailVo<GpsLineData, AbstractGpsData> resorveGpsData(String line, String clientId) {
        MainDetailVo<GpsLineData, AbstractGpsData> md = new MainDetailVo();

        GpsLineData lineData = resorveLineData(line);
        if (lineData == null)
            return md;

        LocalDateTime baseTime = lineData.getBaseTime();
        lineData.setRealBaseTime(baseTime);
        String dataPart = lineData.getDataPart();
        List<AbstractGpsData> list = new ArrayList<>();
        int startIdx = 0;
        int dataCount = 0;
        StringBuffer dataParts = new StringBuffer();

        while (startIdx < dataPart.length()) {
            String idPart = Commons.subString(dataPart, startIdx, startIdx + 4);//2个字节
            String relateTimePart = Commons.subString(dataPart, startIdx + 4, startIdx + 8);//2个字节
            String dataLengthPart = Commons.subString(dataPart, startIdx + 8, startIdx + 12);//2个字节
            if (idPart == null || relateTimePart == null || dataLengthPart == null) {
                String error = "车载数据有误,数据长度有误.则退出数据解析.剩余数据的长度不足解析为1个数据项.剩余数据=" + dataPart.substring(startIdx);
                dataParts.append("ERR-[" + error + "]");
                logger.error(error);
                break;
            }

            int id = Integer.parseInt(idPart, 16);
            int relateTime = Integer.parseInt(relateTimePart, 16);//毫秒
            int dataLength = Integer.parseInt(dataLengthPart, 16);

            int endIdx = startIdx + 12 + (dataLength * 2);
            if (dataLength == 0 || dataPart.length() < endIdx) {
                String error = "车载数据有误,数据长度有误.id=" + idPart + ",相对时间=" + relateTimePart + ",长度=" + dataLength + ",但剩余数据的长度不足.剩余数据=" + dataPart.substring(startIdx + 12);
                dataParts.append("ERR-[" + error + "]");
                logger.error(error);
                break;
            }

            String dataDataPart = Commons.subString(dataPart, startIdx + 12, endIdx);//+12是前面3段元数据的长度
//            logger.debug("发现新数据.pos="+(startIdx+42)+".id="+idPart+",time="+relateTimePart+",length="+dataLengthPart+",data="+dataDataPart);

            try {
                AbstractGpsData gpsData = createGpsData(dataDataPart, id);
                if (gpsData != null) {
                    dataCount++;
                    gpsData.setClientId(clientId);
                    gpsData.setCreateTime(baseTime.plusNanos(1_000_000l * relateTime));//相对时间单位是毫秒
                    gpsData.setImei(lineData.getImei());//imei最后1个字符F忽略
                    gpsData.setDataFullPart(new String[]{idPart, relateTimePart, dataLengthPart, dataDataPart});
                    dataParts.append(dataCount).append("-").append(Arrays.toString(gpsData.getDataFullPart()));
                    list.add(gpsData);
                } else {
                    logger.error("车载数据有误,无法解析出具体数据." + dataDataPart+",id="+id);
                }
            } catch (Exception e) {
                logger.error("车载数据有误,解析具体数据发生异常.id=" + idPart + ",相对时间=" + relateTimePart + ",长度=" + dataLength, e);
            }

            startIdx = endIdx;
        }

        lineData.setDataCount(dataCount);
        lineData.setDataParts(dataParts.toString());
        md.setMain(lineData);
        md.setList(list);

        return md;
    }

    /**
     * 构造1个gps数据对象
     *
     * @param dataDataPart
     * @param id
     * @return
     */
    public AbstractGpsData createGpsData(String dataDataPart, int id) {
        AbstractGpsData gpsData = null;
        if (id == GpsDataTypeItemEnum.ACTION.getId()) {
            //动作
            gpsData = resorveActionData(dataDataPart);
        } else if (id == GpsDataTypeItemEnum.GYRO.getId()) {
            //陀螺仪
            gpsData = resorveGyroData(dataDataPart);
        } else if (id == GpsDataTypeItemEnum.GPS_LOCATION.getId()) {
            //位置
            gpsData = resorveLocationData(dataDataPart);
        } else {
            //其他 int型的数据统一进行保存
            gpsData = resorveGpsData(dataDataPart, id);
        }
        return gpsData;
    }

    /**
     * 解析地理定位数据
     * 第0个字节的最高位(bit7，左数第1位)表示纬度是北纬还是南纬
     * 字节0-3表示纬度具体值
     * 第4个字节的最高位(bit7，左数第1位)表示经度是东经还是西经
     * 字节4-7表示经度具体值
     * 字节8,9表示速度
     * 字节10,11表示方向
     * 字节12的最高位(bit7，左数第1位)表示gps位置的新旧标志
     * 字节12的次高位(bit6，左数第2位)表示海拔正负
     * 字节12的剩余6位+字节13合计表示海拔具体值
     * 字节14表示星星数量
     * 字节15-20折6位表示gps时间。每个字节表示1个时间，转成10进制后分别是年月日时分秒
     *
     * @param dataDataPart 共21个字节，长度=42
     * @return
     */
    public GpsLocationData resorveLocationData(String dataDataPart) {
        /*
        String latPart = dataDataPart.substring(0, 8);//4个字节 纬度
        String lonPart = dataDataPart.substring(8, 16);//4个字节 经度
        String speedPart = dataDataPart.substring(16, 20);//2个字节 速度

        String orientPart = dataDataPart.substring(20, 24);//2个字节 方向
        String gpsAltPart = dataDataPart.substring(24, 26);//1个字节 gps位置的新旧标志、海拔正负
        String altPart = dataDataPart.substring(26, 28);//1个字节 海拔具体值

        String starPart = dataDataPart.substring(28, 30);//1个字节 星星数量
        String timePart = dataDataPart.substring(30, 42);//6个字节 gps时间*/
        JSONObject json = JSONObject.parseObject(dataDataPart);
        int latns = (int)json.get("latns");
        String lat = json.get("lat").toString();
        int lonew = (int)json.get("lonew");
        String lon = json.get("lon").toString();
        String speed = json.get("speed").toString();
        String orient = json.get("orient").toString();
        int oldNew = (int)json.get("oldnew");
        int altPosNeg = (int)json.get("altPosNeg");
        String alt = json.get("alt").toString();
        String star = json.get("star").toString();
        String gpsTime = (String)json.get("gpsTime");

        GpsLocationData gld = new GpsLocationData();
        gld.setLatns(latns);
        gld.setLat(Float.parseFloat(lat));
        gld.setLonew(lonew);
        gld.setLon(Float.parseFloat(lon));
        gld.setSpeed(Integer.parseInt(speed, 16));//速度
        gld.setOrient(Integer.parseInt(orient, 16));//方向
        gld.setOldnew(oldNew);
        gld.setAltPosNeg(altPosNeg);//第6个bit表示海拔数值的正负  0-正值，1-负值
        gld.setAlt(Integer.parseInt(alt, 16));//海拔
        gld.setStar(Integer.parseInt(star, 16));//星数
        if (oldNew == 0) {
            //0表示gps无效,此时时间值是错误的
            gld.setGpsTime(LocalDateTime.of(2000, 1, 1, 1, 1, 1));
        } else {
            gld.setGpsTime(LocalDateTime.parse(gpsTime));
        }
        return gld;
    }

    /**
     * 根据4个字节的数据计算经纬度的标志和具体值
     * 第1个字节的最高位表示东西经还是南北纬标志
     * 第1个字节的最高位替换为0后组成的8个字符(4个字节)表示具体的经纬度的值。
     *
     * @param byte4String
     * @return
     */
    public int[] calcuteLatLong(String byte4String) {
        String byte1Str = byte4String.substring(0, 2);//取第1个字节
        byte byte1 = EncoderUtil.hexDecode(byte1Str)[0];//第1个字节
        String latBits = EncoderUtil.byteToBits(byte1);//转成bit
        int flag = Integer.parseInt(latBits.substring(0, 1));//最高位(第7bit)作为标志
        byte byte1New = EncoderUtil.bitsToByte("0" + latBits.substring(1));//更新第1个字节的最高位为0
        String byte1StrNew = EncoderUtil.hexEncode(new byte[]{byte1New});//得到第1个字节
        String byte4StringNew = byte1StrNew + byte4String.substring(2);//得到4个字节的新值
        int val = Integer.parseInt(byte4StringNew, 16);//得到新值对应的经纬度的具体值
        return new int[]{flag, val};
    }

    /**
     * 将经纬度的值转换成a.b的格式。其中a表示度，b表示0.b度。
     * <p>
     * 经纬度值的解析步骤是：
     * 0 将这个具体的16进制的经纬度的值转成10进制；
     * 1 将这个10进制数/10000，得到一个余数mmmm，得到1个商数dd60ff；
     * 2 将步骤1的商数dd60ff/60,得到1个余数ff，得到1个商数dd；
     * 3 步骤2的商数dd表示经度纬度的度值；
     * 4 步骤1的余数mmm和步骤2的余数ff组合最终的分值f = ff + 0.0001 * mmmm；
     * 5 最终的经度纬度值=dd+(f/60)；
     *
     * @param fullNumber
     * @return
     */
    public float calcuteLatLong(int fullNumber) {
        int mmmm = fullNumber % 10000;//余数
        int dd60ff = fullNumber / 10000;//商数
        int dd = dd60ff / 60;//商数
        int ff = dd60ff % 60;//余数
        double f = ff + 0.0001 * mmmm;
        double d2 = f / 60;
        return (float) (dd + d2);
    }


    /**
     * 计算时间数据
     *
     * @param timePart
     * @return
     */
    private LocalDateTime calcuteTime(String timePart) {
        int year = Integer.parseInt(timePart.substring(0, 2), 16);
        int month = Integer.parseInt(timePart.substring(2, 4), 16);
        int day = Integer.parseInt(timePart.substring(4, 6), 16);
        int hour = Integer.parseInt(timePart.substring(6, 8), 16);
        int min = Integer.parseInt(timePart.substring(8, 10), 16);
        int sec = Integer.parseInt(timePart.substring(10, 12), 16);
        LocalDateTime baseTime = LocalDateTime.of(2000 + year, month, day, hour, min, sec);
        return baseTime;
    }

    /**
     * 解析陀螺仪数据
     * 参数名称		字节数
     * 前后姿态角		2
     * 左右姿态角		2
     * 旋转角度		4
     * 旋转最大角速度		2
     * 旋转平均角速度		2
     * 减速时角加速度		4
     * 加速时角加速度		4
     * 回转时间		1
     *
     * @param dataDataPart 共21个字节，长度=42
     * @return
     */
    public GpsGyroData resorveGyroData(String dataDataPart) {
        if (dataDataPart.length() != 42) {
            logger.error("陀螺仪数据长度不是42个字符21个字节." + dataDataPart);
            return null;
        }
        String fb = dataDataPart.substring(0, 4);
        String lr = dataDataPart.substring(4, 8);
        String rt = dataDataPart.substring(8, 16);
        String rt_ms = dataDataPart.substring(16, 20);
        String rt_as = dataDataPart.substring(20, 24);
        String da = dataDataPart.substring(24, 32);
        String ua = dataDataPart.substring(32, 40);
        String bt = dataDataPart.substring(40, 42);

        GpsGyroData ggd = new GpsGyroData();
        ggd.setFrontBack(Integer.parseInt(fb, 16));
        ggd.setLeftRight(Integer.parseInt(lr, 16));
        ggd.setRotate(Integer.parseInt(rt, 16));
        ggd.setRotateMaxSpeed(Integer.parseInt(rt_ms, 16));
        ggd.setRotateAvgSpeed(Integer.parseInt(rt_as, 16));
        ggd.setDownAcceleration(Long.parseLong(da, 16));
        ggd.setUpAcceleration(Long.parseLong(ua, 16));
        ggd.setBackTime(Integer.parseInt(bt, 16));
        return ggd;
    }

    /**
     * 解析动作数据
     * 共3个字节，长度=6
     * <p>
     * 动作数据分3个字节。
     * 字节1：动作指示
     * 字节2：动作类型 按位解析
     * bit0=1,动臂上升
     * bit1=1,动臂下降
     * bit2=1,斗杆回收
     * bit3=1,斗杆伸出
     * bit4=1,铲斗挖掘
     * bit5=1,铲斗卸载
     * bit6=1,回转
     * bit7=1,行走
     * 字节3：行走类型
     *
     * @param dataDataPart
     * @return
     */
    public GpsActionData resorveActionData(String dataDataPart) {
        if (dataDataPart.length() != 6) {
            logger.error("动作数据长度不是6个字符3个字节." + dataDataPart);
            return null;
        }

        GpsActionData gad = new GpsActionData();
        gad.setAction(Integer.parseInt(dataDataPart.substring(0, 2), 16));//动作指示
        byte actionValByte = EncoderUtil.hexDecode(dataDataPart.substring(2, 4))[0];
        String actionValBits = EncoderUtil.byteToBits(actionValByte);
        gad.setActionVal(actionValBits);//动作类型 8个字节，每1位表示1个动作的状态
        gad.setWalk(Integer.parseInt(dataDataPart.substring(4, 6), 16));//行走类型
        return gad;
    }

    /**
     * 解析基本的简单单一数据的传感器数据
     *
     * @param dataDataPart
     * @param dataId
     * @return
     */
    public GpsData resorveGpsData(String dataDataPart, int dataId) {
        GpsData gd = new GpsData();
        gd.setDataType(dataId);
        if (dataId == GpsDataTypeItemEnum.WORK_MODE.getId() || dataId == GpsDataTypeItemEnum.MODE_E.getId()) {
            //工作模式\E模式 的值不转成10进制
            gd.setVal(Integer.parseInt(dataDataPart));
        } else {
            int intVal = Integer.parseInt(dataDataPart, 16);
            if ((dataId == 10 || dataId == 11) && intVal > 1200) {
                //电流,值超过1200就是有问题的,处理掉
                intVal = 1200;//暂时写成最大值
            }
            gd.setVal(intVal);
        }
        return gd;
    }

}

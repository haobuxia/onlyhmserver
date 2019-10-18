package com.tianyi.helmet.server.entity.data.gpsenum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * gps数据项定义
 * <p>
 * Created by liuhanc on 2017/10/23.
 */
public enum GpsDataTypeItemEnum implements Comparable<GpsDataTypeItemEnum> {
    KEY_TIME(1, "当天钥匙接通时间S", 5, true,10),//(s)
    WATER_TEMPERATURE(2, "水温℃", 3, true,0.0078125),//(℃)
    OIL_PRESSURE(3, "机油压力", 3, true,1),//机油压力是个开关值没有单位
    FUEL_CONSUMPTION(4, "燃料瞬间消耗L/H", 3, true,0.015625),//(L/H)
    REVOLUTION_SPEED(5, "转速rpm", 5, true,0.125),
    TORQUE_PERCENT(6, "扭矩百分比%", 3, true,0.5),//(%)
    TORQUE(7, "扭矩N.m", 3, true,0.0255),
    PUMP_PRESSURE_F(8, "F泵泵压Kg/cm2", 3, true,2),//(Kg/cm2)
    PUMP_PRESSURE_R(9, "R泵泵压Kg/cm2", 3, true,2),//(Kg/cm2)
    ELECTRIC_CURRENT_F(10, "F泵PC-EPC电流mA", 7, true,1),//(mA)
    ELECTRIC_CURRENT_R(11, "R泵PC-EPC电流mA", 7, true,1),//(mA)
    ACTION(12, "动作", 9, true,1),
    WORK_MODE(13, "工作模式", 2, true,1), //@see WorkModeEnum
    MODE_E(14, "E模式调整", 2, true,1),
    GPS_LOCATION(15, "GPS位置", 11, true,1),
    GYRO(16, "陀螺仪", 8, true,1);

    int id;
    String name;
    int dataLength;
    boolean keyData;
    double ratio;//数据计算结果再乘以的值,从而得到最终值

    public static GpsDataTypeItemEnum get(int id) {
        return Stream.of(GpsDataTypeItemEnum.values()).filter(ee -> ee.id == id).findAny().orElse(null);
    }

    /**
     * 简单值数据
     *
     * @return
     */
    public static List<GpsDataTypeItemEnum> getSimpleDataTypeList() {
        return Arrays.stream(GpsDataTypeItemEnum.values()).filter(gpsDataTypeItemEnum ->
                !gpsDataTypeItemEnum.equals(ACTION) &&
                        !gpsDataTypeItemEnum.equals(GPS_LOCATION) &&
                        !gpsDataTypeItemEnum.equals(GYRO)
        ).collect(Collectors.toList());
    }

    /**
     * 视频对比数据
     *
     * @return
     */
    public static List<GpsDataTypeItemEnum> getCompareDataTypeList(){
        return Arrays.stream(new GpsDataTypeItemEnum[]{REVOLUTION_SPEED,PUMP_PRESSURE_F,ELECTRIC_CURRENT_F,PUMP_PRESSURE_R,ELECTRIC_CURRENT_R,TORQUE,TORQUE_PERCENT,OIL_PRESSURE}).collect(Collectors.toList());
    }


    /**
     * 复杂值数据
     *
     * @return
     */
    public static List<GpsDataTypeItemEnum> getComplexDataTypeList() {
        return Arrays.asList(ACTION,GPS_LOCATION,GYRO);
    }

    /**
     * 关键数据
     *
     * @return
     */
    public static List<GpsDataTypeItemEnum> getKeyDataTypeList() {
        List<GpsDataTypeItemEnum> dataTypeList = Arrays.stream(GpsDataTypeItemEnum.values()).filter(gpsDataTypeItemEnum -> gpsDataTypeItemEnum.isKeyData()).collect(Collectors.toList());
        return dataTypeList;
    }

    /**
     * 判断该类型对应的数据值是否Int类型
     *
     * @param id
     * @return
     */
    public static boolean isDataValueInt(int id) {
        if (ACTION.id == id || GPS_LOCATION.id == id || GYRO.id == id) {
            return false;
        }
        return true;
    }


    private GpsDataTypeItemEnum(int id, String name, int dataLength, boolean keyData,double ratio) {
        this.id = id;
        this.name = name;
        this.dataLength = dataLength;
        this.keyData = keyData;
        this.ratio = ratio;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDataLength() {
        return dataLength;
    }

    public boolean isKeyData() {
        return keyData;
    }

    public double getRatio(){
        return ratio;
    }

}

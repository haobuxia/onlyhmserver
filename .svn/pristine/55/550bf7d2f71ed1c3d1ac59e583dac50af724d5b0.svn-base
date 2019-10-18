package com.tianyi.helmet.server.entity.data.gpsenum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * gps数据项定义
 * <p>
 * Created by tianxujin on 2019/04/25.
 */
public enum SgGpsDataTypeItemEnum implements Comparable<SgGpsDataTypeItemEnum> {
    HORSEPOWER_MODE(1, "马力模式", 4, true,1),//(4bit)
    REQUIRED_HORSEPOWER(2, "要求马力率", 3, true,1),//(%)
    FINAL_REQUIRED_HORSEPOWER(3, "最终要求马力率", 3, true,1),//(%)
    ENGINE_SPEED(4, "发动机转速", 7, true,1),//(rpm)
    TSCI_ENG_MODE(5, "TSCI_ENG选择模式", 3, true,1),//(%)
    DISPLAY_LEVEL(6, "表示层面", 3, true,1),//(byte)
    P_UCODE(7, "P code,U code", 5, true,1),
    REQUIRED_DEVICE(8, "要求装置", 3, true,1),//()
    FAULT_DEGREE(9, "故障程度", 4, true,1),//()
    CONTROL_FLAG(10, "控制标志", 2, true,1),//
    DIESEL_FLOW(11, "柴油流量", 4, true,1),//
    ENGINE_WATER(12, "发动机水温", 3, true,1),//
    MAX_HP(13, "最大马力", 5, true,1);

    int id;
    String name;
    int dataLength;
    boolean keyData;
    double ratio;//数据计算结果再乘以的值,从而得到最终值

    public static SgGpsDataTypeItemEnum get(int id) {
        return Stream.of(SgGpsDataTypeItemEnum.values()).filter(ee -> ee.id == id).findAny().orElse(null);
    }

    /**
     * 简单值数据
     *
     * @return
     */
    public static List<SgGpsDataTypeItemEnum> getSimpleDataTypeList() {
        return Arrays.stream(SgGpsDataTypeItemEnum.values()).collect(Collectors.toList());
    }

    /**
     * 视频对比数据
     *
     * @return
     */
    public static List<SgGpsDataTypeItemEnum> getCompareDataTypeList(){
        return Arrays.stream(new SgGpsDataTypeItemEnum[]{HORSEPOWER_MODE,
                REQUIRED_HORSEPOWER,
                FINAL_REQUIRED_HORSEPOWER,
                ENGINE_SPEED,
                TSCI_ENG_MODE,
                DISPLAY_LEVEL,
                P_UCODE,
                REQUIRED_DEVICE,
                FAULT_DEGREE,
                CONTROL_FLAG,
                DIESEL_FLOW,
                ENGINE_WATER,
                MAX_HP}).collect(Collectors.toList());
    }


    /**
     * 复杂值数据
     *
     * @return
     */
//    public static List<SgGpsDataTypeItemEnum> getComplexDataTypeList() {
//        return Arrays.asList(ACTION,GPS_LOCATION,GYRO);
//    }

    /**
     * 关键数据
     *
     * @return
     */
    public static List<SgGpsDataTypeItemEnum> getKeyDataTypeList() {
        List<SgGpsDataTypeItemEnum> dataTypeList = Arrays.stream(SgGpsDataTypeItemEnum.values()).filter(gpsDataTypeItemEnum -> gpsDataTypeItemEnum.isKeyData()).collect(Collectors.toList());
        return dataTypeList;
    }

    /**
     * 判断该类型对应的数据值是否Int类型
     *
     * @param id
     * @return
     */
    public static boolean isDataValueInt(int id) {
//        if (ACTION.id == id || GPS_LOCATION.id == id || GYRO.id == id) {
//            return false;
//        }
        return true;
    }


    private SgGpsDataTypeItemEnum(int id, String name, int dataLength, boolean keyData, double ratio) {
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

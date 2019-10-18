package com.tianyi.helmet.server.service.data;

/**
 * 100D 盒子数据项定义
 * <p>
 * Created by kangweibo on 2018/6/6.
 */
public enum DataTypeItemEnum implements Comparable<DataTypeItemEnum> {

    WATER_TEMPERATURE_100D("KM_0002_02_01@double", "水温℃", 3, false,0.0078125),//(℃)
    OIL_PRESSURE_100D("KM_0002_02_02@double", "机油压力", 3, true,1),//机油压力是个开关值没有单位
    FUEL_CONSUMPTION_100D("KM_0002_02_03@double", "燃料瞬间消耗L/H", 3, true,0.015625),//(L/H)
    REVOLUTION_SPEED_100D("KM_0002_02_04@double", "转速rpm", 5, true,0.125),
    TORQUE_PERCENT_100D("KM_0002_02_05@double", "扭矩百分比%", 3, true,0.5),//(%)
    TORQUE_100D("KM_0002_02_06@double", "扭矩N.m", 3, true,0.0255),
    PUMP_PRESSURE_F_100D("KM_0002_02_07@double", "F泵泵压Kg/cm2", 3, true,2),//(Kg/cm2)
    PUMP_PRESSURE_R_100D("KM_0002_02_08@double", "R泵泵压Kg/cm2", 3, true,2),//(Kg/cm2)
    ELECTRIC_CURRENT_F_100D("KM_0002_02_09@double", "F泵PC-EPC电流mA", 7, true,1),//(mA)
    ELECTRIC_CURRENT_R_100D("KM_0002_02_10@double", "R泵PC-EPC电流mA", 7, true,1),//(mA)
    ACTION_100D("KM_0002_02_11@int", "动作", 1, false,1),
    ACTION_TYPE_100D("KM_0002_02_12@string", "动作类型", 9, false,1),
    WORK_MODE("KM_0002_02_13@string", "工作模式", 2, true,1), //@see WorkModeEnum
    MODE_E("KM_0002_02_14@string", "E模式调整", 2, true,1);


    String id;
    String name;
    int dataLength;
    boolean keyData;
    double ratio;//数据计算结果再乘以的值,从而得到最终值

    /**
     * 根据id返回实际枚举类型
     * @param id
     * @return
     */
    public static DataTypeItemEnum get(String id) {
        for (DataTypeItemEnum type : DataTypeItemEnum.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    private DataTypeItemEnum(String id, String name, int dataLength, boolean keyData, double ratio) {
        this.id = id;
        this.name = name;
        this.dataLength = dataLength;
        this.keyData = keyData;
        this.ratio = ratio;
    }

    public String getId() {
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

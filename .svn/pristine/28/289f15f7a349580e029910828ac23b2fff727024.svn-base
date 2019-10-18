package com.tianyi.helmet.server.service.data;

/**
 * 600s 盒子数据项定义
 * <p>
 * Created by tianxujin on 2019/4/28
 */
public enum DataTypeItemSgEnum implements Comparable<DataTypeItemSgEnum> {

    HORSEPOWER_MODE("KOB_0002_00_1@int", "马力模式",1),//(℃)
    REQUIRED_HORSEPOWER("KOB_0002_00_2@int", "要求马力率",2),//机油压力是个开关值没有单位
    FINAL_REQUIRED_HORSEPOWER("KOB_0002_00_3@int", "燃料瞬间消耗L/H",3),//(L/H)
    ENGINE_SPEED("KOB_0002_00_4@double", "转速rpm",4),
    TSCI_ENG_MODE("KOB_0002_00_5@int", "扭矩百分比%",5),//(%)
    DISPLAY_LEVEL("KOB_0002_00_6@int", "扭矩N.m",6),
    P_UCODE("KOB_0002_00_7@int", "F泵泵压Kg/cm2",7),//(Kg/cm2)
    REQUIRED_DEVICE("KOB_0002_00_8@int", "R泵泵压Kg/cm2",8),//(Kg/cm2)
    FAULT_DEGREE("KOB_0002_00_9@int", "F泵PC-EPC电流mA",9),//(mA)
    CONTROL_FLAG("KOB_0002_00_10@int", "R泵PC-EPC电流mA",10),//(mA)
    DIESEL_FLOW("KOB_0002_00_11@double", "动作",11),
    ENGINE_WATER("KOB_0002_00_12@int", "动作类型",12),
    MAX_HP("KOB_0002_00_13@int", "工作模式",13);

    String id;
    String name;
    int gpsId;
    /**
     * 根据id返回实际枚举类型
     * @param id
     * @return
     */
    public static DataTypeItemSgEnum get(String id) {
        for (DataTypeItemSgEnum type : DataTypeItemSgEnum.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    private DataTypeItemSgEnum(String id, String name, int gpsId) {
        this.id = id;
        this.name = name;
        this.gpsId = gpsId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGpsId() {
        return gpsId;
    }
}

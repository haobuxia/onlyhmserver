package com.tianyi.helmet.server.service.data;

/**
 * 工况数据
 * Created by kangweibo01 on 2018/8/25.
 */

public class WorkData {

    public static final int WATER_TEMPERATURE       = 2; //水温℃
    public static final int OIL_PRESSURE            = 3; //机油压力,机油压力是个开关值没有单位
    public static final int FUEL_CONSUMPTION        = 4; //燃料瞬间消耗L/H
    public static final int REVOLUTION_SPEED        = 5; //转速rpm
    public static final int TORQUE_PERCENT          = 6; //扭矩百分比%
    public static final int TORQUE                  = 7; //扭矩N.m
    public static final int PUMP_PRESSURE_F         = 8; //F泵泵压Kg/cm2
    public static final int PUMP_PRESSURE_R         = 9; //R泵泵压Kg/cm2
    public static final int ELECTRIC_CURRENT_F      = 10; //F泵PC-EPC电流mA
    public static final int ELECTRIC_CURRENT_R      = 11; //R泵PC-EPC电流mA
    public static final int ACTION                  = 12; //动作
    public static final int WORK_MODE                  = 13; //工作模式
    public static final int MODE_E                  = 14; //E模式

    private int mType; // 工况类型
    private int mValue;// 工况数值
    private String mActionVal;//动作类型

    /**
     * 获取工况类型
     * @return
     */
    public final int getType() {
        return mType;
    }

    /**
     * 获取工况数值
     * @return
     */
    public final int getValue() {
        return mValue;
    }

    /**
     * 设置工况类型
     */
    public final void setType(int type) {
        mType = type;
    }

    /**
     * 设置工况数值
     */
    public final void setValue(int value) {
        mValue = value;
    }

    /**
     * 设置动作类型
     */
    public void setActionVal(String actionVal) {
        mActionVal = actionVal;
    }

    /**
     * 设置动作数值
     */
    public String getActionVal() {
        return mActionVal;
    }
}

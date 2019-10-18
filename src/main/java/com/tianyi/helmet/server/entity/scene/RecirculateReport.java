package com.tianyi.helmet.server.entity.scene;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 循环机报告信息
 * <p>
 * Created by liuhanc on 2018/1/30.
 */
public class RecirculateReport extends IdEntity {
    private String reportNo;
    private String reportDate;
    private String createUser;
    private String machineType;
    private String machineCode;//机号
    private String engine;
    private String brand;
    private String factory;

    private Integer videoId;//视频id

    private int speed_overflow_1;//发动机转速-溢流
    private int speed_overflow_2;
    private int speed_overflow_3;
    private int speed_increase_1;//发动机转速-增强
    private int speed_increase_2;
    private int speed_increase_3;
    private int speed_decrease_1;//发动机转速-降速
    private int speed_decrease_2;
    private int speed_decrease_3;

    private int pressure_arm_1;//泵压油压-动臂
    private int pressure_arm_2;
    private int pressure_arm_3;
    private int pressure_dipper_1;//泵压油压-斗杆
    private int pressure_dipper_2;
    private int pressure_dipper_3;
    private int pressure_bucket_1;//泵压油压-铲斗
    private int pressure_bucket_2;
    private int pressure_bucket_3;

    private int pressure_back_1;//泵压油压-回转
    private int pressure_back_2;
    private int pressure_back_3;
    private int pressure_walk_11;//泵压油压-行走
    private int pressure_walk_12;
    private int pressure_walk_13;
    private int pressure_walk_21;//泵压油压-行走
    private int pressure_walk_22;
    private int pressure_walk_23;

    private int pressure_valve_1;//泵压油压-控制阀门
    private int pressure_valve_2;
    private int pressure_valve_3;
    private int pressure_differ_11;//泵压油压-差压
    private int pressure_differ_12;
    private int pressure_differ_13;
    private int pressure_differ_21;//泵压油压-差压
    private int pressure_differ_22;
    private int pressure_differ_23;

    private int back_degree_1;//回转角度
    private int back_degree_2;
    private int back_degree_3;

    private float back_starttime_11;//回转启动时间
    private float back_starttime_12;
    private float back_starttime_13;
    private float back_starttime_21;//回转启动时间
    private float back_starttime_22;
    private float back_starttime_23;
    private float back_alltime_1;//回转时间
    private float back_alltime_2;
    private float back_alltime_3;

    private float walkspeed_r_11;//行走速度
    private float walkspeed_r_12;
    private float walkspeed_r_13;
    private float walkspeed_l_11;
    private float walkspeed_l_12;
    private float walkspeed_l_13;

    private float walkspeed_r_21;
    private float walkspeed_r_22;
    private float walkspeed_r_23;
    private float walkspeed_l_21;
    private float walkspeed_l_22;
    private float walkspeed_l_23;

    private float walkspeed_r_31;
    private float walkspeed_r_32;
    private float walkspeed_r_33;
    private float walkspeed_l_31;
    private float walkspeed_l_32;
    private float walkspeed_l_33;

    private int walkoff_1;//行走跑偏
    private int walkoff_2;
    private int walkoff_3;

    private int decline_device_1;//下降量-工作装置
    private int decline_device_2;
    private int decline_device_3;

    private int decline_arm_1;//下降量-动臂油缸
    private int decline_arm_2;
    private int decline_arm_3;
    private int decline_dipper_1;//下降量-斗杆油缸
    private int decline_dipper_2;
    private int decline_dipper_3;
    private int decline_bucket_1;//下降量-铲斗油缸
    private int decline_bucket_2;
    private int decline_bucket_3;

    private float speed_arm_11;//工作装置速度-动臂
    private float speed_arm_12;
    private float speed_arm_13;
    private float speed_arm_21;//工作装置速度-动臂
    private float speed_arm_22;
    private float speed_arm_23;

    private float speed_dipper_11;//工作装置速度-斗杆
    private float speed_dipper_12;
    private float speed_dipper_13;
    private float speed_dipper_21;//工作装置速度-斗杆
    private float speed_dipper_22;
    private float speed_dipper_23;

    private float speed_bucket_11;//工作装置速度-铲斗
    private float speed_bucket_12;
    private float speed_bucket_13;
    private float speed_bucket_21;//工作装置速度-铲斗
    private float speed_bucket_22;
    private float speed_bucket_23;

    private float timelag_arm_1;//时滞-动臂
    private float timelag_arm_2;
    private float timelag_arm_3;
    private float timelag_dipper_1;//时滞-斗杆
    private float timelag_dipper_2;
    private float timelag_dipper_3;
    private float timelag_bucket_1;//时滞-铲斗
    private float timelag_bucket_2;
    private float timelag_bucket_3;

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public int getSpeed_overflow_1() {
        return speed_overflow_1;
    }

    public void setSpeed_overflow_1(int speed_overflow_1) {
        this.speed_overflow_1 = speed_overflow_1;
    }

    public int getSpeed_overflow_2() {
        return speed_overflow_2;
    }

    public void setSpeed_overflow_2(int speed_overflow_2) {
        this.speed_overflow_2 = speed_overflow_2;
    }

    public int getSpeed_overflow_3() {
        return speed_overflow_3;
    }

    public void setSpeed_overflow_3(int speed_overflow_3) {
        this.speed_overflow_3 = speed_overflow_3;
    }

    public int getSpeed_increase_1() {
        return speed_increase_1;
    }

    public void setSpeed_increase_1(int speed_increase_1) {
        this.speed_increase_1 = speed_increase_1;
    }

    public int getSpeed_increase_2() {
        return speed_increase_2;
    }

    public void setSpeed_increase_2(int speed_increase_2) {
        this.speed_increase_2 = speed_increase_2;
    }

    public int getSpeed_increase_3() {
        return speed_increase_3;
    }

    public void setSpeed_increase_3(int speed_increase_3) {
        this.speed_increase_3 = speed_increase_3;
    }

    public int getSpeed_decrease_1() {
        return speed_decrease_1;
    }

    public void setSpeed_decrease_1(int speed_decrease_1) {
        this.speed_decrease_1 = speed_decrease_1;
    }

    public int getSpeed_decrease_2() {
        return speed_decrease_2;
    }

    public void setSpeed_decrease_2(int speed_decrease_2) {
        this.speed_decrease_2 = speed_decrease_2;
    }

    public int getSpeed_decrease_3() {
        return speed_decrease_3;
    }

    public void setSpeed_decrease_3(int speed_decrease_3) {
        this.speed_decrease_3 = speed_decrease_3;
    }

    public int getPressure_arm_1() {
        return pressure_arm_1;
    }

    public void setPressure_arm_1(int pressure_arm_1) {
        this.pressure_arm_1 = pressure_arm_1;
    }

    public int getPressure_arm_2() {
        return pressure_arm_2;
    }

    public void setPressure_arm_2(int pressure_arm_2) {
        this.pressure_arm_2 = pressure_arm_2;
    }

    public int getPressure_arm_3() {
        return pressure_arm_3;
    }

    public void setPressure_arm_3(int pressure_arm_3) {
        this.pressure_arm_3 = pressure_arm_3;
    }

    public int getPressure_dipper_1() {
        return pressure_dipper_1;
    }

    public void setPressure_dipper_1(int pressure_dipper_1) {
        this.pressure_dipper_1 = pressure_dipper_1;
    }

    public int getPressure_dipper_2() {
        return pressure_dipper_2;
    }

    public void setPressure_dipper_2(int pressure_dipper_2) {
        this.pressure_dipper_2 = pressure_dipper_2;
    }

    public int getPressure_dipper_3() {
        return pressure_dipper_3;
    }

    public void setPressure_dipper_3(int pressure_dipper_3) {
        this.pressure_dipper_3 = pressure_dipper_3;
    }

    public int getPressure_bucket_1() {
        return pressure_bucket_1;
    }

    public void setPressure_bucket_1(int pressure_bucket_1) {
        this.pressure_bucket_1 = pressure_bucket_1;
    }

    public int getPressure_bucket_2() {
        return pressure_bucket_2;
    }

    public void setPressure_bucket_2(int pressure_bucket_2) {
        this.pressure_bucket_2 = pressure_bucket_2;
    }

    public int getPressure_bucket_3() {
        return pressure_bucket_3;
    }

    public void setPressure_bucket_3(int pressure_bucket_3) {
        this.pressure_bucket_3 = pressure_bucket_3;
    }

    public int getPressure_back_1() {
        return pressure_back_1;
    }

    public void setPressure_back_1(int pressure_back_1) {
        this.pressure_back_1 = pressure_back_1;
    }

    public int getPressure_back_2() {
        return pressure_back_2;
    }

    public void setPressure_back_2(int pressure_back_2) {
        this.pressure_back_2 = pressure_back_2;
    }

    public int getPressure_back_3() {
        return pressure_back_3;
    }

    public void setPressure_back_3(int pressure_back_3) {
        this.pressure_back_3 = pressure_back_3;
    }

    public int getPressure_walk_11() {
        return pressure_walk_11;
    }

    public void setPressure_walk_11(int pressure_walk_11) {
        this.pressure_walk_11 = pressure_walk_11;
    }

    public int getPressure_walk_12() {
        return pressure_walk_12;
    }

    public void setPressure_walk_12(int pressure_walk_12) {
        this.pressure_walk_12 = pressure_walk_12;
    }

    public int getPressure_walk_13() {
        return pressure_walk_13;
    }

    public void setPressure_walk_13(int pressure_walk_13) {
        this.pressure_walk_13 = pressure_walk_13;
    }

    public int getPressure_walk_21() {
        return pressure_walk_21;
    }

    public void setPressure_walk_21(int pressure_walk_21) {
        this.pressure_walk_21 = pressure_walk_21;
    }

    public int getPressure_walk_22() {
        return pressure_walk_22;
    }

    public void setPressure_walk_22(int pressure_walk_22) {
        this.pressure_walk_22 = pressure_walk_22;
    }

    public int getPressure_walk_23() {
        return pressure_walk_23;
    }

    public void setPressure_walk_23(int pressure_walk_23) {
        this.pressure_walk_23 = pressure_walk_23;
    }

    public int getPressure_valve_1() {
        return pressure_valve_1;
    }

    public void setPressure_valve_1(int pressure_valve_1) {
        this.pressure_valve_1 = pressure_valve_1;
    }

    public int getPressure_valve_2() {
        return pressure_valve_2;
    }

    public void setPressure_valve_2(int pressure_valve_2) {
        this.pressure_valve_2 = pressure_valve_2;
    }

    public int getPressure_valve_3() {
        return pressure_valve_3;
    }

    public void setPressure_valve_3(int pressure_valve_3) {
        this.pressure_valve_3 = pressure_valve_3;
    }

    public int getPressure_differ_11() {
        return pressure_differ_11;
    }

    public void setPressure_differ_11(int pressure_differ_11) {
        this.pressure_differ_11 = pressure_differ_11;
    }

    public int getPressure_differ_12() {
        return pressure_differ_12;
    }

    public void setPressure_differ_12(int pressure_differ_12) {
        this.pressure_differ_12 = pressure_differ_12;
    }

    public int getPressure_differ_13() {
        return pressure_differ_13;
    }

    public void setPressure_differ_13(int pressure_differ_13) {
        this.pressure_differ_13 = pressure_differ_13;
    }

    public int getPressure_differ_21() {
        return pressure_differ_21;
    }

    public void setPressure_differ_21(int pressure_differ_21) {
        this.pressure_differ_21 = pressure_differ_21;
    }

    public int getPressure_differ_22() {
        return pressure_differ_22;
    }

    public void setPressure_differ_22(int pressure_differ_22) {
        this.pressure_differ_22 = pressure_differ_22;
    }

    public int getPressure_differ_23() {
        return pressure_differ_23;
    }

    public void setPressure_differ_23(int pressure_differ_23) {
        this.pressure_differ_23 = pressure_differ_23;
    }

    public int getBack_degree_1() {
        return back_degree_1;
    }

    public void setBack_degree_1(int back_degree_1) {
        this.back_degree_1 = back_degree_1;
    }

    public int getBack_degree_2() {
        return back_degree_2;
    }

    public void setBack_degree_2(int back_degree_2) {
        this.back_degree_2 = back_degree_2;
    }

    public int getBack_degree_3() {
        return back_degree_3;
    }

    public void setBack_degree_3(int back_degree_3) {
        this.back_degree_3 = back_degree_3;
    }

    public float getBack_starttime_11() {
        return back_starttime_11;
    }

    public void setBack_starttime_11(float back_starttime_11) {
        this.back_starttime_11 = back_starttime_11;
    }

    public float getBack_starttime_12() {
        return back_starttime_12;
    }

    public void setBack_starttime_12(float back_starttime_12) {
        this.back_starttime_12 = back_starttime_12;
    }

    public float getBack_starttime_13() {
        return back_starttime_13;
    }

    public void setBack_starttime_13(float back_starttime_13) {
        this.back_starttime_13 = back_starttime_13;
    }

    public float getBack_starttime_21() {
        return back_starttime_21;
    }

    public void setBack_starttime_21(float back_starttime_21) {
        this.back_starttime_21 = back_starttime_21;
    }

    public float getBack_starttime_22() {
        return back_starttime_22;
    }

    public void setBack_starttime_22(float back_starttime_22) {
        this.back_starttime_22 = back_starttime_22;
    }

    public float getBack_starttime_23() {
        return back_starttime_23;
    }

    public void setBack_starttime_23(float back_starttime_23) {
        this.back_starttime_23 = back_starttime_23;
    }

    public float getBack_alltime_1() {
        return back_alltime_1;
    }

    public void setBack_alltime_1(float back_alltime_1) {
        this.back_alltime_1 = back_alltime_1;
    }

    public float getBack_alltime_2() {
        return back_alltime_2;
    }

    public void setBack_alltime_2(float back_alltime_2) {
        this.back_alltime_2 = back_alltime_2;
    }

    public float getBack_alltime_3() {
        return back_alltime_3;
    }

    public void setBack_alltime_3(float back_alltime_3) {
        this.back_alltime_3 = back_alltime_3;
    }

    public float getWalkspeed_r_11() {
        return walkspeed_r_11;
    }

    public void setWalkspeed_r_11(float walkspeed_r_11) {
        this.walkspeed_r_11 = walkspeed_r_11;
    }

    public float getWalkspeed_r_12() {
        return walkspeed_r_12;
    }

    public void setWalkspeed_r_12(float walkspeed_r_12) {
        this.walkspeed_r_12 = walkspeed_r_12;
    }

    public float getWalkspeed_r_13() {
        return walkspeed_r_13;
    }

    public void setWalkspeed_r_13(float walkspeed_r_13) {
        this.walkspeed_r_13 = walkspeed_r_13;
    }

    public float getWalkspeed_l_11() {
        return walkspeed_l_11;
    }

    public void setWalkspeed_l_11(float walkspeed_l_11) {
        this.walkspeed_l_11 = walkspeed_l_11;
    }

    public float getWalkspeed_l_12() {
        return walkspeed_l_12;
    }

    public void setWalkspeed_l_12(float walkspeed_l_12) {
        this.walkspeed_l_12 = walkspeed_l_12;
    }

    public float getWalkspeed_l_13() {
        return walkspeed_l_13;
    }

    public void setWalkspeed_l_13(float walkspeed_l_13) {
        this.walkspeed_l_13 = walkspeed_l_13;
    }

    public float getWalkspeed_r_21() {
        return walkspeed_r_21;
    }

    public void setWalkspeed_r_21(float walkspeed_r_21) {
        this.walkspeed_r_21 = walkspeed_r_21;
    }

    public float getWalkspeed_r_22() {
        return walkspeed_r_22;
    }

    public void setWalkspeed_r_22(float walkspeed_r_22) {
        this.walkspeed_r_22 = walkspeed_r_22;
    }

    public float getWalkspeed_r_23() {
        return walkspeed_r_23;
    }

    public void setWalkspeed_r_23(float walkspeed_r_23) {
        this.walkspeed_r_23 = walkspeed_r_23;
    }

    public float getWalkspeed_l_21() {
        return walkspeed_l_21;
    }

    public void setWalkspeed_l_21(float walkspeed_l_21) {
        this.walkspeed_l_21 = walkspeed_l_21;
    }

    public float getWalkspeed_l_22() {
        return walkspeed_l_22;
    }

    public void setWalkspeed_l_22(float walkspeed_l_22) {
        this.walkspeed_l_22 = walkspeed_l_22;
    }

    public float getWalkspeed_l_23() {
        return walkspeed_l_23;
    }

    public void setWalkspeed_l_23(float walkspeed_l_23) {
        this.walkspeed_l_23 = walkspeed_l_23;
    }

    public float getWalkspeed_r_31() {
        return walkspeed_r_31;
    }

    public void setWalkspeed_r_31(float walkspeed_r_31) {
        this.walkspeed_r_31 = walkspeed_r_31;
    }

    public float getWalkspeed_r_32() {
        return walkspeed_r_32;
    }

    public void setWalkspeed_r_32(float walkspeed_r_32) {
        this.walkspeed_r_32 = walkspeed_r_32;
    }

    public float getWalkspeed_r_33() {
        return walkspeed_r_33;
    }

    public void setWalkspeed_r_33(float walkspeed_r_33) {
        this.walkspeed_r_33 = walkspeed_r_33;
    }

    public float getWalkspeed_l_31() {
        return walkspeed_l_31;
    }

    public void setWalkspeed_l_31(float walkspeed_l_31) {
        this.walkspeed_l_31 = walkspeed_l_31;
    }

    public float getWalkspeed_l_32() {
        return walkspeed_l_32;
    }

    public void setWalkspeed_l_32(float walkspeed_l_32) {
        this.walkspeed_l_32 = walkspeed_l_32;
    }

    public float getWalkspeed_l_33() {
        return walkspeed_l_33;
    }

    public void setWalkspeed_l_33(float walkspeed_l_33) {
        this.walkspeed_l_33 = walkspeed_l_33;
    }

    public int getWalkoff_1() {
        return walkoff_1;
    }

    public void setWalkoff_1(int walkoff_1) {
        this.walkoff_1 = walkoff_1;
    }

    public int getWalkoff_2() {
        return walkoff_2;
    }

    public void setWalkoff_2(int walkoff_2) {
        this.walkoff_2 = walkoff_2;
    }

    public int getWalkoff_3() {
        return walkoff_3;
    }

    public void setWalkoff_3(int walkoff_3) {
        this.walkoff_3 = walkoff_3;
    }

    public int getDecline_device_1() {
        return decline_device_1;
    }

    public void setDecline_device_1(int decline_device_1) {
        this.decline_device_1 = decline_device_1;
    }

    public int getDecline_device_2() {
        return decline_device_2;
    }

    public void setDecline_device_2(int decline_device_2) {
        this.decline_device_2 = decline_device_2;
    }

    public int getDecline_device_3() {
        return decline_device_3;
    }

    public void setDecline_device_3(int decline_device_3) {
        this.decline_device_3 = decline_device_3;
    }

    public int getDecline_arm_1() {
        return decline_arm_1;
    }

    public void setDecline_arm_1(int decline_arm_1) {
        this.decline_arm_1 = decline_arm_1;
    }

    public int getDecline_arm_2() {
        return decline_arm_2;
    }

    public void setDecline_arm_2(int decline_arm_2) {
        this.decline_arm_2 = decline_arm_2;
    }

    public int getDecline_arm_3() {
        return decline_arm_3;
    }

    public void setDecline_arm_3(int decline_arm_3) {
        this.decline_arm_3 = decline_arm_3;
    }

    public int getDecline_dipper_1() {
        return decline_dipper_1;
    }

    public void setDecline_dipper_1(int decline_dipper_1) {
        this.decline_dipper_1 = decline_dipper_1;
    }

    public int getDecline_dipper_2() {
        return decline_dipper_2;
    }

    public void setDecline_dipper_2(int decline_dipper_2) {
        this.decline_dipper_2 = decline_dipper_2;
    }

    public int getDecline_dipper_3() {
        return decline_dipper_3;
    }

    public void setDecline_dipper_3(int decline_dipper_3) {
        this.decline_dipper_3 = decline_dipper_3;
    }

    public int getDecline_bucket_1() {
        return decline_bucket_1;
    }

    public void setDecline_bucket_1(int decline_bucket_1) {
        this.decline_bucket_1 = decline_bucket_1;
    }

    public int getDecline_bucket_2() {
        return decline_bucket_2;
    }

    public void setDecline_bucket_2(int decline_bucket_2) {
        this.decline_bucket_2 = decline_bucket_2;
    }

    public int getDecline_bucket_3() {
        return decline_bucket_3;
    }

    public void setDecline_bucket_3(int decline_bucket_3) {
        this.decline_bucket_3 = decline_bucket_3;
    }

    public float getSpeed_arm_11() {
        return speed_arm_11;
    }

    public void setSpeed_arm_11(float speed_arm_11) {
        this.speed_arm_11 = speed_arm_11;
    }

    public float getSpeed_arm_12() {
        return speed_arm_12;
    }

    public void setSpeed_arm_12(float speed_arm_12) {
        this.speed_arm_12 = speed_arm_12;
    }

    public float getSpeed_arm_13() {
        return speed_arm_13;
    }

    public void setSpeed_arm_13(float speed_arm_13) {
        this.speed_arm_13 = speed_arm_13;
    }

    public float getSpeed_arm_21() {
        return speed_arm_21;
    }

    public void setSpeed_arm_21(float speed_arm_21) {
        this.speed_arm_21 = speed_arm_21;
    }

    public float getSpeed_arm_22() {
        return speed_arm_22;
    }

    public void setSpeed_arm_22(float speed_arm_22) {
        this.speed_arm_22 = speed_arm_22;
    }

    public float getSpeed_arm_23() {
        return speed_arm_23;
    }

    public void setSpeed_arm_23(float speed_arm_23) {
        this.speed_arm_23 = speed_arm_23;
    }

    public float getSpeed_dipper_11() {
        return speed_dipper_11;
    }

    public void setSpeed_dipper_11(float speed_dipper_11) {
        this.speed_dipper_11 = speed_dipper_11;
    }

    public float getSpeed_dipper_12() {
        return speed_dipper_12;
    }

    public void setSpeed_dipper_12(float speed_dipper_12) {
        this.speed_dipper_12 = speed_dipper_12;
    }

    public float getSpeed_dipper_13() {
        return speed_dipper_13;
    }

    public void setSpeed_dipper_13(float speed_dipper_13) {
        this.speed_dipper_13 = speed_dipper_13;
    }

    public float getSpeed_dipper_21() {
        return speed_dipper_21;
    }

    public void setSpeed_dipper_21(float speed_dipper_21) {
        this.speed_dipper_21 = speed_dipper_21;
    }

    public float getSpeed_dipper_22() {
        return speed_dipper_22;
    }

    public void setSpeed_dipper_22(float speed_dipper_22) {
        this.speed_dipper_22 = speed_dipper_22;
    }

    public float getSpeed_dipper_23() {
        return speed_dipper_23;
    }

    public void setSpeed_dipper_23(float speed_dipper_23) {
        this.speed_dipper_23 = speed_dipper_23;
    }

    public float getSpeed_bucket_11() {
        return speed_bucket_11;
    }

    public void setSpeed_bucket_11(float speed_bucket_11) {
        this.speed_bucket_11 = speed_bucket_11;
    }

    public float getSpeed_bucket_12() {
        return speed_bucket_12;
    }

    public void setSpeed_bucket_12(float speed_bucket_12) {
        this.speed_bucket_12 = speed_bucket_12;
    }

    public float getSpeed_bucket_13() {
        return speed_bucket_13;
    }

    public void setSpeed_bucket_13(float speed_bucket_13) {
        this.speed_bucket_13 = speed_bucket_13;
    }

    public float getSpeed_bucket_21() {
        return speed_bucket_21;
    }

    public void setSpeed_bucket_21(float speed_bucket_21) {
        this.speed_bucket_21 = speed_bucket_21;
    }

    public float getSpeed_bucket_22() {
        return speed_bucket_22;
    }

    public void setSpeed_bucket_22(float speed_bucket_22) {
        this.speed_bucket_22 = speed_bucket_22;
    }

    public float getSpeed_bucket_23() {
        return speed_bucket_23;
    }

    public void setSpeed_bucket_23(float speed_bucket_23) {
        this.speed_bucket_23 = speed_bucket_23;
    }

    public float getTimelag_arm_1() {
        return timelag_arm_1;
    }

    public void setTimelag_arm_1(float timelag_arm_1) {
        this.timelag_arm_1 = timelag_arm_1;
    }

    public float getTimelag_arm_2() {
        return timelag_arm_2;
    }

    public void setTimelag_arm_2(float timelag_arm_2) {
        this.timelag_arm_2 = timelag_arm_2;
    }

    public float getTimelag_arm_3() {
        return timelag_arm_3;
    }

    public void setTimelag_arm_3(float timelag_arm_3) {
        this.timelag_arm_3 = timelag_arm_3;
    }

    public float getTimelag_dipper_1() {
        return timelag_dipper_1;
    }

    public void setTimelag_dipper_1(float timelag_dipper_1) {
        this.timelag_dipper_1 = timelag_dipper_1;
    }

    public float getTimelag_dipper_2() {
        return timelag_dipper_2;
    }

    public void setTimelag_dipper_2(float timelag_dipper_2) {
        this.timelag_dipper_2 = timelag_dipper_2;
    }

    public float getTimelag_dipper_3() {
        return timelag_dipper_3;
    }

    public void setTimelag_dipper_3(float timelag_dipper_3) {
        this.timelag_dipper_3 = timelag_dipper_3;
    }

    public float getTimelag_bucket_1() {
        return timelag_bucket_1;
    }

    public void setTimelag_bucket_1(float timelag_bucket_1) {
        this.timelag_bucket_1 = timelag_bucket_1;
    }

    public float getTimelag_bucket_2() {
        return timelag_bucket_2;
    }

    public void setTimelag_bucket_2(float timelag_bucket_2) {
        this.timelag_bucket_2 = timelag_bucket_2;
    }

    public float getTimelag_bucket_3() {
        return timelag_bucket_3;
    }

    public void setTimelag_bucket_3(float timelag_bucket_3) {
        this.timelag_bucket_3 = timelag_bucket_3;
    }
}

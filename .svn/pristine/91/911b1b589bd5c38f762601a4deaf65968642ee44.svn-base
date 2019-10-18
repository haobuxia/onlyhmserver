package com.tianyi.helmet.server.entity.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsCatagoryEnum;

/**
 * 车载gps终端陀螺仪IMU数据
 * <p>
 * Created by liuhanc on 2017/10/23.
 */
public class GpsGyroData extends AbstractGpsData {
    private int frontBack;//前后姿态角 deg	-180 - +180
    private int leftRight;//左右姿态角 deg	-180 - +180
    private int rotate;//旋转角度 deg	-32767 - +32768
    private int rotateMaxSpeed;//旋转最大角速度 dps	-150 - +150
    private int rotateAvgSpeed;//旋转平均角速度 dps	-150 - +150
    private long downAcceleration;//减速时角加速度 deg/s^2
    private long upAcceleration;//加速时角加速度 deg/s^2
    private int backTime;//回转时间 秒 0-60

    public GpsCatagoryEnum getGpsCatagoryEnum() {
        return GpsCatagoryEnum.GYRO;
    }

    public int getFrontBack() {
        return frontBack;
    }

    public void setFrontBack(int frontBack) {
        this.frontBack = frontBack;
    }

    public int getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(int leftRight) {
        this.leftRight = leftRight;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getRotateMaxSpeed() {
        return rotateMaxSpeed;
    }

    public void setRotateMaxSpeed(int rotateMaxSpeed) {
        this.rotateMaxSpeed = rotateMaxSpeed;
    }

    public int getRotateAvgSpeed() {
        return rotateAvgSpeed;
    }

    public void setRotateAvgSpeed(int rotateAvgSpeed) {
        this.rotateAvgSpeed = rotateAvgSpeed;
    }

    public long getDownAcceleration() {
        return downAcceleration;
    }

    public void setDownAcceleration(long downAcceleration) {
        this.downAcceleration = downAcceleration;
    }

    public long getUpAcceleration() {
        return upAcceleration;
    }

    public void setUpAcceleration(long upAcceleration) {
        this.upAcceleration = upAcceleration;
    }

    public int getBackTime() {
        return backTime;
    }

    public void setBackTime(int backTime) {
        this.backTime = backTime;
    }

    public String dataJson() {
        return "{frontBack:" + frontBack + ",leftRight:" + leftRight + ",rotate:" + rotate + ",rotateMaxSpeed:" + rotateMaxSpeed + ",rotateAvgSpeed:" + rotateAvgSpeed + ",downAcceleration:" + downAcceleration + ",upAcceleration:" + upAcceleration + ",backTime:" + backTime + "}";
    }

    public static GpsGyroData jsonToData(String dataJson) {
        GpsGyroData data = new GpsGyroData();
        JSONObject jo = JSON.parseObject(dataJson);
        data.setBackTime(jo.getInteger("backTime"));
        data.setUpAcceleration(jo.getLongValue("upAcceleration"));
        data.setDownAcceleration(jo.getLongValue("downAcceleration"));
        data.setRotateAvgSpeed(jo.getIntValue("rotateAvgSpeed"));
        data.setRotateMaxSpeed(jo.getIntValue("rotateMaxSpeed"));
        data.setRotate(jo.getIntValue("rotate"));
        data.setLeftRight(jo.getIntValue("leftRight"));
        data.setFrontBack(jo.getIntValue("frontBack"));
        return data;
    }

    public String toCnString() {
        //@todo 暂时只需要旋转角度数据
        return "旋转角度"+rotate;
    }

    @Override
    public String toString() {
        return "陀螺仪数据：" + super.toString() + ".前后姿态角=" + frontBack + ",左右姿态角=" + leftRight + ",旋转角度=" + rotate + ",旋转最大角速度=" + rotateMaxSpeed + ",旋转平均角速度=" + rotateAvgSpeed + ",减速时角加速度=" + downAcceleration + ",加速时角加速度=" + upAcceleration + ",回转时间=" + backTime;
    }
}

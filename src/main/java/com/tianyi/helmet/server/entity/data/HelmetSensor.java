package com.tianyi.helmet.server.entity.data;

import com.tianyi.helmet.server.util.Dates;

/**
 * 头盔传感器数据报文
 * <p>
 * Created by liuhanc on 2017/10/9.
 */
public class HelmetSensor extends HelmetData {
    private Integer concert;//专注度
    private Integer relax;//放松度
    private Float xa;//角度
    private Float ya;//
    private Float za;//
    private Float xg;//加速度
    private Float yg;
    private Float zg;
    private Integer battery;//电池电量
    private Integer runtime;//本次启动后运行时间

    public Float getXa() {
        return xa;
    }

    public void setXa(Float xa) {
        this.xa = xa;
    }

    public Float getYa() {
        return ya;
    }

    public void setYa(Float ya) {
        this.ya = ya;
    }

    public Float getZa() {
        return za;
    }

    public void setZa(Float za) {
        this.za = za;
    }

    public Float getXg() {
        return xg;
    }

    public void setXg(Float xg) {
        this.xg = xg;
    }

    public Float getYg() {
        return yg;
    }

    public void setYg(Float yg) {
        this.yg = yg;
    }

    public Float getZg() {
        return zg;
    }

    public void setZg(Float zg) {
        this.zg = zg;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getFormatedRuntime() {
        return runtime == null ? "0" : Dates.formatSeconds(runtime.intValue());
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public int getConcert() {
        return concert == null ? 0 : concert.intValue();
    }

    public void setConcert(int concert) {
        this.concert = concert;
    }

    public int getRelax() {
        return relax == null ? 0 : relax.intValue() ;
    }

    public void setRelax(int relax) {
        this.relax = relax;
    }

}

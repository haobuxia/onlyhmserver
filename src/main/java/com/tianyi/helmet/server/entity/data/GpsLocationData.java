package com.tianyi.helmet.server.entity.data;

import com.tianyi.helmet.server.entity.data.gpsenum.GpsCatagoryEnum;

import java.time.LocalDateTime;

/**
 * 车载gps终端定位数据
 *
 * Created by liuhanc on 2017/10/23.
 */
public class GpsLocationData extends AbstractGpsData{
    private int latns;//纬度南北 0-北纬，1-南纬
    private float lat;//纬度 0-54000000,转换成aaa.bbbbb的格式存储
    private int lonew;//经度东西 0-东经，1-西经
    private float lon;//经度 0-10800000 转换成aaa.bbbbb的格式存储
    private int speed;//速度 km/h  0-999
    private int orient;//方向度数  0-359
    private int oldnew;//位置新旧值指示 0-旧值，1-新值
    private int altPosNeg;//海拔正负数指示 0-正值，1-负值
    private int alt;//海拔 米 0-9999
    private int star;//可视星数 0-12
    private LocalDateTime gpsTime;//gps时间

    public GpsCatagoryEnum getGpsCatagoryEnum(){
        return GpsCatagoryEnum.LOCAT;
    }

    public int getLatns() {
        return latns;
    }

    public void setLatns(int latns) {
        this.latns = latns;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getLonew() {
        return lonew;
    }

    public void setLonew(int lonew) {
        this.lonew = lonew;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getOldnew() {
        return oldnew;
    }

    public void setOldnew(int oldnew) {
        this.oldnew = oldnew;
    }

    public int getAltPosNeg() {
        return altPosNeg;
    }

    public void setAltPosNeg(int altPosNeg) {
        this.altPosNeg = altPosNeg;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public LocalDateTime getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(LocalDateTime gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String dataJson(){
        return "{latns:"+latns+",lat:"+lat+",lonew:"+lonew+",lon:"+lon+",speed:"+speed+",orient:"+orient+",oldnew:"+oldnew+",altPosNeg:"+altPosNeg+",alt:"+alt+",star:"+star+",gpsTime:\""+gpsTime.toString()+"\"}";
    }

    @Override
    public String toString(){
        return "定位数据："+super.toString()+".纬度南北="+latns+",纬度="+lat+",经度东西="+lonew+",经度="+lon+",速度="+speed+",方向度数="+orient+",位置新旧值指示="+oldnew+",海拔正负数指示="+altPosNeg+",海拔="+alt+",可视星数="+star+",gps时间="+gpsTime;
    }
}

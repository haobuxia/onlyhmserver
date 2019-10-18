package com.tianyi.helmet.server.entity.data;

/**
 * 头盔传感器定位数据报文
 * <p>
 * Created by liuhanc on 2017/10/9.
 */
public class HelmetGps extends HelmetData {
    private Integer gpsType;// 1gps定位,2网络定位
    private Float lat;//经纬度
    private Float lon;

    public HelmetGps() {
    }

    public HelmetGps(float lon, float lat, int type) {
        this.lon = lon;
        this.lat = lat;
        this.gpsType = type;
    }

    public Float[] getLonLat() {
        return new Float[]{lon, lat};
    }

    public void setLonLat(Float[] lonLat) {
        setLon(lonLat[0]);
        setLat(lonLat[1]);
    }

    public Integer getGpsType() {
        return gpsType;
    }

    public void setGpsType(Integer gpsType) {
        this.gpsType = gpsType;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

}

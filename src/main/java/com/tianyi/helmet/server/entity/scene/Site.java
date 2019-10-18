package com.tianyi.helmet.server.entity.scene;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 工地
 *
 * Created by liuhanc on 2018/1/16.
 */
public class Site extends IdEntity {
    private String name;
    private float lon;//经度
    private float lat;//纬度
    private float area;//面积
    private String lonLats;//区域边框定位
    private String address;//地址
    private String province;
    private String city;
    private String district;
    private String township;
    private String street;
    private LocalDateTime createTime;
    private String siteImageLonLats;//工地区域内的图像定位
    private String siteVideoLonLats;//工地区域内的视频定位

    public String getSiteImageLonLats() {
        return siteImageLonLats;
    }

    public void setSiteImageLonLats(String siteImageLonLats) {
        this.siteImageLonLats = siteImageLonLats;
    }

    public String getSiteVideoLonLats() {
        return siteVideoLonLats;
    }

    public void setSiteVideoLonLats(String siteVideoLonLats) {
        this.siteVideoLonLats = siteVideoLonLats;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getLonLats() {
        return lonLats;
    }

    public void setLonLats(String lonLats) {
        this.lonLats = lonLats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString(){
        return name+":"+address;
    }
}

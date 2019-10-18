package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 单位信息
 * Created by wenxinyan on 2018/9/30.
 */
public class Company extends IdEntity {
    private LocalDateTime createTime;
    private String companyName;
    private String address;
    private int companyNature;
    private int province;
    private int city;
    private String contact;
    private String contactNumber;
    private int status;
    private String displayNature;
    private String displayProvince;
    private String displayCity;

    public String getDisplayNature() {
        return displayNature;
    }

    public void setDisplayNature(String displayNature) {
        this.displayNature = displayNature;
    }

    public String getDisplayProvince() {
        return displayProvince;
    }

    public void setDisplayProvince(String displayProvince) {
        this.displayProvince = displayProvince;
    }

    public String getDisplayCity() {
        return displayCity;
    }

    public void setDisplayCity(String displayCity) {
        this.displayCity = displayCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(int companyNature) {
        this.companyNature = companyNature;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

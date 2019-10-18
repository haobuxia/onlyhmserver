package com.tianyi.helmet.server.entity.client;

import java.util.Map;

/**
 * 登录用户信息
 * Created by liuhanc on 2017/11/22.
 */
public class LoginUserInfo {
    private String token;//存于客户端cookie里的标志用户的唯一字符串
    private int id;//用户记录的id
    private String yunToken;//网易账号体系的token
    private String username;
    private String userType = "tianyi";//用户类型,默认田一用户,或者是"tianyuan"表示天远用户
    private long loginTime;
    private long lastOperateTime;
    private String clientIp;
    private Map<String, Boolean> clientTypeMap;
    private boolean admin;
    private boolean sales;
    private boolean customer;
    private boolean driver;
    private boolean demo;//是否demo演示角色
    private boolean tianyuan;//是否天远账号
    private int organisation;

    public int getOrganisation() {
        return organisation;
    }

    public void setOrganisation(int organisation) {
        this.organisation = organisation;
    }

    public LoginUserInfo() {
    }

    public boolean isTianyuan() {
        return tianyuan;
    }

    public void setTianyuan(boolean tianyuan) {
        this.tianyuan = tianyuan;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isSales() {
        return sales;
    }

    public void setSales(boolean sales) {
        this.sales = sales;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public String getYunToken() {
        return yunToken;
    }

    public void setYunToken(String yunToken) {
        this.yunToken = yunToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Map<String, Boolean> getClientTypeMap() {
        return clientTypeMap;
    }

    public void setClientTypeMap(Map<String, Boolean> clientTypeMap) {
        this.clientTypeMap = clientTypeMap;
    }

    public long getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(long lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

//package com.tianyi.helmet.server.entity.client;
//
//import com.tianyi.helmet.server.entity.IdEntity;
//import com.tianyi.helmet.server.util.Dates;
//import org.apache.commons.lang3.StringUtils;
//
//import java.time.LocalDateTime;
//
///**
// * 头盔信息
// * <p>
// * Created by liuhanc on 2017/10/9.
// */
//public class Helmet extends IdEntity {
//    private String imei;//头盔imei
//    private Integer neUserId;//绑定的网易用户id
//    private String neUsername;//绑定的网易用户名
//    private Integer tianYuanUserId;//绑定的天远用户Id,1个头盔最多绑定1个天远用户
//    private Integer tianyiUserId;//绑定的田一用户Id.1个头盔最多绑定1个田一用户，1个田一用户可以有多个头盔？
//    private String model;//型号
//    private String batch;//批次
//    private int saleState = 0;//0待设置,1已售,2内测,3待发售
//    private int customerId;//客户id
//    private int salerId;//销售员id
//    private LocalDateTime activeTime;//设备激活时间,初始化账号时间
//    private LocalDateTime factoryTime;//设备出厂时间,登记对应客户的时间
//
//    private String tianyiUsername;//田一账号名称.不存储入库
//    private String tianYuanUsername;//天远账号名称.不存储入库
//    private String customerName;//客户名称.不存储入库
//
//
//    public String getDisplayName(){
//        return imei+" "+ (StringUtils.isEmpty(customerName) ? "":customerName) + " "+ (StringUtils.isEmpty(neUsername) ?"":neUsername) ;
//    }
//
//    public Integer getTianyiUserId() {
//        return tianyiUserId;
//    }
//
//    public void setTianyiUserId(Integer tianyiUserId) {
//        this.tianyiUserId = tianyiUserId;
//    }
//
//    public String getTianyiUsername() {
//        return tianyiUsername;
//    }
//
//    public void setTianyiUsername(String tianyiUsername) {
//        this.tianyiUsername = tianyiUsername;
//    }
//
//    public String getNeUsername() {
//        return neUsername;
//    }
//
//    public void setNeUsername(String neUsername) {
//        this.neUsername = neUsername;
//    }
//
//    public int getSalerId() {
//        return salerId;
//    }
//
//    public void setSalerId(int salerId) {
//        this.salerId = salerId;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getFactoryTimeStr() {
//        return factoryTime == null ? "" : Dates.format(Dates.toDate(factoryTime), "yyyy-MM-dd HH:mm:ss");
//    }
//
//    public String getActiveTimeStr() {
//        return activeTime == null ? "" : Dates.format(Dates.toDate(activeTime), "yyyy-MM-dd HH:mm:ss");
//    }
//
//    public String getSaleStateStr() {
//        return saleState == 1 ? "已发售" : (saleState == 0 ? "待初始设置" : (saleState == 2 ? "正在内测":"待出厂发售"));
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getBatch() {
//        return batch;
//    }
//
//    public void setBatch(String batch) {
//        this.batch = batch;
//    }
//
//    public int getSaleState() {
//        return saleState;
//    }
//
//    /**
//     *
//     * @param saleState 0待设置,1已售,2内测,3待发售
//     */
//    public void setSaleState(int saleState) {
//        this.saleState = saleState;
//    }
//
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public Integer getNeUserId() {
//        return neUserId;
//    }
//
//    public void setNeUserId(Integer neUserId) {
//        this.neUserId = neUserId;
//    }
//
//    public String getImei() {
//        return imei;
//    }
//
//    public void setImei(String imei) {
//        this.imei = imei;
//    }
//
//    public LocalDateTime getFactoryTime() {
//        return factoryTime;
//    }
//
//    public void setFactoryTime(LocalDateTime factoryTime) {
//        this.factoryTime = factoryTime;
//    }
//
//    public LocalDateTime getActiveTime() {
//        return activeTime;
//    }
//
//    public void setActiveTime(LocalDateTime activeTime) {
//        this.activeTime = activeTime;
//    }
//
//    public Integer getTianYuanUserId() {
//        return tianYuanUserId;
//    }
//
//    public void setTianYuanUserId(Integer tianYuanUserId) {
//        this.tianYuanUserId = tianYuanUserId;
//    }
//
//    public String getTianYuanUsername() {
//        return tianYuanUsername;
//    }
//
//    public void setTianYuanUsername(String tianYuanUsername) {
//        this.tianYuanUsername = tianYuanUsername;
//    }
//}

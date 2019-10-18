//package com.tianyi.helmet.server.entity.client;
//
//import com.tianyi.helmet.server.entity.device.EquipmentLedger;
//
//import java.time.LocalDateTime;
//
///**
// * 田一后台系统用户：app和网页用户信息，此类用户可以登录网页和手机版app，但是不能登录网易和头盔app
// *
// * Created by liuhanc on 2017/11/29.
// */
//public class TianyiUser extends AbstractUserInfo {
//    private String mobile;//手机号
//    private String name;//姓名
//    private int userSex = 2 ; //1 男 0 女 2 未知
//    private String company;//公司
//    private String dept;//部门
//    private String wxId;
//    private LocalDateTime bindWxTime;
//    private String neUsername;//对应的网易用户账号id
//    private String roleCodes;//角色代码集合,不入库.对应TianyiUserRole
//
//    private EquipmentLedger helmet;//指当前用户佩戴的头盔.根据Helmet.tianyiUserId来确定，1个用户正在佩戴中的头盔数量最多1个
//
//    public EquipmentLedger getHelmet() {
//        return helmet;
//    }
//
//    public void setHelmet(EquipmentLedger helmet) {
//        this.helmet = helmet;
//    }
//
//    @Override
//    public String getUserStrType(){
//        return "tianyi";
//    }
//
//    public String getDept() {
//        return dept;
//    }
//
//    public void setDept(String dept) {
//        this.dept = dept;
//    }
//
//    public String getRoleCodes() {
//        return roleCodes;
//    }
//
//    public void setRoleCodes(String roleCodes) {
//        this.roleCodes = roleCodes;
//    }
//
//    public String getUserSexStr(){
//        return userSex == 1 ? "男":( userSex == 0 ? "女" : "未知" );
//    }
//
//    public String getDisplayName(){
//        return this.getName()+" "+this.getUsername();
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getUserSex() {
//        return userSex;
//    }
//
//    public void setUserSex(int userSex) {
//        this.userSex = userSex;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }
//
//    public String getWxId() {
//        return wxId;
//    }
//
//    public void setWxId(String wxId) {
//        this.wxId = wxId;
//    }
//
//    public LocalDateTime getBindWxTime() {
//        return bindWxTime;
//    }
//
//    public void setBindWxTime(LocalDateTime bindWxTime) {
//        this.bindWxTime = bindWxTime;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//}

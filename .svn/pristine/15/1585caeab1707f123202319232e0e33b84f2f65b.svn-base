package com.tianyi.helmet.server.entity.client;


/**
 * 网易用户信息
 * <p>
 * 客户用户需注册网易用户且自动添加自己购买的头盔用户为好友
 * 管理员需注册网易用户且自动添加所有头盔用户为好友
 * 头盔用户属于网易用户
 * <p>
 * Created by liuhanc on 2017/11/2.
 */
public class NeteaseUser extends AbstractUserInfo {
    private int userType = 1;//1头盔app用户;2 web/手机app用户 3管理员 4未设置用户类型
    private String nickName;//头盔昵称,方便头盔的主人设置自定义名称
    private String yunId;//在网易体系中的用户id，可能和username同样。username是在田一体系登录时的用户名
    private String yunToken;//在网易体系中的密码
    private String company; //音视频账号所属公司
    /**
     * update by xiayuan 2018/10/10
     */
    private int userId;//userId

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserTypeStr() {
        return userType == 1 ? "头盔app" : (userType == 2 ? "常规用户" : "系统管理员");
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserType() {
        return userType;
    }

    @Override
    public String getUserStrType(){
        return "netease";
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getYunId() {
        return yunId;
    }

    public void setYunId(String yunId) {
        this.yunId = yunId;
    }

    public String getYunToken() {
        return yunToken;
    }

    public void setYunToken(String yunToken) {
        this.yunToken = yunToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

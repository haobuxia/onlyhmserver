package com.tianyi.helmet.server.entity.client;

import java.time.LocalDateTime;

/**
 * 天远统一登录体系用户
 * <p>
 * Created by liuhanc on 2018/2/28.
 */
public class TianYuanUser extends AbstractUserInfo {
    private String loginType = "1";//1=默认，2=手机号登录
    private String accessToken;//长度近1000个字符
    private String refreshToken;//41aa1156fad9a315e1e7f5c161e71b9d64d6edf07dfc4a9ddf07762ded2a78dd
    private LocalDateTime refreshTime;//最后1次刷新token的时间
    private String tokenType;//Bearer
    private long expiresIn = -1l;//2592000
    private String accountId;//账号id
    private String oprtId;//人员id
    private String oprtName;//人员姓名

    private static final transient String SPECIAL_UESR_NAME_PREFIX = "prod#";

    public static boolean isProdUser(String userName) {
        return userName != null && userName.startsWith(SPECIAL_UESR_NAME_PREFIX);
    }

    public static String getLoginName(String userName) {
        return isProdUser(userName) ? userName.substring(SPECIAL_UESR_NAME_PREFIX.length()) : userName;
    }

    public static String toUserName(String loginName) {
        return SPECIAL_UESR_NAME_PREFIX + loginName;
    }

    public boolean isProdUser() {
        return isProdUser(getUsername());
    }

    public String getDisplayName(){
        return this.getOprtName()+" "+this.getUsername();
    }

    @Override
    public String getUserStrType() {
        return "tianyuan";
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOprtId() {
        return oprtId;
    }

    public void setOprtId(String oprtId) {
        this.oprtId = oprtId;
    }

    public String getOprtName() {
        return oprtName;
    }

    public void setOprtName(String oprtName) {
        this.oprtName = oprtName;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public LocalDateTime getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(LocalDateTime refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getFullToken() {
        return tokenType + " " + accessToken;
    }
}

package com.tianyi.helmet.server.entity.client;

/**
 * 用户角色枚举
 *
 * Created by liuhanc on 2017/11/29.
 */
public enum RoleEnum {
    ADMIN("admin","管理员"),//可以查看一切数据,属于网易用户
    SALES("sales","销售员"),//可以查看头盔销售的客户
    CUSTOMER("customer","客户"),//可以查看自己的头盔状态、佩戴的机手、录制的资源,属于网易用户
    DRIVER("driver","机手"),//可以查看自己录制的资源
    DEMO("demo","演示账号");//演示账号

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private RoleEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}

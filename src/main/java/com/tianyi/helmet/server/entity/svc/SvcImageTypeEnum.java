package com.tianyi.helmet.server.entity.svc;

/**
 * 服务日志工单图片类型枚举
 *
 * Created by liuhanc on 2018/4/13.
 */
public enum SvcImageTypeEnum {
    jihao("机号");

    private String cnName;

    private SvcImageTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }
}

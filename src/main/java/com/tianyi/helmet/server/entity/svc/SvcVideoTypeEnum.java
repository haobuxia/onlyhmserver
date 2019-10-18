package com.tianyi.helmet.server.entity.svc;

/**
 *  服务日志视频类型枚举
 *
 * Created by liuhanc on 2018/4/13.
 */
public enum SvcVideoTypeEnum {
    site("工地"), digger("挖机"), faultCheck("故障检测"), faultRepaird("故障修复");

    private String cnName;

    private SvcVideoTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }
}

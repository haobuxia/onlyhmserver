package com.tianyi.helmet.server.entity.svc;

/**
 *  服务日志资源类型枚举
 *
 * Created by liuhanc on 2018/4/13.
 */
public enum SvcResTypeEnum {
    image("照片"), video("视频"), audio("录音");

    private String cnName;

    private SvcResTypeEnum(String cnName) {
        this.cnName = cnName;
    }


    public String getCnName() {
        return cnName;
    }
}

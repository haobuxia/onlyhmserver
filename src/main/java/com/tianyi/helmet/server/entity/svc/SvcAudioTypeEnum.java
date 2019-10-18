package com.tianyi.helmet.server.entity.svc;

/** 服务日志音频类型枚举
 *
 * Created by liuhanc on 2018/4/13.
 */
public enum SvcAudioTypeEnum {
    faultContent("故障内容"), notHandleReason("故障未处理原因"), userOpinion("用户意见建议");

    private String cnName;

    private SvcAudioTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }
}

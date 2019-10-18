package com.tianyi.helmet.server.entity.scene.svc;

import org.springframework.util.StringUtils;

/**
 * 工单状态枚举
 * Created by liuhanc on 2018/6/30.
 */
public enum WorkOrderStateEnum {
    INIT("未开始"),
    ING("进行中"),
    END("已结束");

    private String cnName;

    public String getCnName() {
        return cnName;
    }

    private WorkOrderStateEnum(String cnName) {
        this.cnName = cnName;
    }

    public static String getStateName(String stateCode) {
        if(StringUtils.isEmpty(stateCode)) return "";
        WorkOrderStateEnum stateEnum = WorkOrderStateEnum.valueOf(stateCode);
        return stateEnum == null ? stateCode : stateEnum.getCnName();
    }

}

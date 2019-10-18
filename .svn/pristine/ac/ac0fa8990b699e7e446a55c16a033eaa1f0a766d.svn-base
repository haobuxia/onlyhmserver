package com.tianyi.helmet.server.entity.scene.svc;

import org.springframework.util.StringUtils;

/**
 * 工单类型枚举
 * Created by liuhanc on 2018/6/30.
 */
public enum WorkOrderTypeEnum {
    DQBY("定期保养"),
    ZFJC("走访检查"),
    LJXS("零件销售"),
    ZQSW("债权事务"),
    XCDC("现场调查"),
    GSGZ("公司改装"),
    GCGZ("工厂改装"),
    CBFW("出保服务"),
    JJFW("交机服务"),
    GZCL("故障处理");

    private String cnName;

    public String getCnName() {
        return cnName;
    }

    private WorkOrderTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public static String getTypeName(String typeCode) {
        if(StringUtils.isEmpty(typeCode)) return "";
        WorkOrderTypeEnum typeEnum = WorkOrderTypeEnum.valueOf(typeCode);
        return typeEnum == null ? typeCode : typeEnum.getCnName();
    }
}

package com.tianyi.helmet.server.entity.scene.svc;

import org.springframework.util.StringUtils;

/**
 * 策略触发事件的类型
 * <p>
 * Created by liuhanc on 2018/7/5.
 */
public enum StrategyEventTypeEnum {
    TIME_LOOP("间隔时间触发"),
    TIME("到时间触发"),
    EVENT("发生事件触发"),
    POSITION("位置触发");

    private String cnName;

    private StrategyEventTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

    public static String getTypeCnName(String typeCode) {
        if(StringUtils.isEmpty(typeCode)) return "";
        StrategyEventTypeEnum typeEnum = StrategyEventTypeEnum.valueOf(typeCode);
        return typeEnum == null ? typeCode : typeEnum.getCnName();
    }
}

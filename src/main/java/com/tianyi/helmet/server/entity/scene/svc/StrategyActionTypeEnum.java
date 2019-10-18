package com.tianyi.helmet.server.entity.scene.svc;

import org.springframework.util.StringUtils;

/**
 * 策略触发动作的类型
 * <p>
 * Created by liuhanc on 2018/7/5.
 */
public enum StrategyActionTypeEnum {
    VIDEO("拍摄视频"),
    IMAGE("拍摄照片"),
    AUDIO("录音"),
    UPLOAD("上传"),
    LINK("激活链接");

    private String cnName;

    private StrategyActionTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

    public static String getTypeCnName(String typeCode) {
        if(StringUtils.isEmpty(typeCode)) return "";
        StrategyActionTypeEnum typeEnum = StrategyActionTypeEnum.valueOf(typeCode);
        return typeEnum == null ? typeCode : typeEnum.getCnName();
    }

}

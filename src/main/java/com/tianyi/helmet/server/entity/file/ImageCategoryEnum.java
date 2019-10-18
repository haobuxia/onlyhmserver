package com.tianyi.helmet.server.entity.file;

/**
 * 各个图片类型枚举
 */
public enum ImageCategoryEnum {
    RECIRC_JH(ResCategoryContants.RECIRC_JH_CNNAME),//机号
    RECIRC(ResCategoryContants.RECIRC_CNNAME),//二手循环机
    WHITEBOARD(ResCategoryContants.WHITEBOARD_CNNAME);//白板

    private String cnName;

    private ImageCategoryEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

}

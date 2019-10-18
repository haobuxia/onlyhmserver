package com.tianyi.helmet.server.entity.recirc;

/**
 * 循环机视频类型
 *
 * <p>
 * Created by liuhanc on 2018/4/23.
 */
public enum RecircVideoTypeEnum {
    SWING_ARM_TEST("动臂测试"),
    RORATE_TEST("旋转测试"),
    LEFT_TRACK_WALK("左履带行走"),
    LEFT_TRACK_WALK_OVERFLOW("左履带行走溢流"),
    RIGHT_TRACK_WALK("右履带行走"),
    DECLINE_UNLOAD("自然下降-铲斗卸载"),
    DECLINE_LOAD("自然下降-额定荷重");

    private String cnName;

    private RecircVideoTypeEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

}

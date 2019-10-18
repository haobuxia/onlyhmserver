package com.tianyi.helmet.server.entity.data.gpsenum;

import java.util.Arrays;

/**
 *  动作类型枚举
 * Created by liuhanc on 2017/10/23.
 */
public enum ActionTypeEnum {
    ARM_UP(0,"动臂上升"),
    ARM_DOWN(1,"动臂下降"),
    ROD_PULL(2,"斗杆回收"),
    ROD_PUSH(3,"斗杆伸出"),
    BUCKET_DIG(4,"铲斗挖掘"),
    BUCKET_UNLOAD(5,"铲斗卸载"),
    BACK(6,"回转"),
    WALK(7,"行走");

    int id;
    String name;

    public static ActionTypeEnum get(int id){
        return Arrays.stream(ActionTypeEnum.values()).filter(actionTypeEnum -> actionTypeEnum.id == id).findAny().orElse(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ActionTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }
}

package com.tianyi.helmet.server.entity.data.gpsenum;

import java.util.Arrays;

/**
 * 工作模式
 *
 * Created by liuhanc on 2017/10/23.
 */
public enum WorkModeEnum {
    ARM_UP(11,"快速模式"),//P模式
    ARM_DOWN(33,"经济模式"),//E
    ROD_PULL(34,"吊装模式"),//L
    ROD_PUSH(11,"破碎模式");//B

    int id;
    String name;

    private WorkModeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static WorkModeEnum get(int id){
        return Arrays.stream(WorkModeEnum.values()).filter(workModeEnum -> workModeEnum.id == id).findAny().orElse(null);
    }
    public static String translate(int id){
        WorkModeEnum workModeEnum = get(id);
        if(workModeEnum == null) return "";
        return workModeEnum.name;
    }
}

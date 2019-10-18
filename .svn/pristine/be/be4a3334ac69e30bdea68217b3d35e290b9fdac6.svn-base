package com.tianyi.helmet.server.entity.file;

import java.util.Arrays;

/**
 * 上传数据类型枚举
 * <p>
 * Created by liuhanc on 2017/10/26.
 */
public enum UploadEntityTypeEnum {
    video(1,"video"),
    image(2,"image"),
    audio(3,"audio"),
    file(4,"file");

    int id;
    String name;

    private UploadEntityTypeEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static UploadEntityTypeEnum get(String name){
        return Arrays.stream(UploadEntityTypeEnum.values()).filter(typeEnum -> typeEnum.getName().equals(name)).findAny().orElse(null);
    }

    public static UploadEntityTypeEnum get(int id){
       return Arrays.stream(UploadEntityTypeEnum.values()).filter(typeEnum -> typeEnum.getId() == id).findAny().orElse(null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

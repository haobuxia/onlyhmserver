package com.tianyi.helmet.server.entity.file;

import java.util.Arrays;

/**
 * 视频状态
 */
public enum VideoStateEnum {
    NOT_PROC(0),//未处理
    PROCING(1),//处理中
    SUCCESS(2),//处理成功
    FAIL(3),//处理失败
    TRACKING(4),//正在字幕合并
    TRACK_FAIL(5),//字幕合并处理失败
    SYNC_DATA(6),//同步二手机数据
    ;

    public static VideoStateEnum get(int state){
        return Arrays.stream(VideoStateEnum.values()).filter(en->en.getState() == state).findFirst().get();
    }

    private int state = 0;

    private VideoStateEnum(int state){
        this.state = state;
    }

    public int getState(){
        return state;
    }

}

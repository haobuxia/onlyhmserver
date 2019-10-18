package com.tianyi.helmet.server.entity.svc;

/**
 *  服务日志简要信息子模块枚举
 *
 * Created by liuhanc on 2018/4/16.
 */
public enum SvcFaultBriefEnum {
    siteVideo("拍摄工地"),
    diggerVideo("拍摄车辆"),
    faultCheckVideo("拍摄故障检测"),
    faultRepairVideo("拍摄故障修复"),
    jhPic("机号照片"),
    timeMile("时间里程"),
    usageStat("使用情况"),
    fault("故障基本信息"),
    faultResove("故障检查解决方式"),
    faultHandle("故障是否处理"),
    faultRepair("故障是否修复"),
    opinion("用户意见");

    String cnName;

    SvcFaultBriefEnum(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

}

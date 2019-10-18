package com.tianyi.helmet.server.entity.svc.po;

/**
 * 服务工单-时间里程
 * Created by liuhanc on 2018/3/16.
 */
public class SvcTimeMile extends SvcRwhAbstract {
    private String setoutTime;//出发时间
    private String returnTime;//返回时间
    private String mile;//里程

    public SvcTimeMile() {
    }

    public SvcTimeMile(String rwh, String setoutTime, String returnTime, String mile) {
        this.rwh = rwh;
        this.setoutTime = setoutTime;
        this.returnTime = returnTime;
        this.mile = mile;
    }

    public String getSetoutTime() {
        return setoutTime;
    }

    public void setSetoutTime(String setoutTime) {
        this.setoutTime = setoutTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    @Override
    public String toString(){
        return "rwh="+rwh+",setoutTime="+setoutTime+",returnTime="+returnTime+",mile="+mile;
    }
}

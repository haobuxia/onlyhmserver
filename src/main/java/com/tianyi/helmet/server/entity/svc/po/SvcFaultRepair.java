package com.tianyi.helmet.server.entity.svc.po;

/**
 * 故障修复
 * Created by liuhanc on 2018/3/16.
 */
public class SvcFaultRepair extends SvcRwhAbstract {
    private boolean repaired;
    private String repairedVideoPath;

    public SvcFaultRepair(){
    }

    public SvcFaultRepair(String rwh,boolean repaired, String repairedVideoPath){
        this.rwh =rwh;
        this.repaired = repaired;
        this.repairedVideoPath = repairedVideoPath;
    }

    /**
     * 已修复要有视频
     * @param rwh
     * @param videoPath
     * @return
     */
    public static SvcFaultRepair repaired(String rwh,String videoPath){
        return new SvcFaultRepair(rwh,true,videoPath);
    }

    /**
     * 未修复，不要原因
     *
     * @param rwh
     * @return
     */
    public static SvcFaultRepair notRepaired(String rwh){
        return new SvcFaultRepair(rwh,false,null);
    }

    public boolean isRepaired() {
        return repaired;
    }

    public void setRepaired(boolean repaired) {
        this.repaired = repaired;
    }

    public String getRepairedVideoPath() {
        return repairedVideoPath;
    }

    public void setRepairedVideoPath(String repairedVideoPath) {
        this.repairedVideoPath = repairedVideoPath;
    }

    @Override
    public String toString(){
        return "rwh="+rwh+",repaired="+repaired+",repairedVideoPath="+repairedVideoPath;
    }
}

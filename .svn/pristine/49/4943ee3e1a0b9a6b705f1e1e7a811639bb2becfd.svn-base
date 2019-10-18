package com.tianyi.helmet.server.entity.svc.po;

/**
 * 服务工单任务
 * Created by liuhanc on 2018/3/16.
 */
public class SvcSimpleTask extends SvcRwhAbstract {
    private String jh;
    private String rwlb;
    /**
     * 任务状态 0待填写，1已填写未完成,2填写完成未上传,3已上传待审核,4已上传被打回,5已上传审核通过。
     * app上判断状态=2,4的可以上传提交，0,1的可以继续填写，3的只能查看
     */
    private String rwzt;//

    public SvcSimpleTask() {
    }

    public SvcSimpleTask(String rwh, String jh, String rwlb,String rwzt) {
        this.rwh = rwh;
        this.jh = jh;
        this.rwlb = rwlb;
        this.rwzt = rwzt;
    }

    public String getJh() {
        return jh;
    }

    public void setJh(String jh) {
        this.jh = jh;
    }

    public String getRwlb() {
        return rwlb;
    }

    public void setRwlb(String rwlb) {
        this.rwlb = rwlb;
    }

    public String getRwzt() {
        return rwzt;
    }

    public void setRwzt(String rwzt) {
        this.rwzt = rwzt;
    }

    @Override
    public String toString() {
        return "rwh=" + rwh + ",jh=" + jh + ",rwlb=" + rwlb+",rwzt="+rwzt;
    }
}

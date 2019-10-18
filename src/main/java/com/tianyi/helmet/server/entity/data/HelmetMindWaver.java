package com.tianyi.helmet.server.entity.data;

/**
 * 脑波信息
 * Created by wenxinyan on 2018/9/20.
 */
public class HelmetMindWaver extends HelmetData {
    Integer concert;
    Integer relax;
    Integer runtime;

    public Integer getConcert() {
        return concert;
    }

    public void setConcert(Integer concert) {
        this.concert = concert;
    }

    public Integer getRelax() {
        return relax;
    }

    public void setRelax(Integer relax) {
        this.relax = relax;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
}

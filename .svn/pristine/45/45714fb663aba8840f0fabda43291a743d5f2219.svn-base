package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.util.List;

/**
 * Created by tianxujin on 2019/6/12 14:03
 */
public class HelmetUniversalInfo extends IdEntity implements java.io.Serializable {
    private Integer selectType = 1;//选择类型（1；列表；2：扫码）
    private String customer;  //客户类型类型（车辆，客户等）
    private String taskType; //任务类型（检查，工单）
    private String finishType; //结束类型（通过，结束）
    private String project;//所属项目（0常州小松工厂1租赁3大修厂4智能服务）id代表了
    private String isQuick; // 是否快速检查0否1是
    private List<CustomerInfo> customers;
    private String avprovider;

    public String getAvprovider() {
        return avprovider;
    }

    public void setAvprovider(String avprovider) {
        this.avprovider = avprovider;
    }

    public String getIsQuick() {
        return isQuick;
    }

    public void setIsQuick(String isQuick) {
        this.isQuick = isQuick;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getFinishType() {
        return finishType;
    }

    public void setFinishType(String finishType) {
        this.finishType = finishType;
    }

    public List<CustomerInfo> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerInfo> customers) {
        this.customers = customers;
    }
}

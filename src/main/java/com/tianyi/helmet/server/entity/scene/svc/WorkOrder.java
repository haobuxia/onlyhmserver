package com.tianyi.helmet.server.entity.scene.svc;

import com.tianyi.helmet.server.entity.IdEntity;

import java.util.Date;
import java.util.List;

/**
 * 工单
 * Created by liuhanc on 2018/6/30.
 */
public class WorkOrder extends IdEntity {
    private String orderNo;//工单号
    private String subject;//工作主题
    private String orderType;// 工单类型代码//@see WorkOrderTypeEnum
    private Integer userId;//服务人员Id
    private String userRealName;//服务人员姓名
    private String customerName;//客户公司或客户称谓
    private String contactName;//客户联系人姓名
    private String contactPhone;//客户联系人电话
    private String latLng;//车辆定位gps，经纬度以,分隔
    private String address;//车辆定位地址
    private String brand;//车辆品牌
    private String model;//车辆机型
    private String machineCode;//车辆机号
    private String orderState = WorkOrderStateEnum.INIT.toString();// @see WorkOrderStateEnum
    private Integer collaborateCnt = 0;//协作次数
    private Integer orderCnt = 0;//订单数
    private String remark;//备注
    private String tags;//需要采集的视频标签
    private Date planTime;//排班时间
    private Date inputTime;//填写时间
    private Date startTime;//工单开始时间
    private Date endTime;//工单结束时间

    private String orderTypeName;//工单任务类型的名称，不入库存储
    private String orderStateName;//工单状态名，不入库存储

    private List<WorkOrderStrategy> strategyList;//工单策略
    private String videoCounts;//tags对应各个标签在当前工单对应视频数量.不入库存储

    public String getVideoCounts() {
        return videoCounts;
    }

    public void setVideoCounts(String videoCounts) {
        this.videoCounts = videoCounts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCollaborateCnt(Integer collaborateCnt) {
        this.collaborateCnt = collaborateCnt;
    }

    public void setOrderCnt(Integer orderCnt) {
        this.orderCnt = orderCnt;
    }

    public List<WorkOrderStrategy> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<WorkOrderStrategy> strategyList) {
        this.strategyList = strategyList;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(int orderCnt) {
        this.orderCnt = orderCnt;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getOrderStateName() {
        return orderStateName;
    }

    public void setOrderStateName(String orderStateName) {
        this.orderStateName = orderStateName;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public int getCollaborateCnt() {
        return collaborateCnt;
    }

    public void setCollaborateCnt(int collaborateCnt) {
        this.collaborateCnt = collaborateCnt;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}

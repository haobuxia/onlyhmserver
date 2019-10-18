package com.tianyi.helmet.server.vo.workorder;

import com.tianyi.helmet.server.entity.workorder.TyDevice;
import com.tianyi.helmet.server.entity.workorder.WorkPart;

import java.util.List;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019/1/3 17:29
 * @version 0.1
 **/
public class WorkCardVo {
    private String workCardId;
    private String workCardName;
    private String workCardType;
    private String workCardTypeName;
    private Long workCardPlanTime;
    private String workCardPlanText;
    private String workCardStatus;
    private String workCardStatusName;
    private String srvPersonId;
    private String srvPersonName;
    private TyDevice deviceInfo;
    private String customerId;
    private String customerName;
    private String customerPhone;
    private List <WorkPartVo> parts;
    private List<WorkJobVo> workJobs;
    private CustomerVo customerInfo;

    public TyDevice getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(TyDevice deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getWorkCardId() {
        return workCardId;
    }

    public void setWorkCardId(String workCardId) {
        this.workCardId = workCardId;
    }

    public String getWorkCardName() {
        return workCardName;
    }

    public void setWorkCardName(String workCardName) {
        this.workCardName = workCardName;
    }

    public String getWorkCardType() {
        return workCardType;
    }

    public void setWorkCardType(String workCardType) {
        this.workCardType = workCardType;
    }

    public String getWorkCardTypeName() {
        return workCardTypeName;
    }

    public void setWorkCardTypeName(String workCardTypeName) {
        this.workCardTypeName = workCardTypeName;
    }

    public Long getWorkCardPlanTime() {
        return workCardPlanTime;
    }

    public void setWorkCardPlanTime(Long workCardPlanTime) {
        this.workCardPlanTime = workCardPlanTime;
    }

    public String getWorkCardPlanText() {
        return workCardPlanText;
    }

    public void setWorkCardPlanText(String workCardPlanText) {
        this.workCardPlanText = workCardPlanText;
    }

    public String getWorkCardStatus() {
        return workCardStatus;
    }

    public void setWorkCardStatus(String workCardStatus) {
        this.workCardStatus = workCardStatus;
    }

    public String getWorkCardStatusName() {
        return workCardStatusName;
    }

    public void setWorkCardStatusName(String workCardStatusName) {
        this.workCardStatusName = workCardStatusName;
    }

    public String getSrvPersonId() {
        return srvPersonId;
    }

    public void setSrvPersonId(String srvPersonId) {
        this.srvPersonId = srvPersonId;
    }

    public String getSrvPersonName() {
        return srvPersonName;
    }

    public void setSrvPersonName(String srvPersonName) {
        this.srvPersonName = srvPersonName;
    }



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<WorkPartVo> getParts() {
        return parts;
    }

    public void setParts(List<WorkPartVo> parts) {
        this.parts = parts;
    }

    public List<WorkJobVo> getWorkJobs() {
        return workJobs;
    }

    public void setWorkJobs(List<WorkJobVo> workJobs) {
        this.workJobs = workJobs;
    }

    public void setCustomerInfo(CustomerVo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public CustomerVo getCustomerInfo() {
        return customerInfo;
    }
}

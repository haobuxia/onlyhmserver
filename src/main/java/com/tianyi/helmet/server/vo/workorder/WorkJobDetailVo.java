package com.tianyi.helmet.server.vo.workorder;

public class WorkJobDetailVo {
    private String partAddress;

    private String partDescribe;

    private String serviceAddress;

    private String serviceDescribe;


    public String getPartAddress() {
        return partAddress;
    }

    public void setPartAddress(String partAddress) {
        this.partAddress = partAddress;
    }

    public String getPartDescribe() {
        return partDescribe;
    }

    public void setPartDescribe(String partDescribe) {
        this.partDescribe = partDescribe;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getServiceDescribe() {
        return serviceDescribe;
    }

    public void setServiceDescribe(String serviceDescribe) {
        this.serviceDescribe = serviceDescribe;
    }
}

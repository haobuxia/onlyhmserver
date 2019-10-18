package com.tianyi.helmet.server.vo.workorder;

public class WorkPartDeliveryAppVo {
    private String partId;
    private String partName;
    private String partCount;

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartCount() {
        return partCount;
    }

    public void setPartCount(String partCount) {
        this.partCount = partCount;
    }

    @Override
    public String toString() {
        return "WorkPartDeliveryVo{" +
                "partId='" + partId + '\'' +
                ", partName='" + partName + '\'' +
                ", partCount='" + partCount + '\'' +
                '}';
    }
}
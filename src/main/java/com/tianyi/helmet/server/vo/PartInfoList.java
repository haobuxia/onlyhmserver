package com.tianyi.helmet.server.vo;

import java.io.Serializable;
import java.util.List;

public class PartInfoList implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<PartsList> partsList;
    private String vclType;

    public List<PartsList> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<PartsList> partsList) {
        this.partsList = partsList;
    }

    public String getVclType() {
        return vclType;
    }

    public void setVclType(String vclType) {
        this.vclType = vclType;
    }

    @Override
    public String toString() {
        return "PartInfoList{" +
                "partsList=" + partsList +
                ", vclType='" + vclType + '\'' +
                '}';
    }
}

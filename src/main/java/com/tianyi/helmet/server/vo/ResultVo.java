package com.tianyi.helmet.server.vo;

import java.io.Serializable;
import java.util.List;

public class ResultVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PartInfoList> partInfoList;
    private List<InventoryList> inventoryList;

    public List<PartInfoList> getPartInfoList() {
        return partInfoList;
    }

    public void setPartInfoList(List<PartInfoList> partInfoList) {
        this.partInfoList = partInfoList;
    }

    public List<InventoryList> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<InventoryList> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "partInfoList=" + partInfoList +
                ", inventoryList=" + inventoryList +
                '}';
    }
}

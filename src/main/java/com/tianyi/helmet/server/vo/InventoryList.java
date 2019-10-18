package com.tianyi.helmet.server.vo;

import java.io.Serializable;

public class InventoryList implements Serializable{
    private static final long serialVersionUID = 1L;
    private String warehouseName;
    private String inventoryQty;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getInventoryQty() {
        return inventoryQty;
    }

    public void setInventoryQty(String inventoryQty) {
        this.inventoryQty = inventoryQty;
    }

    @Override
    public String toString() {
        return "InventoryList{" +
                "warehouseName='" + warehouseName + '\'' +
                ", inventoryQty='" + inventoryQty + '\'' +
                '}';
    }
}

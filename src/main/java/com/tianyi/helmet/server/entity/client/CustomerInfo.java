package com.tianyi.helmet.server.entity.client;

import java.util.List;

/**
 * Created by tianxujin on 2019/6/12 14:07
 */
public class CustomerInfo implements java.io.Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

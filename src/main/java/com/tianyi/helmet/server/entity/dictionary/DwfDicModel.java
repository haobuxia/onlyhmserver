package com.tianyi.helmet.server.entity.dictionary;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * DWF获取基础字典表
 * Created by tianxujin on 2019/2/1.
 */

public class DwfDicModel {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

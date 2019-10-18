package com.tianyi.helmet.server.entity.dictionary;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 省 字典表
 * Created by wenxinyan on 2018/9/30.
 */
public class Province extends IdEntity {
    private String provincialName;

    public String getProvincialName() {
        return provincialName;
    }

    public void setProvincialName(String provincialName) {
        this.provincialName = provincialName;
    }
}

package com.tianyi.helmet.server.entity.dictionary;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 单位性质 字典表
 * Created by wenxinyan on 2018/9/30.
 */
public class CompanyNature extends IdEntity {
    private String natureName;

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }
}

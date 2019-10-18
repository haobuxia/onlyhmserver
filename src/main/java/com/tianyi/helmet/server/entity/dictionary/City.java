package com.tianyi.helmet.server.entity.dictionary;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 市 字典表
 * Created by wenxinyan on 2018/9/30.
 */
public class City extends IdEntity {
    private String cityName;
    private int provincialId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getProvincialId() {
        return provincialId;
    }

    public void setProvincialId(int provincialId) {
        this.provincialId = provincialId;
    }
}

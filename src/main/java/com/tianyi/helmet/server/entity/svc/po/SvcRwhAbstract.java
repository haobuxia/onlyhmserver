package com.tianyi.helmet.server.entity.svc.po;

import com.tianyi.helmet.server.entity.svc.SvcRwhEntity;

/**
 * Created by liuhanc on 2018/3/19.
 */
public class SvcRwhAbstract implements SvcRwhEntity {
    protected String rwh;

    public void setRwh(String rwh) {
        this.rwh = rwh;
    }

    @Override
    public String getRwh() {
        return rwh;
    }
}

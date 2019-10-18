package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.util.List;

/**
 * Created by tianxujin on 2019/8/24 14:03
 */
public class HelmetUniversalurlInfo extends IdEntity implements java.io.Serializable {
    private String url;  // 系统url
    private String urltype; // 访问类型
    private int uid;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrltype() {
        return urltype;
    }

    public void setUrltype(String urltype) {
        this.urltype = urltype;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}

package com.tianyi.helmet.server.entity.svc.po;

/**
 * 服务日志接口的分页
 *
 * Created by liuhanc on 2018/3/13.
 */
public class SvcRestPage {
    public SvcRestPage(int page, int size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    private int page;//页数以0开头
    private int size;
    private String sort;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

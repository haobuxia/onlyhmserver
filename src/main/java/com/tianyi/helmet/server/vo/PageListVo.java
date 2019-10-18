package com.tianyi.helmet.server.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 分页数据对象
 * <p>
 * Created by liuhanc on 2017/10/12.
 */
public class PageListVo<T> {
    private List<T> list;
    private int page;
    private int pageSize;
    private int total;
    private int flag;//仅用在工单管理查询视频列表的时候

    public static final int DEFAULT_PAGE_SIZE = 12;

    public static Map<String, Object> createParamMap(Integer page) {
        return createParamMap(page,DEFAULT_PAGE_SIZE);
    }

    public static Map<String, Object> createParamMap(Integer page, Integer pageSize) {
        if (page == null) page = 1;
        if (pageSize == null) pageSize = DEFAULT_PAGE_SIZE;

        Map<String, Object> map =  new HashMap<>();
        map.put("start", (page - 1) * pageSize);
        map.put("length", pageSize);

        return map;
    }

    public PageListVo() {
        this(1);
    }

    public PageListVo(int page) {
        this(page,DEFAULT_PAGE_SIZE);
    }

    public PageListVo(int page, int pageSize) {
        if (page == 0) page = 1;
        if (pageSize == 0) pageSize = PageListVo.DEFAULT_PAGE_SIZE;
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageListVo(int page, List<T> list, int total) {
        this(page,DEFAULT_PAGE_SIZE,list,total);
    }

    public PageListVo(int page, int pageSize, List<T> list, int total) {
        if (page == 0) page = 1;
        if (pageSize == 0) pageSize = PageListVo.DEFAULT_PAGE_SIZE;
        this.page = page;
        this.pageSize = pageSize;
        this.list = list;
        this.total = total;
    }

    public int getPageCount() {
        return total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    public boolean hasPrePage() {
        return page > 1;
    }

    public boolean hasNextPage() {
        return page < getPageCount();
    }

    public int[] getPageRange() {
        int pageCount = getPageCount();
        if (pageCount <= 10) {
            return IntStream.range(1, pageCount + 1).toArray();
        } else {
            //总页数pageCount > 10
            if (page <= 5) {
                return IntStream.range(1, 10).toArray();
            } else {
                if (page < pageCount - 5) {
                    return IntStream.range(page - 5, page + 5).toArray();
                } else {
                    return IntStream.range(page - 5, pageCount + 1).toArray();
                }
            }
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

package com.tianyi.helmet.server.entity;


import java.util.Comparator;
import java.util.Objects;

/**
 * 基于id自增字段作为主键的实体类基础类
 *
 * Created by liuhanc on 2017/10/17.
 */
public abstract class IdEntity implements Comparable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        IdEntity e = (IdEntity) o;
        return Objects.compare(this.getId(), e.getId(), Comparator.comparing(Integer::intValue));
    }
}

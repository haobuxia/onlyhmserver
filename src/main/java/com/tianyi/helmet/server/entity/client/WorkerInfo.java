package com.tianyi.helmet.server.entity.client;

import java.util.List;

/**
 * Created by tianxujin on 2019/6/12 14:13
 */
public class WorkerInfo implements java.io.Serializable {
    private String id;
    private String code;
    private String name;
    private String details;
    private String state;
    private List<TaskInfo> tasks; // 工单列表

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TaskInfo> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskInfo> tasks) {
        this.tasks = tasks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

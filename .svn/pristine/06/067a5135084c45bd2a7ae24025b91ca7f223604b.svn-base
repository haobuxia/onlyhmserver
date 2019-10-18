package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * 标签分组
 * Created by liuhanc on 2018/5/24
 */
public class TagGroup extends IdEntity {
    private String groupName;//标签名称
    private LocalDateTime createTime;

    public TagGroup() {
    }

    public TagGroup(int id, String groupName) {
        this.setId(id);
        this.setGroupName(groupName);
    }

    public String getCreateTimeString() {
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime), "yyyy-MM-dd HH:mm:ss");
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

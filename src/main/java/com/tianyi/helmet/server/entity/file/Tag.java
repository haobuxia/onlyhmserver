package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * 标签
 * Created by liuhanc on 2017/12/28.
 */
public class Tag extends IdEntity {
    private String tagName;//标签名称
    private LocalDateTime createTime;
    private Integer groupId;

    public Tag() {
    }

    public Tag(int id, String tagName, int groupId) {
        this.setId(id);
        this.setTagName(tagName);
        this.setGroupId(groupId);
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCreateTimeString() {
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime), "yyyy-MM-dd HH:mm:ss");
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}

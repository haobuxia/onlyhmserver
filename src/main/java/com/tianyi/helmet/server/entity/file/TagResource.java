package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * 标签资源
 * Created by liuhanc on 2017/12/28.
 */
public class TagResource extends IdEntity {
    private int tagId;//标签名称
    private LocalDateTime createTime;
    /**
     * @see UploadEntityTypeEnum
     */
    private int resType;//资源类型
    private int resId;

    public String getCreateTimeString() {
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime), "yyyy-MM-dd HH:mm:ss");
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

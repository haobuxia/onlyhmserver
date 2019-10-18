package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.util.Date;

/**
 * 田一后台系统用户系的联人
 * <p>
 * Created by liuhanc on 2018/6/29
 */
public class TianyiContact extends IdEntity {
    private int userId;//用户id
    private int contactId;//联系人用户id
    private Date addTime;
    private String remark;//备注

    private User contactUser;//联系人用户信息

    public User getContactUser() {
        return contactUser;
    }

    public void setContactUser(User contactUser) {
        this.contactUser = contactUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 用户角色对应关系
 *
 * Created by liuhanc on 2017/11/29.
 */
public class TianyiUserRole extends IdEntity{
    private int userId;
    private String roleCode;//@see RoleEnum
    private LocalDateTime createTime;//

    public TianyiUserRole(){}
    public TianyiUserRole(int userId,String roleCode){
        this.userId = userId;
        this.roleCode = roleCode;
        this.createTime = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}

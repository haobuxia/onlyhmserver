package com.tianyi.helmet.server.entity.power;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 角色信息
 * Created by wenxinyan on 2018/10/9.
 */
public class Role extends IdEntity {
    private LocalDateTime createTime;
    private String roleName;
    private Integer companyId;
    private Integer universalId;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUniversalId() {
        return universalId;
    }

    public void setUniversalId(Integer universalId) {
        this.universalId = universalId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

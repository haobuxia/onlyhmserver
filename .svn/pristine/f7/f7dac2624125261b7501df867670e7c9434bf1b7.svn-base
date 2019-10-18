package com.tianyi.helmet.server.entity.client;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * 基础用户抽象类
 *
 * <p>
 * Created by liuhanc on 2017/11/29.
 */
public abstract class AbstractUserInfo extends IdEntity {
    private String username;
    private String password;
    private LocalDateTime regTime;

    public abstract String getUserStrType();

    public String getRegTimeString() {
        return regTime == null ? "" : Dates.format(Dates.toDate(regTime), "yyyy-MM-dd HH:mm:ss");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }
}

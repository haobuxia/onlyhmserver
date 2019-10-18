package com.tianyi.helmet.server.entity.app;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * apk文件更新
 *
 * Created by liuhanc on 2017/12/18.
 */
public class ApkUpdate extends IdEntity{
    private LocalDateTime createTime;
    private Integer createUserId;//创建更新配置的人
    private String createUserName;//关联对象不入库
    private String clientId;//头盔id
    private String clientNumber;//头盔编号关联对象不入库
    private Integer apkId;
    private String apkFileType;
    private ApkFile apkFile;//关联对象不入库

    private LocalDateTime updateTime;
    private Integer status=0;

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApkFileType() {
        return apkFileType;
    }

    public void setApkFileType(String apkFileType) {
        this.apkFileType = apkFileType;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTimeString(){
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime),"yyyy-MM-dd HH:mm:ss");
    }

    public ApkFile getApkFile() {
        return apkFile;
    }

    public void setApkFile(ApkFile apkFile) {
        this.apkFile = apkFile;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getApkId() {
        return apkId;
    }

    public void setApkId(Integer apkId) {
        this.apkId = apkId;
    }
}

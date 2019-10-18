package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;

import java.time.LocalDateTime;

/**
 * 上传数据信息抽象类
 *
 * <p>
 * Created by liuhanc on 2017/10/26.
 */
public abstract class UploadEntity extends IdEntity {
    private LocalDateTime uploadTime;//上传时间
    private LocalDateTime createTime;//录制时间
    private String clientId;//头盔imei唯一识别标志
    /**
     * update by xiayuan 2018/10/10
     */
    private String neUserName;//头盔定义的网易账号
    private int userId;//视频录制机手唯一识别标志
    private String account;//视频录制人姓名

    private String fileName;
    private String description;
    private String ossPath;//文件存储oss路径
    private int viewCount;
    private long sizeKb;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNeUserName() {
        return neUserName;
    }

    public void setNeUserName(String neUserName) {
        this.neUserName = neUserName;
    }

    public abstract UploadEntityTypeEnum getType();

    public String getCreateTimeString() {
        return createTime == null ? "" : Dates.format(Dates.toDate(createTime), "yyyy-MM-dd HH:mm:ss");
    }

    public String getUploadTimeString() {
        return uploadTime == null ? "" : Dates.format(Dates.toDate(uploadTime), "yyyy-MM-dd HH:mm:ss");
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public long getSizeKb() {
        return sizeKb;
    }

    public void setSizeKb(long sizeKb) {
        this.sizeKb = sizeKb;
    }

}

package com.tianyi.helmet.server.entity.app;

import com.tianyi.helmet.server.entity.IdEntity;
import com.tianyi.helmet.server.util.Dates;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * apk 文件信息
 *
 * Created by liuhanc on 2017/12/18.
 */
public class ApkFile extends IdEntity {
    private String fileName;
    private String fileType;//@ ApkFileTypeEnum
    private LocalDateTime uploadTime;
    private String version;
    private String description;
    private String ossPath;
    private Integer uploadUserId;
    private String uploadUserName;//不入库

    public String getFileTypeName(){
        return StringUtils.isEmpty(fileType) ? "":ApkFileTypeEnum.valueOf(fileType).getName();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public String getUploadTimeString(){
        return uploadTime == null ? "" : Dates.format(Dates.toDate(uploadTime),"yyyy-MM-dd HH:mm:ss");
    }
    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }
}

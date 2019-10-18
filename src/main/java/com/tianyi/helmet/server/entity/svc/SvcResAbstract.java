package com.tianyi.helmet.server.entity.svc;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 服务过程中录制的资源抽象类
 *
 * <p>
 * Created by liuhanc on 2018/3/19.
 */
public abstract class SvcResAbstract extends IdEntity implements SvcRwhEntity {
    protected String rwh;//任务号
    private String svcId;//主记录id
    private LocalDateTime uploadTime;//上传时间
    private String machineCode;//机号
    private String clientId;//头盔id
    private String oprtId;//录制人id 天远体系里的用户id，oprtId
    private String userName;
    private String ossPath;//保存的oss路径
    private String resPath;//资源播放完整路径,不入库存储

    /**
     * 获得资源分类
     *
     * @return
     * @see SvcResTypeEnum
     */
    public abstract SvcResTypeEnum getResType();

    /**
     * 获得资源内部分类
     *
     * @return
     */
    public abstract String getResInnerTypeName();

    public String getSvcId() {
        return svcId;
    }

    public void setSvcId(String svcId) {
        this.svcId = svcId;
    }

    public String getResPath() {
        return resPath;
    }

    public void setResPathPrefix(String pathPrefix) {
        this.resPath = pathPrefix + ossPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRwh() {
        return rwh;
    }

    public void setRwh(String rwh) {
        this.rwh = rwh;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getOprtId() {
        return oprtId;
    }

    public void setOprtId(String oprtId) {
        this.oprtId = oprtId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}

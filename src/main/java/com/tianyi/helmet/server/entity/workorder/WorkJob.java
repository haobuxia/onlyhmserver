package com.tianyi.helmet.server.entity.workorder;

public class WorkJob {
    private Integer jobPk;

    private String pltOid;

    private String pltTyWorkOid;

    private String pltJobName;

    private Boolean pltIsCustomerJob;

    private String pltJobStatus;

    private Integer pltJobStartTime;

    private Integer pltJobFinishTime;

    private String pltJobVideos;

    private String pltJobDetails;

    private Integer pltSequence;

    private String pltTyExpertWorkOid;

    private String pltJobSummary;

    private String pltTyExpertJobOid;

    public Integer getJobPk() {
        return jobPk;
    }

    public void setJobPk(Integer jobPk) {
        this.jobPk = jobPk;
    }

    public String getPltOid() {
        return pltOid;
    }

    public void setPltOid(String pltOid) {
        this.pltOid = pltOid == null ? null : pltOid.trim();
    }

    public String getPltTyWorkOid() {
        return pltTyWorkOid;
    }

    public void setPltTyWorkOid(String pltTyWorkOid) {
        this.pltTyWorkOid = pltTyWorkOid == null ? null : pltTyWorkOid.trim();
    }

    public String getPltJobName() {
        return pltJobName;
    }

    public void setPltJobName(String pltJobName) {
        this.pltJobName = pltJobName == null ? null : pltJobName.trim();
    }

    public Boolean getPltIsCustomerJob() {
        return pltIsCustomerJob;
    }

    public void setPltIsCustomerJob(Boolean pltIsCustomerJob) {
        this.pltIsCustomerJob = pltIsCustomerJob;
    }

    public String getPltJobStatus() {
        return pltJobStatus;
    }

    public void setPltJobStatus(String pltJobStatus) {
        this.pltJobStatus = pltJobStatus == null ? null : pltJobStatus.trim();
    }

    public Integer getPltJobStartTime() {
        return pltJobStartTime;
    }

    public void setPltJobStartTime(Integer pltJobStartTime) {
        this.pltJobStartTime = pltJobStartTime;
    }

    public Integer getPltJobFinishTime() {
        return pltJobFinishTime;
    }

    public void setPltJobFinishTime(Integer pltJobFinishTime) {
        this.pltJobFinishTime = pltJobFinishTime;
    }

    public String getPltJobVideos() {
        return pltJobVideos;
    }

    public void setPltJobVideos(String pltJobVideos) {
        this.pltJobVideos = pltJobVideos == null ? null : pltJobVideos.trim();
    }

    public String getPltJobDetails() {
        return pltJobDetails;
    }

    public void setPltJobDetails(String pltJobDetails) {
        this.pltJobDetails = pltJobDetails == null ? null : pltJobDetails.trim();
    }

    public Integer getPltSequence() {
        return pltSequence;
    }

    public void setPltSequence(Integer pltSequence) {
        this.pltSequence = pltSequence;
    }

    public String getPltTyExpertWorkOid() {
        return pltTyExpertWorkOid;
    }

    public void setPltTyExpertWorkOid(String pltTyExpertWorkOid) {
        this.pltTyExpertWorkOid = pltTyExpertWorkOid == null ? null : pltTyExpertWorkOid.trim();
    }

    public String getPltJobSummary() {
        return pltJobSummary;
    }

    public void setPltJobSummary(String pltJobSummary) {
        this.pltJobSummary = pltJobSummary == null ? null : pltJobSummary.trim();
    }

    public String getPltTyExpertJobOid() {
        return pltTyExpertJobOid;
    }

    public void setPltTyExpertJobOid(String pltTyExpertJobOid) {
        this.pltTyExpertJobOid = pltTyExpertJobOid == null ? null : pltTyExpertJobOid.trim();
    }
}
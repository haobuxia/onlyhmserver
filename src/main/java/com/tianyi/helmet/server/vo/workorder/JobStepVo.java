package com.tianyi.helmet.server.vo.workorder;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019/1/4 08:17
 * @version 0.1
 **/
public class JobStepVo {
    private String jobId;
    private String stepId;
    private Integer stepSeq;
    private String stepSummary;
    private String stepDesc;
    private Boolean isMark = Boolean.FALSE;
    private List<String> pictures = new ArrayList<>();
    private List<String> videos = new ArrayList<>();

    /**
     * @return the jobId
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the stepId
     */
    public String getStepId() {
        return stepId;
    }

    /**
     * @param stepId the stepId to set
     */
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    /**
     * @return the stepSeq
     */
    public Integer getStepSeq() {
        return stepSeq;
    }

    /**
     * @param stepSeq the stepSeq to set
     */
    public void setStepSeq(Integer stepSeq) {
        this.stepSeq = stepSeq;
    }

    /**
     * @return the stepSummary
     */
    public String getStepSummary() {
        return stepSummary;
    }

    /**
     * @param stepSummary the stepSummary to set
     */
    public void setStepSummary(String stepSummary) {
        this.stepSummary = stepSummary;
    }

    /**
     * @return the stepDesc
     */
    public String getStepDesc() {
        return stepDesc;
    }

    /**
     * @param stepDesc the stepDesc to set
     */
    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    /**
     * @return the isMark
     */
    public Boolean getIsMark() {
        return isMark;
    }

    /**
     * @param isMark the isMark to set
     */
    public void setIsMark(Boolean isMark) {
        this.isMark = isMark;
    }

    /**
     * @return the pictures
     */
    public List<String> getPictures() {
        return pictures;
    }

    /**
     * @param pictures the pictures to set
     */
    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    /**
     * @return the videos
     */
    public List<String> getVideos() {
        return videos;
    }

    /**
     * @param videos the videos to set
     */
    public void setVideos(List<String> videos) {
        this.videos = videos;
    }


}

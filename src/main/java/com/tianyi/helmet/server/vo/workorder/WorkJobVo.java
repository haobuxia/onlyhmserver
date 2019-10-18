package com.tianyi.helmet.server.vo.workorder;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019/1/4 08:06
 * @version 0.1
 **/
public class WorkJobVo {

    private String workCardId;
    private String jobId;
    private String jobSeq;
    private String jobSummary;
    private String jobDescribe;
    private String jobStatus;
    private String jobStatusName;
    private List<JobStepVo> steps = new ArrayList<>();

    public String getWorkCardId() {
        return workCardId;
    }

    public void setWorkCardId(String workCardId) {
        this.workCardId = workCardId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobSeq() {
        return jobSeq;
    }

    public void setJobSeq(String jobSeq) {
        this.jobSeq = jobSeq;
    }

    public String getJobSummary() {
        return jobSummary;
    }

    public void setJobSummary(String jobSummary) {
        this.jobSummary = jobSummary;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatusName() {
        return jobStatusName;
    }

    public void setJobStatusName(String jobStatusName) {
        this.jobStatusName = jobStatusName;
    }

    public List<JobStepVo> getSteps() {
        return steps;
    }

    public void setSteps(List<JobStepVo> steps) {
        this.steps = steps;
    }
}

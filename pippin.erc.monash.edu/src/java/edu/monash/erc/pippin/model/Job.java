package edu.monash.erc.pippin.model;

import java.util.List;

/**
 * @名称 POJO类 - Job
 * @作者 ${author}
 * @日期 ${datetime}
 */
public class Job {
    private java.lang.String jobId;
    public java.lang.String getJobId() {
        return jobId;
    }
    public void setJobId(java.lang.String jobId) {
        this.jobId = jobId;
    }

    private java.lang.String time;
    public java.lang.String getTime() {
        return time;
    }
    public void setTime(java.lang.String time) {
        this.time = time;
    }

    private java.lang.String status;
    public java.lang.String getStatus() {
        return status;
    }
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private List<Protein> proteinList;

    public List<Protein> getProteinList() {
        return proteinList;
    }

    public void setProteinList(List<Protein> proteinList) {
        this.proteinList = proteinList;
    }
    

}
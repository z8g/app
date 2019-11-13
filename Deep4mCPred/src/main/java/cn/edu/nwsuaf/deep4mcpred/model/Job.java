package cn.edu.nwsuaf.deep4mcpred.model;

import java.util.Set;

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

    private Set<OutputData> outputDataSet;

    public Set<OutputData> getOutputDataSet() {
        return outputDataSet;
    }

    public void setOutputDataSet(Set<OutputData> outputDataSet) {
        this.outputDataSet = outputDataSet;
    }

}

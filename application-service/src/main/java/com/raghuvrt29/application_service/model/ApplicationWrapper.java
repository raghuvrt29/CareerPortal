package com.raghuvrt29.application_service.model;

public class ApplicationWrapper {
    private String applicationId;
    private String jobId;
    private String status;

    public ApplicationWrapper(Application application){
        this.applicationId = application.getId().toString();
        this.jobId = application.getJobPostId().toString();
        this.status = application.getStatus();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

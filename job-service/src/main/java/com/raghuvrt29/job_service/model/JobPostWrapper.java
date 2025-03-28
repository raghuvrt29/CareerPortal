package com.raghuvrt29.job_service.model;

public class JobPostWrapper {
    private String jobPostId;
    private String postProfile;
    private String employerId;
    private Integer reqExperience;

    public JobPostWrapper(JobPost jobPost){
        this.jobPostId = jobPost.getPostId().toString();
        this.postProfile = jobPost.getPostProfile();
        this.employerId = jobPost.getEmployerId().toString();
        this.reqExperience = jobPost.getReqExperience();
    }

    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getPostProfile() {
        return postProfile;
    }

    public void setPostProfile(String postProfile) {
        this.postProfile = postProfile;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public Integer getReqExperience() {
        return reqExperience;
    }

    public void setReqExperience(Integer reqExperience) {
        this.reqExperience = reqExperience;
    }
}

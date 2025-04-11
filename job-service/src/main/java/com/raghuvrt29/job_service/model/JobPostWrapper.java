package com.raghuvrt29.job_service.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class JobPostWrapper {
    private UUID postId;
    private String jobTitle;
    private String employerName;
    private List<String> locations;
    private int reqExperience;

    public JobPostWrapper(JobPost job) {
        this.postId = job.getPostId();
        this.jobTitle = job.getJobTitle();
        this.employerName = job.getEmployerName();
        this.locations = job.getLocations();
        this.reqExperience = job.getReqExperience();
    }
}

package com.raghuvrt29.application_service.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ApplicationWrapper {
    private UUID id;
    private String jobTitle;
    private String applicantName;
    private String employerName;
    private String status;

    public ApplicationWrapper(Application application) {
        this.id = application.getId();
        this.jobTitle = application.getJobTitle();
        this.applicantName = application.getApplicantName();
        this.employerName = application.getEmployerName();
        this.status = application.getStatus();
    }
}

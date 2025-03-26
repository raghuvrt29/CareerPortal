package com.raghuvrt29.application_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID jobPostId;
    private UUID applicantId;
    private String status;

    public Application(UUID id, UUID jobPostId, UUID applicantId, String status) {
        this.id = id;
        this.jobPostId = jobPostId;
        this.applicantId = applicantId;
        this.status = status;
    }

    public Application() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(UUID jobPostId) {
        this.jobPostId = jobPostId;
    }

    public UUID getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(UUID applicantId) {
        this.applicantId = applicantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

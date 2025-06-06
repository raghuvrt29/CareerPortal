package com.raghuvrt29.application_service.controller;

import com.raghuvrt29.application_service.feign.JobInterface;
import com.raghuvrt29.application_service.model.Application;
import com.raghuvrt29.application_service.model.ApplicationWrapper;
import com.raghuvrt29.application_service.model.JobPost;
import com.raghuvrt29.application_service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@EnableMethodSecurity
public class ApplicationController {
    @Autowired
    ApplicationService service;

    @Autowired
    JobInterface jobInterface;

    @PostMapping("/apply/{jobPostId}")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public String submitApplication(@PathVariable("jobId") String jobId){
        JwtAuthenticationToken authToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        JobPost job = jobInterface.getJob(jobId);
        UUID jobUUID = UUID.fromString(jobId);
        UUID applicantUUID = UUID.fromString(authToken.getName());

        Application application = new Application();
        application.setJobId(jobUUID);
        application.setJobTitle(job.getJobTitle());
        application.setApplicantId(applicantUUID);
        application.setApplicantName((String) authToken.getTokenAttributes().get("name"));
        application.setEmployerId(job.getEmployerId());
        application.setEmployerName(job.getEmployerName());
        application.setStatus("Submitted");
        application.setCrOn(new Date());

        return service.submitApplication(application);
    }

    @GetMapping("application/{applicationId}")
    @PreAuthorize("hasRole('ROLE_APPLICANT') or hasRole('ROLE_EMPLOYER')")
    public Application getApplication(@PathVariable("applicationId") String applicationId){
        UUID applicationUUID = UUID.fromString(applicationId);
        return service.getApplication(applicationUUID);
    }

    @GetMapping("/{applicantId}/applications")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public List<ApplicationWrapper> getSubmittedApplications(@PathVariable("applicantId") String applicantId){
        UUID applicantUUID = UUID.fromString(applicantId);
        List<Application> submittedApps = service.getApplicationsByApplicant(applicantUUID);
        return submittedApps.stream()
                .map(ApplicationWrapper::new)
                .toList();
    }

    @GetMapping("/post/{jobId}/applications")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public List<ApplicationWrapper> getReceivedApplications(@PathVariable("jobId") String jobId){
        UUID jobUUID = UUID.fromString(jobId);
        List<Application> receivedApps = service.getApplicationsByJobPost(jobUUID);
        return receivedApps.stream()
                .map(ApplicationWrapper::new)
                .toList();
    }
}

package com.raghuvrt29.application_service.controller;

import com.raghuvrt29.application_service.model.Application;
import com.raghuvrt29.application_service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    @Autowired
    ApplicationService service;

    @PostMapping("/apply/{jobPostId}")
    public String submitApplication(@PathVariable("jobPostId") int jobPostId, @RequestParam int applicantId){
        Application application = new Application();
        application.setApplicantId(applicantId);
        application.setJobPostId(jobPostId);
        application.setStatus("Submitted");

        return service.submitApplication(application);
    }
}

package com.raghuvrt29.application_service.service;

import com.raghuvrt29.application_service.model.Application;
import com.raghuvrt29.application_service.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepo repo;

    public String submitApplication(Application application){
        repo.save(application);
        return "Application Submitted";
    }

    public List<Application> getApplicationsByApplicant(Integer applicantId){
        return repo.findByApplicantId(applicantId);
    }

    public List<Application> getApplicationsByJobPost(Integer jobPostId){
        return repo.findByJobPostId(jobPostId);
    }

    public Application updateApplication(Application application){
        repo.save(application);
        return repo.findById(application.getId()).orElse(new Application());
    }

    public String deleteApplication(int applicationId){
        repo.deleteById(applicationId);
        return "Application deleted";
    }
}

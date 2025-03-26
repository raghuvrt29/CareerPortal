package com.raghuvrt29.application_service.service;

import com.raghuvrt29.application_service.model.Application;
import com.raghuvrt29.application_service.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepo repo;

    public String submitApplication(Application application){
        repo.save(application);
        return "Application Submitted";
    }

    public Application getApplication(UUID applicationId) {
        return repo.findById(applicationId).get();
    }

    public List<Application> getApplicationsByApplicant(UUID applicantId){
        return repo.findByApplicantId(applicantId);
    }

    public List<Application> getApplicationsByJobPost(UUID jobPostId){
        return repo.findByJobPostId(jobPostId);
    }

    public Application updateApplication(Application application){
        repo.save(application);
        return repo.findById(application.getId()).orElse(new Application());
    }

    public String deleteApplication(UUID applicationId){
        repo.deleteById(applicationId);
        return "Application deleted";
    }
}

package com.raghuvrt29.applicant_service.feign;

import com.raghuvrt29.applicant_service.model.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient
public interface ApplicationInterface {
    @GetMapping("/submittedApplications/{applicantId}")
    public List<Application> getSubmittedApplication(@PathVariable("applicantId") int applicantId);
}

package com.raghuvrt29.applicant_service.feign;

import com.raghuvrt29.applicant_service.model.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "APPLICATION-SERVICE")
public interface ApplicationInterface {
    @GetMapping("/{applicantId}/applications")
    public List<Application> getSubmittedApplication(@PathVariable("applicantId") String applicantId);
}

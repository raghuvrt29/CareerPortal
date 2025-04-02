package com.raghuvrt29.applicant_service.feign;

import com.raghuvrt29.applicant_service.model.ApplicationWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "APPLICATION-SERVICE")
public interface ApplicationInterface {
    @GetMapping("/{applicantId}/applications")
    public List<ApplicationWrapper> getSubmittedApplications(@PathVariable("applicantId") String applicantId);
}

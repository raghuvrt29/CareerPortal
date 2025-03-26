package com.raghuvrt29.employer_service.feign;

import com.raghuvrt29.employer_service.model.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("APPLICATION-SERVICE")
public interface ApplicationInterface {
    @GetMapping("/post/{jobId}/applications")
    public List<Application> getReceivedApplications(@PathVariable("jobId") String jobId);
}

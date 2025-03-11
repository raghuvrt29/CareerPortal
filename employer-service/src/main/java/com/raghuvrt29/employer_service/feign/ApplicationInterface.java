package com.raghuvrt29.employer_service.feign;

import com.raghuvrt29.employer_service.model.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("APPLICATION-SERVICE")
public interface ApplicationInterface {
    @GetMapping("/recievedApplications/{jobId}")
    public List<Application> getRecievedApplications(@PathVariable("jobId") int jobId);
}

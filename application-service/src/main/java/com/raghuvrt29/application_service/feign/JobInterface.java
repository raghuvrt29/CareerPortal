package com.raghuvrt29.application_service.feign;

import com.raghuvrt29.application_service.model.JobPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("JOB-SERVICE")
public interface JobInterface {
    @GetMapping("/post/{postId}")
    public JobPost getJob(@PathVariable("postId") String postId);

}

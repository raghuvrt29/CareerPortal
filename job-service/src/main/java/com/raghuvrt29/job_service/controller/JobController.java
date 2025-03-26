package com.raghuvrt29.job_service.controller;

import com.raghuvrt29.job_service.model.JobPost;
import com.raghuvrt29.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@EnableMethodSecurity
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/posts")
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("/post/{postId}")
    public JobPost getJob(@PathVariable("postId") String postID){
        UUID postId = UUID.fromString(postID);
        return service.getJob(postId);
    }

    @GetMapping("/posts/search/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public void addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
    }

    @PutMapping("/post")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        return service.updateJob(jobPost);
    }

    @DeleteMapping("/post/{postID}")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public String deleteJob(@PathVariable("postID") String postID){
        UUID postId = UUID.fromString(postID);
        service.deleteJob(postId);
        return "Post Deleted";
    }
}

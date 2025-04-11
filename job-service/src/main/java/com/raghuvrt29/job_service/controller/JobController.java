package com.raghuvrt29.job_service.controller;

import com.raghuvrt29.job_service.model.JobPost;
import com.raghuvrt29.job_service.model.JobPostInput;
import com.raghuvrt29.job_service.model.JobPostWrapper;
import com.raghuvrt29.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@EnableMethodSecurity
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/posts")
    public List<JobPostWrapper> getAllJobs(){
        List<JobPost> allJobs = service.getAllJobs();
        return allJobs.stream()
                .map(JobPostWrapper::new)
                .toList();
    }

    @GetMapping("/post/{postId}")
    public JobPost getJob(@PathVariable("postId") String postID){
        UUID postId = UUID.fromString(postID);
        return service.getJob(postId);
    }

    @GetMapping("/posts/search/{keyword}")
    public List<JobPostWrapper> searchByKeyword(@PathVariable("keyword") String keyword){
        List<JobPost> jobPosts = service.search(keyword);
        return jobPosts.stream()
                .map(JobPostWrapper::new)
                .toList();
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public void addJob(@RequestBody JobPostInput jobPostInput){
        try {
            JwtAuthenticationToken authToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

            JobPost jobPost = new JobPost();
            jobPost.setJobTitle(jobPostInput.getJobTitle());
            jobPost.setJobDesc(jobPostInput.getJobDesc());
            jobPost.setEmployerId(UUID.fromString(authToken.getName()));
            jobPost.setEmployerName((String) authToken.getTokenAttributes().get("name"));
            jobPost.setLocations(jobPostInput.getLocations());
            jobPost.setReqExperience(jobPostInput.getReqExperience());
            jobPost.setCrOn(new Date());
            jobPost.setDeadline(jobPostInput.getDeadline());

            service.addJob(jobPost);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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

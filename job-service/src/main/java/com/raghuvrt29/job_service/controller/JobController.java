package com.raghuvrt29.job_service.controller;

import com.raghuvrt29.job_service.model.JobPost;
import com.raghuvrt29.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/posts")
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("/post/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        return service.getJob(postId);
    }

    @GetMapping("/posts/search/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }

    @PostMapping("/post")
    public void addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
    }

    @PutMapping("/post")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        return service.updateJob(jobPost);
    }

    @DeleteMapping("/post/{postID}")
    public String deleteJob(@PathVariable("postID") int postID){
        service.deleteJob(postID);
        return "Post Deleted";
    }

    @GetMapping("/load")
    public String loadData(){
        service.loadData();
        return "Success";
    }

}

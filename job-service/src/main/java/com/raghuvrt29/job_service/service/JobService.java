package com.raghuvrt29.job_service.service;

import com.raghuvrt29.job_service.model.JobPost;
import com.raghuvrt29.job_service.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.asList;

@Service
public class JobService {

    @Autowired
    JobRepo repo;

    public void addJob(JobPost jobPost){
        repo.save(jobPost);
    }

    public List<JobPost> getAllJobs(){
        return repo.findAll();
    }

    public JobPost getJob(UUID postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public JobPost updateJob(JobPost jobPost) {
        repo.save(jobPost);
        return repo.findById(jobPost.getPostId()).orElse(new JobPost());
    }

    public void deleteJob(UUID postID) {
        repo.deleteById(postID);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }
}

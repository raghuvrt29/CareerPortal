package com.raghuvrt29.job_service.service;

import com.raghuvrt29.job_service.model.JobPost;
import com.raghuvrt29.job_service.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public JobPost getJob(Integer postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public JobPost updateJob(JobPost jobPost) {
        repo.save(jobPost);
        return repo.findById(jobPost.getPostId()).orElse(new JobPost());
    }

    public void deleteJob(int postID) {
        repo.deleteById(postID);
    }

    public void loadData() {
        List<JobPost> jobs = new ArrayList<JobPost>(asList(
                new JobPost(1,"Android Developer","Develop mobile apps",5,asList("Java","Kotlin")),
                new JobPost(2,"Backend Engineer","Develop Backend",2,asList("Java","Spring","SprinBoot")),
                new JobPost(3,"Frontend Engineer","Develop Frontend",1,asList("JavaScript","React","CSS"))
        ));
        repo.saveAll(jobs);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }
}

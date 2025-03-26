package com.raghuvrt29.job_service.repo;

import com.raghuvrt29.job_service.model.JobPost;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


@Repository
public interface JobRepo extends JpaRepository< JobPost, UUID> {

    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);

}
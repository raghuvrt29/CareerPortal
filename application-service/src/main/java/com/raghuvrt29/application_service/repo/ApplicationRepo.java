package com.raghuvrt29.application_service.repo;

import com.raghuvrt29.application_service.model.Application;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {
    List<Application> findByApplicantId(Integer aplicantId);
    List<Application> findByJobPostId(Integer jobPostId);
}

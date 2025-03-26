package com.raghuvrt29.application_service.repo;

import com.raghuvrt29.application_service.model.Application;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, UUID> {
    List<Application> findByApplicantId(UUID aplicantId);
    List<Application> findByJobPostId(UUID jobPostId);
}

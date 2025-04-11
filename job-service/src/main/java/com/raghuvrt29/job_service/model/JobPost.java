package com.raghuvrt29.job_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID jobId;
    private String jobTitle;
    private String jobDesc;
    private UUID employerId;
    private String employerName;
    private List<String> locations;
    private int reqExperience;
    private Date crOn;
    private Date deadline;
}

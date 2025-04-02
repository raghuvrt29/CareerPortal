package com.raghuvrt29.applicant_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;

@Data
@Entity
public class Education {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private String instituteName;
    @Column(nullable = false)
    private String instituteLoc;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private String fieldOfStudy;
    @Column(nullable = false)
    private Month startMonth;
    @Column(nullable = false)
    private Month endMonth;
    @Column(nullable = false)
    private Boolean isGraduated;
}

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
public class Experience {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private String employerName;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Month startMonth;
    private Month endMonth;
    @Column(nullable = false)
    private Boolean isCurrent;
}

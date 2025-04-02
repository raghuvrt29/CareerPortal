package com.raghuvrt29.employer_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Data
@Table(name="employers")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String employerName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String headOffLoc;
    @Column(nullable = false)
    private Integer noOfEmployees;
    private String about;
}

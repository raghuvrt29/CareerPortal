package com.raghuvrt29.application_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPost {
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

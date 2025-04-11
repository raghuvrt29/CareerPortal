package com.raghuvrt29.job_service.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class JobPostInput {
    private String jobTitle;
    private String jobDesc;
    private List<String> locations;
    private int reqExperience;
    private Date deadline;
}

package com.raghuvrt29.applicant_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApplicantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicantServiceApplication.class, args);
	}

}

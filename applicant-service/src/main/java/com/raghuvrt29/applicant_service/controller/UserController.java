package com.raghuvrt29.applicant_service.controller;

import com.raghuvrt29.applicant_service.feign.ApplicationInterface;
import com.raghuvrt29.applicant_service.model.Application;
import com.raghuvrt29.applicant_service.model.User;
import com.raghuvrt29.applicant_service.service.JwtService;
import com.raghuvrt29.applicant_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationInterface applicationInterface;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        service.saveUser(user);
        return "Successfully Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getId().toString());
        }
        else{
            return "Login failed";
        }
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public List<Application> myApplications(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UUID userUUID = service.getUser(username).getId();
        return applicationInterface.getSubmittedApplication(userUUID.toString());
    }
}

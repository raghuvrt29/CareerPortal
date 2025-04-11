package com.raghuvrt29.applicant_service.controller;

import com.raghuvrt29.applicant_service.feign.ApplicationInterface;
import com.raghuvrt29.applicant_service.model.ApplicationWrapper;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        try{
            User user = service.getUser(username);
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getId().toString(), password));

            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getId().toString(), user.getFirstname()+" "+user.getLastname());
            }
            else{
                return "Login failed";
            }
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public List<ApplicationWrapper> myApplications(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userUUID = authentication.getName();
            return applicationInterface.getSubmittedApplications(userUUID);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<ApplicationWrapper>();
        }
    }
}

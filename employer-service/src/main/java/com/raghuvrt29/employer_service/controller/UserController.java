package com.raghuvrt29.employer_service.controller;

import com.raghuvrt29.employer_service.feign.ApplicationInterface;
import com.raghuvrt29.employer_service.model.ApplicationWrapper;
import com.raghuvrt29.employer_service.model.User;
import com.raghuvrt29.employer_service.service.JwtService;
import com.raghuvrt29.employer_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationInterface applicationInterface;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        service.saveUser(user);
        return "Successfully Registered";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        try {
            User user = service.getUser(username);
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getId().toString(), password));

            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getId().toString());
            }
            else{
                return "Login failed";
            }
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/post/{jobId}/applications")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public List<ApplicationWrapper> getReceivedApplications(@PathVariable("jobId") String jobId){
        return applicationInterface.getReceivedApplications(jobId);
    }

}

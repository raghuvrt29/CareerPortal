package com.raghuvrt29.applicant_service.service;

import com.raghuvrt29.applicant_service.model.User;
import com.raghuvrt29.applicant_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User saveUser(User user){
        return repo.save(user);
    }

    public User getUser(String username) {
        return repo.findByUsername(username);
    }

}

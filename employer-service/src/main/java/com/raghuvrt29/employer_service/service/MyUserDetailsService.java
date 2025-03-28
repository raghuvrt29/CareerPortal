package com.raghuvrt29.employer_service.service;

import com.raghuvrt29.employer_service.model.User;
import com.raghuvrt29.employer_service.model.UserPrincipal;
import com.raghuvrt29.employer_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = repo.findById(UUID.fromString(userId));
        if(user.isEmpty()){
            System.out.println("user 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user.get());
    }
}

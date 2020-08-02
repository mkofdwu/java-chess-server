package com.example.javachessserver.auth;

import com.example.javachessserver.user.UserRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BasicDBObject query = new BasicDBObject("username", username);
        return userRepo.findOne(query).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

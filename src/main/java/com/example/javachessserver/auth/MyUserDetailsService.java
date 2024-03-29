package com.example.javachessserver.auth;

import com.example.javachessserver.auth.models.UserRegisterDetails;
import com.example.javachessserver.user.UserRepository;
import com.example.javachessserver.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        throw new UsernameNotFoundException("Could not find a user with the username: " + username);
    }

    public UserDetails loadUserByUserId(String userId) {
        return userRepo.findById(userId).get();
    }

    // FIXME: should this be placed here?
    public User createUser(UserRegisterDetails registerDetails) {
        User user = new User(registerDetails);
        userRepo.insert(user);
        return user;
    }
}

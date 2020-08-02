package com.example.javachessserver.auth;

import com.example.javachessserver.user.models.User;

import java.util.Optional;

public interface AuthService {
    Optional<String> login(String username, String password);

    Optional<User> getUser(String token);

    void logout();
}

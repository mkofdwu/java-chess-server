package com.example.javachessserver.auth;

import com.example.javachessserver.user.models.User;

import java.util.Optional;

public class TokenAuthService implements AuthService {
    @Override
    public Optional<String> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String token) {
        return Optional.empty();
    }

    @Override
    public void logout() {

    }
}

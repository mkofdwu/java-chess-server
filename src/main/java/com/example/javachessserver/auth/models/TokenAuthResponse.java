package com.example.javachessserver.auth.models;

import com.example.javachessserver.user.models.User;

public class TokenAuthResponse {
    private final String token;
    private final User user;

    public TokenAuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}

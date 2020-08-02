package com.example.javachessserver.auth.models;

public class TokenAuthResponse {
    private final String token;

    public TokenAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

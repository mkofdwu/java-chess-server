package com.example.javachessserver.game;

public class GameRequest {
    private String otherUserId;

    public GameRequest() {
    }

    public GameRequest(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }
}

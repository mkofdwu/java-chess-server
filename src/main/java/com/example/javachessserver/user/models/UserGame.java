package com.example.javachessserver.user.models;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class UserGame {
    @MongoId
    private String gameId;
    private String name;
    private Boolean side; // which side the user was playing on, 0 - black, 1 - white

    public UserGame() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSide() {
        return side;
    }

    public void setSide(Boolean side) {
        this.side = side;
    }
}

package com.example.javachessserver.models;

class UserGame {
    private String name;
    private Boolean side; // which side the user was playing on, 0 - black, 1 - white
    private String gameId;
}

public class User {
    private String _id;
    private String username;
    private String password;
    private String profilePic;
    private String bio;
    private UserGame[] pastGames;
    private UserGame[] ongoingGames;
}

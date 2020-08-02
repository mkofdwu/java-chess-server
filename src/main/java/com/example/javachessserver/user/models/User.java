package com.example.javachessserver.user.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class User {
    @MongoId
    private String _id;
    private String username;
    private String password;
    private String profilePic;
    private String bio;
    private List<UserGame> pastGames;
    private List<UserGame> ongoingGames;
    @MongoId
    private List<String> requestsSent;
    @MongoId
    private List<String> requestsReceived;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getId() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<UserGame> getPastGames() {
        return pastGames;
    }

    public void setPastGames(List<UserGame> pastGames) {
        this.pastGames = pastGames;
    }

    public List<UserGame> getOngoingGames() {
        return ongoingGames;
    }

    public void setOngoingGames(List<UserGame> ongoingGames) {
        this.ongoingGames = ongoingGames;
    }

    public List<String> getRequestsSent() {
        return requestsSent;
    }

    public void setRequestsSent(List<String> requestsSent) {
        this.requestsSent = requestsSent;
    }

    public List<String> getRequestsReceived() {
        return requestsReceived;
    }

    public void setRequestsReceived(List<String> requestsReceived) {
        this.requestsReceived = requestsReceived;
    }
}

// classes to define API request and response bodies


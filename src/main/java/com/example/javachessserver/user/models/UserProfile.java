package com.example.javachessserver.user.models;

public class UserProfile {
    private String id;
    private String username;
    private String profilePic;
    private String bio;

    public UserProfile() {
    }

    public UserProfile(User user) {
        id = user.get_id();
        username = user.getUsername();
        profilePic = user.getProfilePic();
        bio = user.getBio();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}

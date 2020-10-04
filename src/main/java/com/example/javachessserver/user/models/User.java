package com.example.javachessserver.user.models;

import com.example.javachessserver.auth.models.UserRegisterDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document
public class User implements UserDetails {
    @MongoId
    private String _id;
    private String username;
    private String password;
    private String profilePic;
    private String bio;
    private List<UserGame> pastGames;
    private List<UserGame> ongoingGames;
    private List<String> requestsSent;
    private List<String> requestsReceived;
    private UserSettings settings;

    public User() {
    }

    public User(UserRegisterDetails registerDetails) {
        _id = ObjectId.get().toString();
        username = registerDetails.getUsername();
        password = new BCryptPasswordEncoder().encode(registerDetails.getPassword());
        profilePic = "";
        bio = "";
        pastGames = new ArrayList<>();
        ongoingGames = new ArrayList<>();
        requestsSent = new ArrayList<>();
        requestsReceived = new ArrayList<>();
        settings = new UserSettings(0, 0);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof User && ((User) other).get_id().equals(_id);
    }
}

// classes to define API request and response bodies


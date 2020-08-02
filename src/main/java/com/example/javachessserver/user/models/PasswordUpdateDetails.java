package com.example.javachessserver.user.models;

public class PasswordUpdateDetails {
    private String oldPassword;
    private String newPassword;

    public PasswordUpdateDetails() {}

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

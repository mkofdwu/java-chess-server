package com.example.javachessserver.user.models;

public class UpdatePasswordDetails {
    private String oldPassword;
    private String newPassword;

    public UpdatePasswordDetails() {}

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

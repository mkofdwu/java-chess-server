package com.example.javachessserver.user.models;

public class UserSettings {
    private int theme;
    private int accent;

    public UserSettings() {}

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public int getAccent() {
        return accent;
    }

    public void setAccent(int accent) {
        this.accent = accent;
    }
}

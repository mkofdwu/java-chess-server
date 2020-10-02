package com.example.javachessserver;

import com.example.javachessserver.user.models.User;

import java.util.ArrayList;

public class Store {
    public static ArrayList<User> connectedUsers; // fixme: should this be ArrayList<String> instead?
    public static ArrayList<User> usersSearchingForGame; // fixme: ^
}

package com.example.javachessserver.socket;

import com.example.javachessserver.Store;
import com.example.javachessserver.user.models.User;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        User user = (User) ((UsernamePasswordAuthenticationToken) headers.getHeader("simpUser")).getPrincipal();
        Store.connectedUsers.add(user);
        System.out.println("User connected: " + user);
    }

    @EventListener
    public void handleSessionDisconnected(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        User user = (User) ((UsernamePasswordAuthenticationToken) headers.getHeader("simpUser")).getPrincipal();
        Store.connectedUsers.remove(user);
        Store.usersSearchingForGame.remove(user);
        System.out.println("User disconnected: " + user);
    }
}

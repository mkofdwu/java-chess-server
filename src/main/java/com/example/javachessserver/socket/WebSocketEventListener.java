package com.example.javachessserver.socket;

import com.example.javachessserver.Store;
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
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headers.getSessionId();
        String authorizationHeader = (String) headers.getHeader("Authorization");
        System.out.println(sessionId);
        System.out.println(authorizationHeader);
        // Store.connectedUsers.add(event.getUser());
        System.out.println("Session connected: " + event);
    }

    @EventListener
    private void handleSessionDisconnected(SessionDisconnectEvent event) {
        System.out.println("Session disconnected: " + event);
    }
}

package com.example.javachessserver.socket;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        System.out.println("Session connected: " + event);
    }

    @EventListener
    private void handleSessionDisconnected(SessionDisconnectEvent event) {
        System.out.println("Session disconnected: " + event);
    }
}

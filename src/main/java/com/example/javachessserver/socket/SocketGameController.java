package com.example.javachessserver.socket;

import com.example.javachessserver.Store;
import com.example.javachessserver.socket.models.Message;
import com.example.javachessserver.socket.models.Move;
import com.example.javachessserver.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SocketGameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/random")
    public void newRandomGame(@AuthenticationPrincipal User user) {
        if (Store.usersSearchingForGame.isEmpty()) {
            Store.usersSearchingForGame.add(user);
        } else {
            Random rand = new Random();
            User otherUser = Store.usersSearchingForGame.remove(rand.nextInt(Store.usersSearchingForGame.size()));
            // create match between them
            Store.connectedUsers.add(user);
            Store.connectedUsers.add(otherUser);
            simpMessagingTemplate.convertAndSend("/topic/new/" + user.getId(), otherUser.getId());
            simpMessagingTemplate.convertAndSend("/topic/new/" + otherUser.getId(), user.getId());
        }
    }

    @MessageMapping("/request/{toUserId}")
    public void newGameRequest(@AuthenticationPrincipal User user, @DestinationVariable String toUserId) {
        // TODO: connect to socket server when app starts
        // TODO: connectedUsers contains everyone, including those searching for a game
        for (User otherUser : Store.connectedUsers) {
            if (otherUser.getId().equals(toUserId)) {
                simpMessagingTemplate
            }
        }
        // TODO: send '`user` isn't online now :('
    }

    @MessageMapping("/move/{toUserId}")
    public void sendMove(@DestinationVariable String toUserId, Move move) {
        for (User otherUser : Store.connectedUsers) {
            if (otherUser.getId().equals(toUserId)) {
                simpMessagingTemplate.convertAndSend("/topic/moves/" + toUserId, move); // FUTURE: check that this move is valid
            }
        }
    }

    @MessageMapping("/message/{toUserId}")
    public void sendMessage(@DestinationVariable String toUserId, Message message) {
        for (User otherUser : Store.connectedUsers) {
            if (otherUser.getId().equals(toUserId)) {
                simpMessagingTemplate.convertAndSend("/topic/messages/" + toUserId, message);
            }
        }
    }
}

package com.example.javachessserver.socket;

import com.example.javachessserver.Store;
import com.example.javachessserver.game.GameRepository;
import com.example.javachessserver.game.OngoingGame;
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

import java.util.List;
import java.util.Random;

@RestController
public class SocketGameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private GameRepository gameRepo;

    @MessageMapping("/move/{toUserId}")
    public void sendMove(@DestinationVariable String toUserId, Move move) {
        // for now, since all processing is done on client side in terms of chess logic, the updated fen position as well as end of game flags are sent from the client. This definitely poses a huge security issue, however.
        OngoingGame game = (OngoingGame) gameRepo.findById(move.getGameId()).get();
        game.setFenPosition(move.getUpdatedFenPosition());
        game.getMoves().add(List.of(move.getFromFile(), move.getFromRank(), move.getToFile(), move.getToRank()));
        gameRepo.save(game);
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

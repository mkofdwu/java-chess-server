package com.example.javachessserver.socket;

import com.example.javachessserver.Store;
import com.example.javachessserver.game.GameRepository;
import com.example.javachessserver.game.OngoingGame;
import com.example.javachessserver.game.PastGame;
import com.example.javachessserver.game.RecordedMove;
import com.example.javachessserver.socket.models.Message;
import com.example.javachessserver.socket.models.Move;
import com.example.javachessserver.user.UserRepository;
import com.example.javachessserver.user.models.User;
import com.example.javachessserver.user.models.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocketGameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private GameRepository gameRepo;

    @MessageMapping("/move/{toUserId}")
    public void sendMove(@DestinationVariable String toUserId, Move move) {
        // for now, since all processing is done on client side in terms of chess logic, the updated fen position as well as end of game flags are sent from the client. This definitely poses a huge security issue, however.
        String gameId = move.getGameId();
        OngoingGame game = (OngoingGame) gameRepo.findById(gameId).get();
        game.setFenPosition(move.getUpdatedFenPosition());
        game.getMoves().add(new RecordedMove(move.getFromFile(), move.getFromRank(), move.getToFile(), move.getToRank(), move.getMoveType()));
        if (move.getResult() == 0) {
            gameRepo.save(game);
        } else {
            PastGame pastGame = new PastGame(game, move.getResult());
            gameRepo.save(pastGame);

            // fixme: this doesn't seem very elegant
            User otherUser = userRepo.findById(toUserId).get();
            UserGame otherUserGame = null;
            for (UserGame checkUserGame : otherUser.getOngoingGames()) {
                if (checkUserGame.getGameId().equals(gameId)) {
                    otherUserGame = checkUserGame;
                    break;
                }
            }
            otherUser.getOngoingGames().remove(otherUserGame);
            otherUser.getPastGames().add(otherUserGame);
            userRepo.save(otherUser);
            User user = userRepo.findById(!otherUserGame.getIsWhite() ? game.getWhite() : game.getBlack()).get();
            UserGame userGame = null;
            for (UserGame checkUserGame : user.getOngoingGames()) {
                if (checkUserGame.getGameId().equals(gameId)) {
                    userGame = checkUserGame;
                    break;
                }
            }
            user.getOngoingGames().remove(userGame);
            user.getPastGames().add(userGame);
            userRepo.save(user);
        }
        simpMessagingTemplate.convertAndSend("/topic/moves/" + toUserId, move); // FUTURE: check that this move is valid
    }

    @MessageMapping("/message/{toUserId}")
    public void sendMessage(@DestinationVariable String toUserId, Message message) {
        // fixme: check for resign / accept draw offer to end game
        for (User otherUser : Store.connectedUsers) {
            if (otherUser.get_id().equals(toUserId)) {
                simpMessagingTemplate.convertAndSend("/topic/messages/" + toUserId, message);
            }
        }
    }
}

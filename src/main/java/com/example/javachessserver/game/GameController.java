package com.example.javachessserver.game;

import com.example.javachessserver.Store;
import com.example.javachessserver.user.UserRepository;
import com.example.javachessserver.user.models.User;
import com.example.javachessserver.user.models.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable(value = "gameId") String gameId) {
        return gameRepo.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    @PostMapping("/random")
    public void randomGame(@AuthenticationPrincipal User user) {
        if (Store.usersSearchingForGame.isEmpty()) {
            Store.usersSearchingForGame.add(user);
        } else {
            User otherUser = Store.usersSearchingForGame.get(new Random().nextInt(Store.usersSearchingForGame.size()));
            UserGame[] userGames = createGame(user, otherUser);
            simpMessagingTemplate.convertAndSend("/topic/new-games/" + user.getId(), userGames[0]);
            simpMessagingTemplate.convertAndSend("/topic/new-games/" + otherUser.getId(), userGames[1]);
        }
    }

    @PostMapping("/request")
    public void requestGame(@AuthenticationPrincipal User user, @RequestParam("otherUserId") String otherUserId) {
        User otherUser = userRepo.findById(otherUserId).get();
        user.getRequestsSent().add(otherUserId);
        otherUser.getRequestsReceived().add(user.getId());
        // fixme: check if other user is connected?
        simpMessagingTemplate.convertAndSend("/topic/requests/" + otherUserId, user.getId());
    }

    @PostMapping("/request-response")
    public void respondToGameRequest(@AuthenticationPrincipal User user, @RequestParam("otherUserId") String otherUserId, @RequestParam("accept") boolean accept) {
        User otherUser = userRepo.findById(otherUserId).get();
        user.getRequestsReceived().remove(otherUserId);
        otherUser.getRequestsSent().remove(user.getId());
        if (accept) {
            UserGame[] userGames = createGame(user, otherUser);
            simpMessagingTemplate.convertAndSend("/topic/new-games/" + user.getId(), userGames[0]);
            simpMessagingTemplate.convertAndSend("/topic/accepted-requests/" + otherUserId, userGames[1]);
        }
    }

    private UserGame[] createGame(User user1, User user2) {
        OngoingGame game = new OngoingGame();

        // determine who's playing which side
        Random rand = new Random();
        boolean user1IsWhite = rand.nextBoolean();
        User whiteUser = user1IsWhite ? user1 : user2;
        User blackUser = user1IsWhite ? user2 : user1;

        // set game details
        game.setWhite(whiteUser.getId());
        game.setBlack(blackUser.getId());
        game.setMoves(new ArrayList<>());

        // add UserGame for each player
        String gameName = String.format("%s - %s", whiteUser.getUsername(), blackUser.getUsername());
        UserGame[] userGames = new UserGame[]{
                new UserGame(game.getId(), gameName, user1IsWhite),
                new UserGame(game.getId(), gameName, !user1IsWhite)
        };
        user1.getOngoingGames().add(userGames[0]);
        user2.getOngoingGames().add(userGames[1]);

        gameRepo.insert(game);
        userRepo.save(user1);
        userRepo.save(user2);

        return userGames;
    }
}

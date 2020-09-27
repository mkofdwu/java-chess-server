package com.example.javachessserver.game;

import com.example.javachessserver.Store;
import com.example.javachessserver.user.UserRepository;
import com.example.javachessserver.user.models.User;
import com.example.javachessserver.user.models.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/random")
    public void createRandomGame(@AuthenticationPrincipal User user) {
        Game game = new Game();
        if (Store.usersSearchingForGame.isEmpty()) {
            Store.usersSearchingForGame.add(user);
        } else {
            User otherUser = Store.usersSearchingForGame.get(new Random().nextInt(Store.usersSearchingForGame.size()));
            // TODO: send socket message to other user & start game

            // determine who's playing which side
            Random rand = new Random();
            boolean userSide = rand.nextBoolean();
            User whiteUser = userSide ? user : otherUser;
            User blackUser = userSide ? otherUser : user;

            // set game details
            game.setWhite(whiteUser.getId());
            game.setBlack(blackUser.getId());
            game.setMoves(new ArrayList<>());
            game.setBoard(new ArrayList<>());

            // add UserGame for each player
            String gameName = String.format("%s - %s", whiteUser.getUsername(), blackUser.getUsername());
            user.getOngoingGames().add(new UserGame(game.getId(), gameName, userSide));
            otherUser.getOngoingGames().add(new UserGame(game.getId(), gameName, !userSide));

            gameRepo.insert(game);
        }
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable(value = "gameId") String gameId) {
        return gameRepo.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    @PostMapping("/{gameId}/move")
    public void playMove(@PathVariable(value = "gameId") String gameId, @RequestParam(name = "move") String moveString) {
        Game game = gameRepo.findById(gameId).orElseThrow(GameNotFoundException::new);
        List<Integer> move = GameUtils.stringToMove(moveString);

        if (GameUtils.isValidMove(game.getBoard(), move)) {
            GameUtils.applyMove(game.getBoard(), move);
            gameRepo.save(game);
        } else {
            throw new InvalidMoveException();
        }
    }

}

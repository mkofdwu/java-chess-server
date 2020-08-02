package com.example.javachessserver.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The id does not match any game in the database")
class GameNotFoundException extends RuntimeException {
}

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameRepository repo;

    @PostMapping("/random")
    public void createRandomGame() {
        Game game = new Game();
        repo.insert(game);
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable(value = "gameId") String gameId) {
        return repo.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    @PostMapping("/{gameId}/move")
    public void move(@PathVariable(value = "gameId") String gameId, @RequestParam(name = "move") String move) {
        Game game = repo.findById(gameId).orElseThrow(GameNotFoundException::new);
        // TODO: verify the move is valid, then make the move
    }

}

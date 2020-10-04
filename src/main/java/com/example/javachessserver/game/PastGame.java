package com.example.javachessserver.game;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PastGame extends Game {
    private int result; // 0 - draw, 1 - white wins, 2 - black wins

    public PastGame() {
    }

    public PastGame(OngoingGame ongoingGame, int endOfGame) {
        _id = ongoingGame.getId();
        white = ongoingGame.getWhite();
        black = ongoingGame.getBlack();
        moves = ongoingGame.getMoves();
        timestamp = ongoingGame.getTimestamp();
        if (endOfGame == 1) {
            // active player wins
            result = moves.size() % 2 == 0 ? 2 : 1;
        } else if (endOfGame == 2) {
            // draw
            result = 0;
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

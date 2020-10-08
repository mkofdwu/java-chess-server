package com.example.javachessserver.game;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PastGame extends Game {
    private int result; // 0 - no result, 1 - draw, 2 - white wins, 3 - black wins

    public PastGame() {
    }

    public PastGame(OngoingGame ongoingGame, int result) {
        _id = ongoingGame.get_id();
        white = ongoingGame.getWhite();
        black = ongoingGame.getBlack();
        recordedMoves = ongoingGame.getMoves();
        timestamp = ongoingGame.getTimestamp();
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

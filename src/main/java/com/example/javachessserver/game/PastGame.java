package com.example.javachessserver.game;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PastGame extends Game {
    private int result; // 1 - white wins, -1 - black wins, 0 - draw

    public PastGame() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

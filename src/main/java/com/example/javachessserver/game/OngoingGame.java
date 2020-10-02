package com.example.javachessserver.game;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OngoingGame extends Game {
    private String fenPosition; // includes game flags

    public OngoingGame() {
    }

    public String getFenPosition() {
        return fenPosition;
    }

    public void setFenPosition(String fenPosition) {
        this.fenPosition = fenPosition;
    }
}

package com.example.javachessserver.game;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Game {
    @MongoId
    private String _id;
    @MongoId
    private String white;
    @MongoId
    private String black;
    private List<List<int>> moves; // in the format [ (file1, rank1, file2, rank2) ]

    public Game() {}

    @Override
    public String toString() {
        return "Game{" +
                "_id='" + _id + '\'' +
                ", white='" + white + '\'' +
                ", black='" + black + '\'' +
                '}';
    }

    public String getId() {
        return _id;
    }

    public String getWhite() {
        return white;
    }

    public void setWhite(String white) {
        this.white = white;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public List<List<int>> getMoves() {
        return moves;
    }

    public void setMoves(List<List<int>> moves) {
        this.moves = moves;
    }
}

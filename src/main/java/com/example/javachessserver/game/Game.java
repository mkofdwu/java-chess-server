package com.example.javachessserver.game;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Game {
    @MongoId
    private String _id;
    private String white;
    private String black;
    private List<List<Integer>> moves; // in the format [ (file1, rank1, file2, rank2) ]
    private List<List<Integer>> board;

    public Game() {
    }

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

    public List<List<Integer>> getMoves() {
        return moves;
    }

    public void setMoves(List<List<Integer>> moves) {
        this.moves = moves;
    }

    public List<List<Integer>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }
}

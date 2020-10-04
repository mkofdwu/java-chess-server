package com.example.javachessserver.socket.models;

public class Move {
    private String gameId;
    private int fromFile;
    private int fromRank;
    private int toFile;
    private int toRank;
    private String moveType;
    private int endOfGame; // 0 - nothing, 1 - win, 2 - draw
    private String updatedFenPosition; // temporary solution but may pose security concerns

    public Move() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getFromFile() {
        return fromFile;
    }

    public void setFromFile(int fromFile) {
        this.fromFile = fromFile;
    }

    public int getFromRank() {
        return fromRank;
    }

    public void setFromRank(int fromRank) {
        this.fromRank = fromRank;
    }

    public int getToFile() {
        return toFile;
    }

    public void setToFile(int toFile) {
        this.toFile = toFile;
    }

    public int getToRank() {
        return toRank;
    }

    public void setToRank(int toRank) {
        this.toRank = toRank;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public int getEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(int endOfGame) {
        this.endOfGame = endOfGame;
    }

    public String getUpdatedFenPosition() {
        return updatedFenPosition;
    }

    public void setUpdatedFenPosition(String updatedFenPosition) {
        this.updatedFenPosition = updatedFenPosition;
    }
}

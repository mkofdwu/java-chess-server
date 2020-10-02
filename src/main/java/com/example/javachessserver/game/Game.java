package com.example.javachessserver.game;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@JsonSubTypes({
        @JsonSubTypes.Type(OngoingGame.class),
        @JsonSubTypes.Type(PastGame.class)
})
@BsonDiscriminator
public abstract class Game {
    @MongoId
    protected String _id;
    protected String white;
    protected String black;
    protected List<List<Integer>> moves; // in the format [ (file1, rank1, file2, rank2) ]
    protected Date timestamp;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

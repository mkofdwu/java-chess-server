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
    protected List<RecordedMove> recordedMoves;
    protected Date timestamp;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<RecordedMove> getMoves() {
        return recordedMoves;
    }

    public void setMoves(List<RecordedMove> recordedMoves) {
        this.recordedMoves = recordedMoves;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

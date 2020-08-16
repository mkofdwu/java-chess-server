package com.example.javachessserver.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The id does not match any game in the database")
public class GameNotFoundException extends RuntimeException {
}

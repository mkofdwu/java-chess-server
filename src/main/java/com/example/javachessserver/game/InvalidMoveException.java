package com.example.javachessserver.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Invalid move")
public class InvalidMoveException extends RuntimeException {
}

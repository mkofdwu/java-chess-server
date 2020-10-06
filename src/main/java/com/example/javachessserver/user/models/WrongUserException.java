package com.example.javachessserver.user.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Wrong user")
public class WrongUserException extends RuntimeException {
}

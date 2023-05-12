package com.example.AnimeAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InformationNotAcceptedException extends RuntimeException {
    public InformationNotAcceptedException(String message) {super(message);}
}

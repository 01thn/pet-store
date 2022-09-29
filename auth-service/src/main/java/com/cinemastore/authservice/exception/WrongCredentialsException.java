package com.cinemastore.authservice.exception;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException() {
    }

    public WrongCredentialsException(String message) {
        super(message);
    }
}

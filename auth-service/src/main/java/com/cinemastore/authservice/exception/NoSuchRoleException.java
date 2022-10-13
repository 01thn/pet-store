package com.cinemastore.authservice.exception;

public class NoSuchRoleException extends Exception {
    public NoSuchRoleException() {
    }

    public NoSuchRoleException(String message) {
        super(message);
    }
}

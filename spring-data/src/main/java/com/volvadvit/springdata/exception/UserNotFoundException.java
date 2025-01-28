package com.volvadvit.springdata.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final int id) {
        super("User with id " + id + " not found");
    }
}

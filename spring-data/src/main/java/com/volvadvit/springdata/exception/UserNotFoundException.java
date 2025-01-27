package com.volvadvit.springdata.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final Long id) {
        super("User with id " + id + " not found");
    }
}

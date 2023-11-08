package org.makarov.lab7.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super(String.format("User id:%d not found", userId));
    }
}

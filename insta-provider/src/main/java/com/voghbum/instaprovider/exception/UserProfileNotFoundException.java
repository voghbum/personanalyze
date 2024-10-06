package com.voghbum.instaprovider.exception;

public class UserProfileNotFoundException extends GeneralIPException {
    public UserProfileNotFoundException(String username) {
        super(username);
    }

    public UserProfileNotFoundException(String username, String message) {
        super(username, message);
    }

    public UserProfileNotFoundException(String username, String message, Throwable cause) {
        super(username, message, cause);
    }
}

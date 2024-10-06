package com.voghbum.instaprovider.exception;

public class ParseDataException extends GeneralIPException {

    public ParseDataException(String username) {
        super(username);
    }

    public ParseDataException(String username, String message) {
        super(username, message);
    }

    public ParseDataException(String username, String message, Throwable cause) {
        super(username, message, cause);
    }

    public ParseDataException(String username, Throwable cause) {
        super(username, cause);
    }
}

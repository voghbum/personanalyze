package com.voghbum.instaprovider.exception;

public class GeneralIPException extends Exception {
    public GeneralIPException() {
        super();
    }

    public GeneralIPException(String message) {
        super(message);
    }

    public GeneralIPException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralIPException(Throwable cause) {
        super(cause);
    }
}

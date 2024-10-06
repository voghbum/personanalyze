package com.voghbum.instaprovider.exception;

import java.io.IOException;

public class GeneralIPException extends IOException {
    private final String problematicUsername;

    public GeneralIPException(String username) {
        super();
        this.problematicUsername = username;
    }

    public GeneralIPException(String username, String message) {
        super(message);
        this.problematicUsername = username;
    }

    public GeneralIPException(String username, String message, Throwable cause) {
        super(message, cause);
        this.problematicUsername = username;
    }

    public GeneralIPException(String username, Throwable cause) {
        super(cause);
        this.problematicUsername = username;
    }
}

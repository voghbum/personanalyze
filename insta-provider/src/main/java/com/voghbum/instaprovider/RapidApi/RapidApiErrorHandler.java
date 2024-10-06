package com.voghbum.instaprovider.RapidApi;

import com.voghbum.instaprovider.exception.GeneralIPException;
import com.voghbum.instaprovider.exception.UserProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RapidApiErrorHandler implements ResponseErrorHandler {
    private final String username;

    public RapidApiErrorHandler(String username) {
        this.username = username;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        var statusCode = response.getStatusCode();
        if(statusCode.is5xxServerError()) {
            throw new GeneralIPException(username, String.format("Server error while fetching instagram profile: %s", statusCode));
        } else if(statusCode.is4xxClientError()) {
            if(statusCode.equals(HttpStatus.NOT_FOUND)) {
                throw new UserProfileNotFoundException(username, String.format("Instagram profile not found: %s", statusCode));
            }
            throw new GeneralIPException(username, String.format("Client error while fetching instagram profile: %s", statusCode));
        }
    }
}

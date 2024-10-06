package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
import com.voghbum.application.service.AnalyzeService;
import com.voghbum.application.service.AiService;
import com.voghbum.instaprovider.data.UserFeed;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import com.voghbum.instaprovider.exception.GeneralIPException;
import com.voghbum.instaprovider.exception.UserProfileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;

@RestController
@Scope("prototype")
@RequestMapping("/api/user_information")
public class UserInformationController {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeAiController.class);
    private final AnalyzeService analyzeService;

    public UserInformationController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @PostMapping("/profile_info")
    public ResponseEntity<UserProfile> userProfile(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("user info requested: " + username);
        var response = analyzeService.getUserProfile(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user_feed")
    public ResponseEntity<UserFeed> userFeed(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("user feed requested: " + username);
        var response = analyzeService.getUserFeed(username, 10);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user_stories")
    public ResponseEntity<UserStories> userStories(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("user stories requested: " + username);
        var response = analyzeService.getUserStories(username);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(UserProfileNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

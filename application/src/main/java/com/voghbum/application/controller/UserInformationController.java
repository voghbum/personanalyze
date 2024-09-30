package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
import com.voghbum.application.service.AnalyzeService;
import com.voghbum.application.service.AiService;
import com.voghbum.instaprovider.data.UserFeed;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserProfile> userProfile(@RequestBody UserRequest request) {
        String username = request.getUsername();
        LOG.info("user info requested: " + username);
        try {
            var response = analyzeService.getUserInfo(username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error getting user info", e);
            return ResponseEntity.internalServerError().body(new UserProfile());
        }
    }

    @PostMapping("/user_feed")
    public ResponseEntity<UserFeed> userFeed(@RequestBody UserRequest request) {
        String username = request.getUsername();
        LOG.info("user feed requested: " + username);
        try {
            var response = analyzeService.getUserFeed(username, 10);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error fetching user feed!", e);
            return ResponseEntity.internalServerError().body(new UserFeed());
        }
    }

    @PostMapping("/user_stories")
    public ResponseEntity<UserStories> userStories(@RequestBody UserRequest request) {
        String username = request.getUsername();
        LOG.info("user stories requested: " + username);
        try {
            var response = analyzeService.getUserStories(username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error fetching user stories!", e);
            return ResponseEntity.internalServerError().body(new UserStories());
        }
    }
}

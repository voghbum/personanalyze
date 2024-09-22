package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
import com.voghbum.application.data.response.AnalyzeResponse;
import com.voghbum.application.data.response.RoastResponse;
import com.voghbum.application.service.AnalyzeService;
import com.voghbum.application.service.RoastService;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/*
fetchUserFeed için -> "/api/user_feed"
fetchUserInfo için -> "/api/user_info"
fetchUserStories için -> "/api/user_stories"
 */
@RestController
@Scope("prototype")
@RequestMapping("/api")
public class AnalyzeController {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeController.class);
    private final AnalyzeService analyzeService;
    private final RoastService roastService;

    public AnalyzeController(AnalyzeService analyzeService, RoastService roastService) {
        this.analyzeService = analyzeService;
        this.roastService = roastService;
    }

    @PostMapping("/user_info")
    public ResponseEntity<UserProfile> userProfile(@RequestBody UserRequest request) {
        // username ile işlemleri burada yapın
        String username = request.getUsername();
        var response = analyzeService.getUserInfo(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user_feed")
    public ResponseEntity<List<UserPosts.Item>> userFeed(@RequestBody UserRequest request) {
        // username ile işlemleri burada yapın
        String username = request.getUsername();
        var response = analyzeService.getUserFeed(username);
        // İşlenmiş veriyi JSON formatında geri gönder
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user_stories")
    public ResponseEntity<UserStories> userStories(@RequestBody UserRequest request) {
        // username ile işlemleri burada yapın
        String username = request.getUsername();
        var response = analyzeService.getUserStories(username);
        // İşlenmiş veriyi JSON formatında geri gönder
        return ResponseEntity.ok(response);
    }

    @PostMapping("/roast")
    public ResponseEntity<RoastResponse> process(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        var result = roastService.roast(username);
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }
}

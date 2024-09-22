package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
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
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<UserPosts.Item>> userFeed(@RequestBody UserRequest request) {
        String username = request.getUsername();
        LOG.info("user feed requested: " + username);
        try {
            var response = analyzeService.getUserFeed(username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error fetching user feed!", e);
            return ResponseEntity.internalServerError().body(new ArrayList<>());
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

    @PostMapping("/roast")
    public ResponseEntity<RoastResponse> process(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
        //var result = roastService.roast(username);
        var result = new RoastResponse();
        result.setAiResult("Arkadaşın, sosyal medya için bir stilde takılamayan ve modaya uygun kıyafetleri asla bulamayan tiplerden biri. İkinci fotoğraftaki gömlek, sanki bir paletin içine düşüp çıkmış gibi, ama herhalde onun için bu, \"sanat eseri\" sayılır. İlk fotoğraftaki ciddi ifadesiyle aslında içten içe gülmekten başka bir şey yapmadığını düşünüyorum. Üçüncü fotoğraftaki karizmatik havası ise sadece güneş gözlüklerine dayanıyor; yoksa Amsterdam'dan dönerken kaybettiği tarzını bulmaktan aciz kalmış durumda. Onun bu özgüveni ve absürt moda anlayışı, bizim için bir komedi kaynağı!\n" + "\n");
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }
}

package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
import com.voghbum.application.data.response.*;
import com.voghbum.application.service.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Scope("prototype")
@RequestMapping("/api/ai")
public class AnalyzeAiController {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeAiController.class);
    private final AiService aiService;

    public AnalyzeAiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/personal_life")
    public ResponseEntity<PersonalLifeAnalyzeResponse> personalLife(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
        var result = aiService.getPersonalInformation(username);
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/love_life")
    public ResponseEntity<LoveLifeResponse> loveLife(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("love_life requested: " + username);
        var result = aiService.loveLife(username);
        LOG.info("love_life result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change_millionaire")
    public ResponseEntity<ChangeMillionaireResponse> changeOfMillionaire(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        Thread.sleep(2000);
        LOG.info("change_millionaire requested: " + username);
        var result = aiService.millionaireChange(username);
        LOG.info("change_millionaire result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/similar_celeb")
    public ResponseEntity<SimilarCelebResponse> similarCeleb(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("similar_celeb requested: " + username);
        var result = aiService.similarCeleb(username);
        LOG.info("similar_celeb result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/strength_and_weaknesses")
    public ResponseEntity<StrengthAndWeaknessResponse> strengthAndWeaknesses(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("strength_and_weaknesses requested: " + username);
        var result = aiService.strengthAndWeakness(username);
        LOG.info("strength_and_weaknesses result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/analyze_for_private")
    public ResponseEntity<PersonalLifeAnalyzeResponse> analyzeForPrivateUser(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("analuze_for_private requested: " + username);
        var result = aiService.analyzeForPrivate(username);
        LOG.info("analuze_for_private result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }
}

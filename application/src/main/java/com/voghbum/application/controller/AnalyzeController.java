package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
import com.voghbum.application.data.response.AnalyzeResponse;
import com.voghbum.application.data.response.RoastResponse;
import com.voghbum.application.service.AnalyzeService;
import com.voghbum.application.service.RoastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping("/analyze")
    public ResponseEntity<AnalyzeResponse> analyze(@RequestBody UserRequest request) {
        // username ile işlemleri burada yapın
        String username = request.getUsername();

        var response = analyzeService.analyze(username);

        // İşlenmiş veriyi JSON formatında geri gönder
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roast")
    public ResponseEntity<RoastResponse> process(@RequestParam("username") String username) throws IOException, InterruptedException {
        var result = roastService.roast(username);
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }
}

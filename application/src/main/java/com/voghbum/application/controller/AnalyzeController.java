package com.voghbum.application.controller;

import com.voghbum.application.controller.request.UserRequest;
import com.voghbum.application.data.dto.AnalyzeResponse;
import com.voghbum.application.service.AnalyzeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Scope("prototype")
@RequestMapping("/")
public class AnalyzeController {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);
    private final AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @PostMapping("api/analyze/")
    public ResponseEntity<AnalyzeResponse> analyze(@RequestBody UserRequest request) {
        // username ile işlemleri burada yapın
        String username = request.getUsername();

        var response = analyzeService.analyze(username);

        // İşlenmiş veriyi JSON formatında geri gönder
        return ResponseEntity.ok(response);
    }
}

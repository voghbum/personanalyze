package com.voghbum.application.controller;

import com.voghbum.application.controller.request.UserRequest;
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
    public ResponseEntity<String> analyze(@RequestBody UserRequest request) {
        // username ile işlemleri burada yapın
        String username = request.getUsername();

        // Örnek bir işlem: Username'i büyük harfe çevirme
        String response = "Username in uppercase: " + username.toUpperCase();

        // İşlenmiş veriyi JSON formatında geri gönder
        return ResponseEntity.ok(response);
    }
}

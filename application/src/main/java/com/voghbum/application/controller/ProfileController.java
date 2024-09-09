package com.voghbum.application.controller;

import com.voghbum.application.service.RoastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("v1/api")
public class ProfileController {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);
    private final RoastService roastService;

    public ProfileController(RoastService roastService) {
        this.roastService = roastService;
    }

    @GetMapping("/roast")
    public ResponseEntity<Map<String, Object>> process(@RequestParam("username") String username) throws IOException, InterruptedException {
        return ResponseEntity.ok(roastService.roast(username));
    }
}

package com.voghbum.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageProxyController {

    @GetMapping("/proxy-image")
    public ResponseEntity<byte[]> proxyImage(@RequestParam String url) {
        RestTemplate restTemplate = new RestTemplate();
        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/jpeg");
        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}
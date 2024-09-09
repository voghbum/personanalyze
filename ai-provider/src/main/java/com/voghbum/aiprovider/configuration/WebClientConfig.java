package com.voghbum.aiprovider.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration("AiWebClientConfig")
public class WebClientConfig {
    @Value("${api.key.openai}")
    private String API_KEY;

    @Bean(name = "aiWebClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", API_KEY)
                .build();
    }
}
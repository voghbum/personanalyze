package com.voghbum.aiprovider.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration("AiRestClientConfig")
class RestClientConfig {
    @Value("${api.key.openai}")
    private String API_KEY;

    @Bean(name = "aiRestClient")
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", API_KEY)
                .build();
    }
}
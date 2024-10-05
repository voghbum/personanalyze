package com.voghbum.instaprovider.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration("instaRestClientConfig")
public class RestClientConfig {
    @Value("${api.key.rapidapi}")
    private String API_KEY;

    @Bean(name = "instaRestClient")
    public RestClient restClient() {
        return RestClient.builder()
            .baseUrl("https://instagram-scraper-api2.p.rapidapi.com")
            .defaultHeader("x-rapidapi-key", API_KEY)
            .defaultHeader("x-rapidapi-host", "instagram-scraper-api2.p.rapidapi.com")
            .build();
    }
}
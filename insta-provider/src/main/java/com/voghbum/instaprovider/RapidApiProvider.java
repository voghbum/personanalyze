package com.voghbum.instaprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.ProfileInfoData;
import com.voghbum.instaprovider.data.ProfilePostsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RapidApiProvider implements InstaProvider {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public RapidApiProvider(@Qualifier("instaWebClient") WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public ProfileInfoData getProfileInfo(String username) {
        return webClient.get()
                .uri("/v1/info?username_or_id_or_url={username}", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseProfileInfoData).block();
    }

    @Override
    public ProfilePostsData getProfilePosts(String username) {
        return webClient.get()
                .uri("/v1.2/posts?username_or_id_or_url={username}", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseProfilePostsData).block();
    }

    private ProfileInfoData parseProfileInfoData(String responseBody) {
        try {
            String dataJson = objectMapper.readTree(responseBody).get("data").toString();
            return objectMapper.readValue(dataJson, ProfileInfoData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ProfileInfoData", e);
        }
    }

    private ProfilePostsData parseProfilePostsData(String responseBody) {
        try {
            String dataJson = objectMapper.readTree(responseBody).get("data").toString();
            return objectMapper.readValue(dataJson, ProfilePostsData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ProfilePostsData", e);
        }
    }
}

package com.voghbum.instaprovider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserPosts;

import java.util.List;

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
    public UserProfile getProfileInfo(String username) {
        return webClient.get()
                .uri("/v1/info?username_or_id_or_url={username}", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseProfileInfoData).block();
    }

    @Override
    public UserPosts getProfilePosts(String username) {
        return webClient.get()
                .uri("/v1.2/posts?username_or_id_or_url={username}", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseProfilePostsData).block();
    }

    private UserProfile parseProfileInfoData(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.get("data");
            return objectMapper.treeToValue(dataNode, UserProfile.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ProfileInfoData", e);
        }
    }

private UserPosts parseProfilePostsData(String responseBody) {
    try {
        JsonNode rootNode = objectMapper.readTree(responseBody);
        JsonNode dataNode = rootNode.get("data");

        // UserPosts nesnesini oluştur ve JSON verisinden gerekli bilgileri al
        UserPosts userPosts = new UserPosts();
        UserPosts.Data data = new UserPosts.Data();
        data.setCount(dataNode.get("count").asInt());
        data.setItems(objectMapper.convertValue(dataNode.get("items"), new TypeReference<List<UserPosts.Item>>() {}));
        userPosts.setData(data);
        return userPosts;
    } catch (Exception e) {
        throw new RuntimeException("Failed to parse ProfilePostsData", e);
    }
}
}

package com.voghbum.instaprovider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserPosts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.voghbum.instaprovider.data.UserStories;
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
    public UserProfile getUserInfo(String username) {
        return webClient.get()
                .uri("/v1/info?username_or_id_or_url={username}&include_about=true", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseUserInfo).block();
    }

    @Override
    public UserPosts getUserPosts(String username) {
        return webClient.get()
                .uri("/v1.2/posts?username_or_id_or_url={username}", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseUserPosts).block();
    }

    @Override
    public UserStories getUserStories(String username) throws IOException, InterruptedException {
        return webClient.get()
                .uri("v1/highlights?username_or_id_or_url={username}", username)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseUserStories).block();
    }

    /*
    highlighted_stories = []
    for item in highlight_data["data"]["items"]:
        simplified_item = {
            "title": item["title"],
            "cover_image_url": item["cover_media"]["cropped_image_version"]["url"]
        }
        highlighted_stories.append(simplified_item)

    return highlighted_stories
     */
    private UserStories parseUserStories(String responseBody) {
        UserStories result = new UserStories();
        List<UserStories.Story> stories = new ArrayList<>();
        result.setHighlightedStories(stories);
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode itemsNode = rootNode.get("data").get("items");

            for(JsonNode item : itemsNode) {
                UserStories.Story story = new UserStories.Story();
                story.setTitle(item.get("title").asText());
                story.setCoverImageUrl(item.get("cover_media").get("cropped_image_version").get("url").asText());
                stories.add(story);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ProfileInfoData", e);
        }
    }


    private UserProfile parseUserInfo(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.get("data");
            return objectMapper.treeToValue(dataNode, UserProfile.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ProfileInfoData", e);
        }
    }

    private UserPosts parseUserPosts(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.get("data");
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

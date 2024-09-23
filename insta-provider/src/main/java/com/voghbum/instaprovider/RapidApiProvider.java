package com.voghbum.instaprovider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserFeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.voghbum.instaprovider.data.UserStories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
    public UserFeed getUserPosts(String username) {
        try {
            return webClient.get()
                    .uri("/v1.2/posts?username_or_id_or_url={username}", username)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(this::parseUserPosts).block();
        } catch (WebClientResponseException.Forbidden forbidden) {
            var resultForPrivate = new UserFeed();
            resultForPrivate.setUserPosts(new ArrayList<>());
            return resultForPrivate;
        }
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

    private UserFeed parseUserPosts(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode itemsNode = rootNode.get("data").get("items");
            UserFeed userFeed = new UserFeed();
            ArrayList<UserFeed.UserPost> userPosts = new ArrayList<>();
            userFeed.setUserPosts(userPosts);
            userFeed.setPaginationToken(rootNode.get("pagination_token").asText());

            for(JsonNode item : itemsNode) {
                UserFeed.UserPost userPost = new UserFeed.UserPost();
                userPost.setCaption(item.get("caption").asText());
                userPost.setLikeCount(item.get("like_count").asInt());
                userPost.setCommentCount(item.get("comment_count").asInt());
                userPost.setTakenAt(item.get("taken_at").asText());
                userPost.setId(item.get("id").asInt());
                userPost.setThumbnailUrl(item.get("thumbnail_url").asText());
                userPosts.add(userPost);
            }
            return userFeed;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to parse ProfilePostsData", e);
        }
    }
}

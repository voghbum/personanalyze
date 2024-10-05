package com.voghbum.instaprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserFeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.voghbum.instaprovider.data.UserStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;

@Service
public class RapidApiProvider implements InstaProvider {
    private static final Logger LOG = LoggerFactory.getLogger(RapidApiProvider.class);
    private final RestClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public RapidApiProvider(@Qualifier("instaRestClient") RestClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserProfile getUserInfo(String username) {
        String result;
        try {
            result = webClient.get()
                    .uri("/v1/info?username_or_id_or_url={username}&include_about=true", username)
                    .retrieve().body(String.class);
            return parseUserInfo(result);
        } catch (Exception e) {
            LOG.error("error: ", e);
            throw e;
        }
    }

    @Override
    public UserFeed getUserPosts(String username, int iterationNum) {
        try {
            String ffs = webClient.get()
                    .uri("/v1.2/posts?username_or_id_or_url={username}", username)
                    .retrieve().body(String.class);
            var firstFetchUF = parseUserPosts(ffs);

            var totalPosts = firstFetchUF.getUserPosts();
            int iteration = 1;
            var page_token = firstFetchUF.getPaginationToken();
            UserFeed iterationUF = firstFetchUF;

            while((iteration < iterationNum) && !page_token.equals("null")) {
                String ifs = ffs;
                ifs = webClient.get()
                        .uri("/v1.2/posts?username_or_id_or_url={username}&pagination_token={page_token}", username, page_token)
                        .retrieve()
                        .body(String.class);
                iterationUF = parseUserPosts(ifs);
                totalPosts.addAll(iterationUF.getUserPosts());
                page_token = iterationUF.getPaginationToken();
                iteration++;
            }

            iterationUF.setUserPosts(totalPosts);
            return iterationUF;
        } catch (HttpStatusCodeException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                var resultForPrivate = new UserFeed();
                resultForPrivate.setUserPosts(new ArrayList<>());
                return resultForPrivate;
            }
            throw e;
        }
    }

    @Override
    public UserStories getUserStories(String username) throws IOException, InterruptedException {
        var result = webClient.get()
                .uri("v1/highlights?username_or_id_or_url={username}", username)
                .retrieve()
                .body(String.class);
        return parseUserStories(result);
    }


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
            if(rootNode.get("pagination_token") != null)
                userFeed.setPaginationToken(rootNode.get("pagination_token").asText());

            for(JsonNode item : itemsNode) {
                UserFeed.UserPost userPost = new UserFeed.UserPost();
                userPost.setCaption(Optional.ofNullable(item.get("caption").get("text")).map(JsonNode::asText).orElse(""));
                userPost.setLikeCount(Optional.ofNullable(item.get("like_count")).map(JsonNode::asInt).orElse(0));
                userPost.setCommentCount(Optional.ofNullable(item.get("comment_count")).map(JsonNode::asInt).orElse(0));
                userPost.setTakenAt(Optional.ofNullable(item.get("taken_at")).map(JsonNode::asText).orElse(""));
                userPost.setId(Optional.ofNullable(item.get("id")).map(JsonNode::asInt).orElse(0));
                userPost.setThumbnailUrl(Optional.ofNullable(item.get("thumbnail_url")).map(JsonNode::asText).orElse(""));
                userPosts.add(userPost);
            }
            return userFeed;
        } catch (Throwable e) {
            throw new RuntimeException("Failed to parse ProfilePostsData", e);
        }
    }
}

package com.voghbum.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AnalyzeService {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeService.class);
    private final InstaProvider instaProvider;
    private final AiProvider aiProvider;
    private final ObjectMapper objectMapper;

    public AnalyzeService(InstaProvider instaProvider, AiProvider aiProvider) {
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
        this.objectMapper = new ObjectMapper();
    }

    public UserProfile getUserInfo(String username) {
        try {
            var result = instaProvider.getUserInfo(username);
            logFetchedData(result);
            return result;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserPosts.Item> getUserFeed(String username) {
        try {
            var userPosts = instaProvider.getUserPosts(username);
            List<UserPosts.Item> result = userPosts.getData().getItems();
            logFetchedData(result);
            return result;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UserStories getUserStories(String username) {
        try {
            var result = instaProvider.getUserStories(username);
            logFetchedData(result);
            return result;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void logFetchedData(Object data) {
        String resultJson = null;
        try {
            resultJson = objectMapper.writeValueAsString(data);
            LOG.info("fetched data ({}): {}", data.getClass().getSimpleName(), resultJson);
        } catch (JsonProcessingException e) {
            LOG.warn("fetched data cannot deserialize due to error:", e);
        }
    }
}

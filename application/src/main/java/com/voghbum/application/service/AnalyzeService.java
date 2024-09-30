package com.voghbum.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.application.data.dal.DataAccessLayer;
import com.voghbum.instaprovider.data.UserFeed;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnalyzeService {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeService.class);
    private final DataAccessLayer dal;
    private final ObjectMapper objectMapper;

    public AnalyzeService(DataAccessLayer dal) {
        this.objectMapper = new ObjectMapper();
        this.dal = dal;
    }

    public UserProfile getUserInfo(String username) throws IOException, InterruptedException {
        var result = dal.getUserProfile(username);
        logFetchedData(result);
        return result;
    }

    public UserFeed getUserFeed(String username, int iterationCount) throws IOException, InterruptedException {
        UserFeed result = dal.getUserFeed(username, iterationCount);
        logFetchedData(result);
        return result;
    }

    public UserStories getUserStories(String username) throws IOException, InterruptedException {
        var result = dal.getUserStories(username);
        logFetchedData(result);
        return result;
    }

    private void logFetchedData(Object data) {
        if(data == null) {
            LOG.warn("data is null!");
            return;
        }
        String resultJson = null;
        try {
            resultJson = objectMapper.writeValueAsString(data);
            LOG.info("fetched data ({}): {}", data.getClass().getSimpleName(), resultJson);
        } catch (JsonProcessingException e) {
            LOG.warn("fetched data cannot deserialize due to error:", e);
        }
    }
}

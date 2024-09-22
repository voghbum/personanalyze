package com.voghbum.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.application.data.entity.ProfileInformation;
import com.voghbum.application.data.repository.ProfileInformationRepository;
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
    private final ProfileInformationRepository pir;
    private final ObjectMapper objectMapper;

    public AnalyzeService(InstaProvider instaProvider, AiProvider aiProvider, ProfileInformationRepository repository) {
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
        this.objectMapper = new ObjectMapper();
        this.pir = repository;
    }

    public UserProfile getUserInfo(String username) throws IOException, InterruptedException {
        boolean profileExistsInDb = pir.existsByNickName(username);
        UserProfile result;
        if(profileExistsInDb) {
            var fromDb = pir.findByNickName(username);
            try {
                result = fromDb.getUserProfile();
            } catch (JsonProcessingException e) {
                result = instaProvider.getUserInfo(username);
                var persisting = new ProfileInformation();
                persisting.setUserProfile(result);
                persisting.setNickName(username);
                persisting.setId(fromDb.getId());
                // todo: Burada, sadece ilgili column'u update edecek şekilde yapmalıyız!
                pir.createOrUpdate(persisting);
            }
        } else {
            result = instaProvider.getUserInfo(username);
            var persisting = new ProfileInformation();
            persisting.setUserProfile(result);
            persisting.setNickName(username);
            pir.save(persisting);
        }
        return result;
    }

    public List<UserPosts.Item> getUserFeed(String username) throws IOException, InterruptedException {
        boolean profileExistsInDb = pir.existsByNickName(username);
        List<UserPosts.Item> result;
        if(profileExistsInDb) {
            var fromDb = pir.findByNickName(username);
            try {
                result = fromDb.getUserPosts();
            } catch (JsonProcessingException e) {
                result = instaProvider.getUserPosts(username).getData().getItems();
                var persisting = new ProfileInformation();
                persisting.setUserPosts(result);
                persisting.setNickName(username);
                persisting.setId(fromDb.getId());
                // todo: Burada, sadece ilgili column'u update edecek şekilde yapmalıyız!
                pir.createOrUpdate(persisting);
            }
        } else {
            result = instaProvider.getUserPosts(username).getData().getItems();
            var persisting = new ProfileInformation();
            persisting.setUserPosts(result);
            persisting.setNickName(username);
            pir.save(persisting);
        }
        return result;
    }

    public UserStories getUserStories(String username) throws IOException, InterruptedException {
        boolean profileExistsInDb = pir.existsByNickName(username);
        UserStories result;
        if(profileExistsInDb) {
            var fromDb = pir.findByNickName(username);
            try {
                result = fromDb.getUserStories();
            } catch (JsonProcessingException e) {
                result = instaProvider.getUserStories(username);
                var persisting = new ProfileInformation();
                persisting.setUserStories(result);
                persisting.setNickName(username);
                persisting.setId(fromDb.getId());
                // todo: Burada, sadece ilgili column'u update edecek şekilde yapmalıyız!
                pir.createOrUpdate(persisting);
            }
        } else {
            result = instaProvider.getUserStories(username);
            var persisting = new ProfileInformation();
            persisting.setUserStories(result);
            persisting.setNickName(username);
            pir.save(persisting);
        }
        return result;
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

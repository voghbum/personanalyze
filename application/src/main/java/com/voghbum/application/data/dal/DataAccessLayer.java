package com.voghbum.application.data.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.application.data.entity.UserFeedEntity;
import com.voghbum.application.data.entity.UserProfileEntity;
import com.voghbum.application.data.entity.UserStoriesEntity;
import com.voghbum.application.data.repository.UserFeedRepository;
import com.voghbum.application.data.repository.UserProfileRepository;
import com.voghbum.application.data.repository.UserStoriesRepository;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.UserFeed;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DataAccessLayer {
    private final UserProfileRepository userProfileRepository;
    private final UserStoriesRepository userStoriesRepository;
    private final InstaProvider instaProvider;
    private final AiProvider aiProvider;
    private final UserFeedRepository userFeedRepository;

    public DataAccessLayer(UserProfileRepository userProfileRepository,
                           UserStoriesRepository userStoriesRepository,
                           InstaProvider instaProvider, AiProvider aiProvider, UserFeedRepository userFeedRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userStoriesRepository = userStoriesRepository;
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
        this.userFeedRepository = userFeedRepository;
    }

    public UserProfile getUserProfile(String nickName) throws IOException, InterruptedException {
        Optional<UserProfileEntity> fromDb = userProfileRepository.findByNickName(nickName);
        if(fromDb.isEmpty()) {
            UserProfile fromApi = instaProvider.getUserInfo(nickName);
            UserProfileEntity persisting = new UserProfileEntity();
            persisting.setNickName(nickName);
            persisting.setUserProfile(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userProfileRepository.save(persisting);
            return fromApi;
        }

        try {
            return fromDb.get().getUserProfile();
        } catch (JsonProcessingException e) {
            UserProfileEntity updating = fromDb.get();
            UserProfile fromApi = instaProvider.getUserInfo(nickName);
            updating.setUserProfile(fromApi);
            updating.setLastUpdateTime(LocalDateTime.now());
            userProfileRepository.save(updating);
            return fromApi;
        }
    }

    public UserFeed getUserFeed(String nickName) throws IOException, InterruptedException {
        Optional<UserFeedEntity> fromDb = userFeedRepository.findByNickName(nickName);
        if(fromDb.isEmpty()) {
            UserFeed fromApi = instaProvider.getUserPosts(nickName);
            UserFeedEntity persisting = new UserFeedEntity();
            persisting.setNickName(nickName);
            persisting.setUserFeed(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userFeedRepository.save(persisting);
            return fromApi;
        }

        try {
            return fromDb.get().getUserFeed();
        } catch (JsonProcessingException e) {
            UserFeedEntity updating = fromDb.get();
            UserFeed fromApi = instaProvider.getUserPosts(nickName);
            updating.setUserFeed(fromApi);
            updating.setLastUpdateTime(LocalDateTime.now());
            userFeedRepository.save(updating);
            return fromApi;
        }
    }

    public UserStories getUserStories(String nickName) throws IOException, InterruptedException {
        Optional<UserStoriesEntity> fromDb = userStoriesRepository.findByNickName(nickName);
        if(fromDb.isEmpty()) {
            UserStories fromApi = instaProvider.getUserStories(nickName);
            UserStoriesEntity persisting = new UserStoriesEntity();
            persisting.setNickName(nickName);
            persisting.setUserStories(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userStoriesRepository.save(persisting);
            return fromApi;
        }

        try {
            return fromDb.get().getUserStories();
        } catch (JsonProcessingException e) {
            UserStoriesEntity updating = fromDb.get();
            UserStories fromApi = instaProvider.getUserStories(nickName);
            updating.setUserStories(fromApi);
            updating.setLastUpdateTime(LocalDateTime.now());
            userStoriesRepository.save(updating);
            return fromApi;
        }
    }



}

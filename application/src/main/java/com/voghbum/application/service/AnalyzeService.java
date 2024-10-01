package com.voghbum.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnalyzeService {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeService.class);
    private final UserProfileRepository userProfileRepository;
    private final UserStoriesRepository userStoriesRepository;
    private final UserFeedRepository userFeedRepository;
    private final InstaProvider instaProvider;
    private final ObjectMapper objectMapper;

    public AnalyzeService(InstaProvider instaProvider, UserFeedRepository userFeedRepository,
                          UserStoriesRepository userStoriesRepository, UserProfileRepository userProfileRepository) {
        this.instaProvider = instaProvider;
        this.userFeedRepository = userFeedRepository;
        this.userStoriesRepository = userStoriesRepository;
        this.userProfileRepository = userProfileRepository;
        this.objectMapper = new ObjectMapper();
    }

    public UserProfile getUserProfile(String nickName) throws IOException, InterruptedException {
        Optional<UserProfileEntity> fromDb = userProfileRepository.findByNickName(nickName);

        UserProfile upFromDb = getUPIfIsSufficient(nickName, fromDb);
        if(upFromDb != null) {
            return upFromDb;
        }

        Object lock = LockMapsForAnalyzeService.LOCK_FOR_USER_PROFILE.computeIfAbsent(nickName, k -> new Object());
        synchronized (lock) {
            fromDb = userProfileRepository.findByNickName(nickName);

            upFromDb = getUPIfIsSufficient(nickName, fromDb);
            if(upFromDb != null) {
                return upFromDb;
            }

            UserProfile fromApi = instaProvider.getUserInfo(nickName);
            UserProfileEntity persisting = new UserProfileEntity();
            persisting.setNickName(nickName);
            persisting.setUserProfile(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userProfileRepository.save(persisting);

            LockMapsForAnalyzeService.LOCK_FOR_USER_PROFILE.remove(nickName);
            return fromApi;
        }
    }

    public UserFeed getUserFeed(String nickName, int iterationCount) throws IOException, InterruptedException {
        Optional<UserFeedEntity> fromDb = userFeedRepository.findByNickName(nickName);

        UserFeed ufFromDb = getUFIfIsSufficient(nickName, fromDb, iterationCount);
        if((ufFromDb != null) && (ufFromDb.getUserPosts().size() >= iterationCount * 12 || ufFromDb.getPaginationToken().equals("null"))) {
            return ufFromDb;
        }

        Object lock = LockMapsForAnalyzeService.LOCK_FOR_USER_FEED.computeIfAbsent(nickName, k -> new Object());
        synchronized (lock) {
            fromDb = userFeedRepository.findByNickName(nickName);

            ufFromDb = getUFIfIsSufficient(nickName, fromDb, iterationCount);
            if((ufFromDb != null) && (ufFromDb.getUserPosts().size() >= iterationCount * 12 || ufFromDb.getPaginationToken().equals("null"))) {
                return ufFromDb;
            }
            UserFeed fromApi = instaProvider.getUserPosts(nickName, iterationCount);
            UserFeedEntity persisting = new UserFeedEntity();
            fromDb.ifPresent(userFeedEntity -> persisting.setId(userFeedEntity.getId()));
            persisting.setNickName(nickName);
            persisting.setUserFeed(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userFeedRepository.save(persisting);
            LockMapsForAnalyzeService.LOCK_FOR_USER_FEED.remove(nickName);
            return fromApi;
        }
    }

    public UserStories getUserStories(String nickName) throws IOException, InterruptedException {
        Optional<UserStoriesEntity> fromDb = userStoriesRepository.findByNickName(nickName);

        UserStories usFromDb = getUSIfIsSufficient(nickName, fromDb);
        if(usFromDb != null) {
            return usFromDb;
        }

        Object lock = LockMapsForAnalyzeService.LOCK_FOR_USER_STORIES.computeIfAbsent(nickName, k -> new Object());
        synchronized (lock) {
            fromDb = userStoriesRepository.findByNickName(nickName);
            usFromDb = getUSIfIsSufficient(nickName, fromDb);
            if(usFromDb != null) {
                return usFromDb;
            }

            UserStories fromApi = instaProvider.getUserStories(nickName);
            UserStoriesEntity persisting = new UserStoriesEntity();
            persisting.setNickName(nickName);
            persisting.setUserStories(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userStoriesRepository.save(persisting);
            return fromApi;
        }
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

    private UserProfile getUPIfIsSufficient(String nickName, Optional<UserProfileEntity> fromDb) {
        if(fromDb.isPresent()) {
            try {
                return fromDb.get().getUserProfile();
            } catch (Exception e) {
                LOG.error("Exception while decoding data fetched from DB for username: {} ", nickName, e);
                return null;
            }
        }
        return null;
    }

    private UserFeed getUFIfIsSufficient(String nickName, Optional<UserFeedEntity> fromDb, int iterationCount) {
        if(fromDb.isPresent()) {
            try {
                return fromDb.get().getUserFeed();
            } catch (Exception e) {
                LOG.error("Exception while decoding data fetched from DB for username: {} ", nickName, e);
                return null;
            }
        }
        return null;
    }

    private UserStories getUSIfIsSufficient(String nickName, Optional<UserStoriesEntity> fromDb) {
        try {
            if(fromDb.isPresent()) {
                return fromDb.get().getUserStories();
            }
        } catch (Exception e) {
            LOG.error("Exception while decoding data fetched from DB for username: {} ", nickName, e);
            return null;
        }
        return null;
    }
}

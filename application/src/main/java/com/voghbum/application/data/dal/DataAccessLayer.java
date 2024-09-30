package com.voghbum.application.data.dal;

import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.AiOutput;
import com.voghbum.application.data.entity.*;
import com.voghbum.application.data.repository.AiOutputRepository;
import com.voghbum.application.data.repository.UserFeedRepository;
import com.voghbum.application.data.repository.UserProfileRepository;
import com.voghbum.application.data.repository.UserStoriesRepository;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.UserFeed;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class DataAccessLayer {
    private static final Logger LOG = LoggerFactory.getLogger(DataAccessLayer.class);
    private final UserProfileRepository userProfileRepository;
    private final UserStoriesRepository userStoriesRepository;
    private final UserFeedRepository userFeedRepository;
    private final AiOutputRepository aiOutputRepository;
    private final InstaProvider instaProvider;
    private final AiProvider aiProvider = null;


    public DataAccessLayer(UserProfileRepository userProfileRepository,
                           UserStoriesRepository userStoriesRepository,
                           InstaProvider instaProvider, AiProvider aiProvider,
                           UserFeedRepository userFeedRepository,
                           AiOutputRepository aiOutputRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userStoriesRepository = userStoriesRepository;
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
        this.userFeedRepository = userFeedRepository;
        this.aiOutputRepository = aiOutputRepository;
    }

    @Transactional(readOnly = true)
    public UserProfile getUserProfile(String nickName) throws IOException, InterruptedException {
        Optional<UserProfileEntity> fromDb = userProfileRepository.findByNickName(nickName);

        UserProfile upFromDb = getUPIfIsSufficient(nickName, fromDb);
        if(upFromDb != null) {
            return upFromDb;
        }

        Object lock = LockMaps.LOCK_FOR_USER_PROFILE.computeIfAbsent(nickName, k -> new Object());
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

            LockMaps.LOCK_FOR_USER_PROFILE.remove(nickName);
            return fromApi;
        }
    }

    @Transactional(readOnly = true)
    public UserFeed getUserFeed(String nickName, int iterationCount) throws IOException, InterruptedException {
        Optional<UserFeedEntity> fromDb = userFeedRepository.findByNickName(nickName);

        UserFeed ufFromDb = getUFIfIsSufficient(nickName, fromDb, iterationCount);
        if(ufFromDb != null) {
            return ufFromDb;
        }

        Object lock = LockMaps.LOCK_FOR_USER_FEED.computeIfAbsent(nickName, k -> new Object());
        synchronized (lock) {
            fromDb = userFeedRepository.findByNickName(nickName);

            ufFromDb = getUFIfIsSufficient(nickName, fromDb, iterationCount);
            if(ufFromDb != null) {
                return ufFromDb;
            }
            UserFeed fromApi = instaProvider.getUserPosts(nickName, iterationCount);
            UserFeedEntity persisting = new UserFeedEntity();
            persisting.setNickName(nickName);
            persisting.setUserFeed(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userFeedRepository.save(persisting);
            LockMaps.LOCK_FOR_USER_FEED.remove(nickName);
            return fromApi;
        }
    }

    @Transactional(readOnly = true)
    public UserStories getUserStories(String nickName) throws IOException, InterruptedException {
        Optional<UserStoriesEntity> fromDb = userStoriesRepository.findByNickName(nickName);

        UserStories usFromDb = getUSIfIsSufficient(nickName, fromDb);
        if(usFromDb != null) {
            return usFromDb;
        }

        Object lock = LockMaps.LOCK_FOR_USER_STORIES.computeIfAbsent(nickName, k -> new Object());
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

    public AiOutput getAiOutput(String nickName, AiOutputType type) {
        Optional<AiOutputEntity> fromDb = aiOutputRepository.findByUsernameAndResultType(nickName, type);

        AiOutput usFromDb = getAiIfSufficient(nickName, fromDb);
        if(usFromDb != null) {
            return usFromDb;
        }

        Object lock = LockMaps.LOCK_FOR_USER_STORIES.computeIfAbsent(nickName, k -> new Object());
        synchronized (lock) {
            fromDb = aiOutputRepository.findByUsernameAndResultType(nickName, type);
            usFromDb = getUSIfIsSufficient(nickName, fromDb);
            if(usFromDb != null) {
                return usFromDb;
            }

            UserStories fromApi = aiProvider.analyzeLoveLife(nickName);
            UserStoriesEntity persisting = new UserStoriesEntity();
            persisting.setNickName(nickName);
            persisting.setUserStories(fromApi);
            persisting.setLastUpdateTime(LocalDateTime.now());
            userStoriesRepository.save(persisting);
            return fromApi;
        }
    }

    private AiOutput getAiIfSufficient(String nickName, Optional<AiOutputEntity> fromDb) {
        if(fromDb.isPresent()) {
            try {
                return fromDb.get().getAiOutput();
            } catch (Exception e) {
                LOG.error("Exception while decoding data fetched from DB for username: {} ", nickName, e);
                return null;
            }
        }
        return null;
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
                var postsFromDb = fromDb.get().getUserFeed();
                var isSufficient = postsFromDb.getUserPosts().size() >= iterationCount * 12 || postsFromDb.getPaginationToken() == null;
                if(isSufficient) {
                    return postsFromDb;
                }
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

package com.voghbum.application.service;

import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.application.data.response.AnalyzeResponse;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.UserPosts;
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

    public AnalyzeService(InstaProvider instaProvider, AiProvider aiProvider) {
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
    }

    public AnalyzeResponse analyze(String username) {
        var result = new AnalyzeResponse();

        try {
            var info = instaProvider.getUserPosts(username);
            var posts = instaProvider.getUserInfo(username);
            var stories = instaProvider.getUserStories(username);

            List<UserPosts.Item> postItems = posts.getData().getItems();
            result.setUserPosts(postItems);
            result.setUserProfile(info);
            result.setUsername(username);
            result.setStories(stories);

            if(info.getAbout() == null) {
                LOG.warn("About info is null");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

package com.voghbum.application.service;

import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.application.data.dto.AnalyzeResponse;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.UserProfile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AnalyzeService {
    private final InstaProvider instaProvider;
    private final AiProvider aiProvider;

    public AnalyzeService(InstaProvider instaProvider, AiProvider aiProvider) {
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
    }

    public AnalyzeResponse analyze(String username) {
        var result = new AnalyzeResponse();

        try {
            var info = instaProvider.getProfileInfo(username);
            var posts = instaProvider.getProfilePosts(username);

            result.setUserPosts(posts);
            result.setUserProfile(info);
            result.setUsername(username);

            if(info.getAbout() == null) {
                var emptyAbout = new UserProfile.About();
                emptyAbout.setCountry("unknown");
                emptyAbout.setDateJoined("unknown");
                emptyAbout.setDateJoinedAsTimestamp(0L);
                emptyAbout.setFormerUsernames(0);
                info.setAbout(emptyAbout);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

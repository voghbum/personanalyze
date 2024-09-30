package com.voghbum.instaprovider;

import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserFeed;
import com.voghbum.instaprovider.data.UserStories;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InstaProvider {
    UserProfile getUserInfo(String username) throws IOException, InterruptedException;
    UserFeed getUserPosts(String username, int iteration) throws IOException, InterruptedException;
    UserStories getUserStories(String username) throws IOException, InterruptedException;
}

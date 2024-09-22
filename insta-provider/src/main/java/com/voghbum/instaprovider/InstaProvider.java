package com.voghbum.instaprovider;

import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserStories;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InstaProvider {
    UserProfile getUserPosts(String username) throws IOException, InterruptedException;
    UserPosts getUserInfo(String username) throws IOException, InterruptedException;
    UserStories getUserStories(String username) throws IOException, InterruptedException;
}

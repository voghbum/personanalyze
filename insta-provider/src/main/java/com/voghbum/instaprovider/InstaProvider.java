package com.voghbum.instaprovider;

import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserPosts;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InstaProvider {
    UserProfile getProfileInfo(String username) throws IOException, InterruptedException;
    UserPosts getProfilePosts(String username) throws IOException, InterruptedException;
}

package com.voghbum.instaprovider;

import com.voghbum.instaprovider.data.ProfileInfoData;
import com.voghbum.instaprovider.data.ProfilePostsData;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InstaProvider {
    ProfileInfoData getProfileInfo(String username) throws IOException, InterruptedException;
    ProfilePostsData getProfilePosts(String username) throws IOException, InterruptedException;
}

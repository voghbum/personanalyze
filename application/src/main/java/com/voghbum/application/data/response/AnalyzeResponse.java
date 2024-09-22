package com.voghbum.application.data.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;

import java.util.List;

public class AnalyzeResponse {
    private String username;
    @JsonProperty("user_info")
    private UserProfile userProfile;
    @JsonProperty("user_feed")
    private List<UserPosts.Item> userPosts;
    @JsonProperty("stories")
    private UserStories stories;

    public UserStories getStories() {
        return stories;
    }

    public void setStories(UserStories stories) {
        this.stories = stories;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<UserPosts.Item> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<UserPosts.Item> userPosts) {
        this.userPosts = userPosts;
    }
}

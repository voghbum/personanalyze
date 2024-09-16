package com.voghbum.application.data.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserProfile;

public class AnalyzeResponse {
    private String username;
    @JsonProperty("user_profile")
    private UserProfile userProfile;
    @JsonProperty("user_posts")
    private UserPosts userPosts;

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

    public UserPosts getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(UserPosts userPosts) {
        this.userPosts = userPosts;
    }
}

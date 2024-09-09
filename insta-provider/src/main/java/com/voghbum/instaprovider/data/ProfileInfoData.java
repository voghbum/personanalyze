package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
public class ProfileInfoData {
    private String biography;
    private String category;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("follower_count")
    private int followerCount;
    @JsonProperty("following_count")
    private int followingCount;

    // Getters ve Setters
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    @Override
    public String toString() {
        return "ProfileInfoData{" +
                "biography='" + biography + '\'' +
                ", category='" + category + '\'' +
                ", fullName='" + fullName + '\'' +
                ", followerCount=" + followerCount +
                ", followingCount=" + followingCount +
                '}';
    }
}

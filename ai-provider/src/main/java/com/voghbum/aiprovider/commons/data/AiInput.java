package com.voghbum.aiprovider.commons.data;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;

@JsonFilter("excludeImageUrls")
public class AiInput {
    private List<AiInputImage> images;
    private String biography;
    private String fullName;
    private int followerCount;
    private int followingCount;
    private String username;
    private String profilePicUrl;
    private int mediaCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public int getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(int mediaCount) {
        this.mediaCount = mediaCount;
    }

    public List<AiInputImage> getImages() {
        return images;
    }

    public void setImages(List<AiInputImage> imageUrls) {
        this.images = imageUrls;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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
}

package com.voghbum.aiprovider.commons.data;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;

@JsonFilter("excludeImageUrls")
public class AiInput {
    private List<AiInputImage> images;
    private String biography;
    private String fullName;
    private String Caption;
    private int followerCount;
    private int followingCount;

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

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
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

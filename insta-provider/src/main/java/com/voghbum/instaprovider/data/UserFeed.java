package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserFeed {
    @JsonProperty("user_posts")
    private List<UserPost> userPosts;
    private String paginationToken;

    public String getPaginationToken() {
        return paginationToken;
    }

    public void setPaginationToken(String paginationToken) {
        this.paginationToken = paginationToken;
    }

    public List<UserPost> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<UserPost> userPosts) {
        this.userPosts = userPosts;
    }

    public static class UserPost {
        @JsonProperty("id")
        private long id;
        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;
        @JsonProperty("caption")
        private String caption;
        @JsonProperty("like_count")
        private long likeCount;
        @JsonProperty("comment_count")
        private long commentCount;
        @JsonProperty("taken_at")
        private String takenAt;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public long getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(long likeCount) {
            this.likeCount = likeCount;
        }

        public long getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(long commentCount) {
            this.commentCount = commentCount;
        }

        public String getTakenAt() {
            return takenAt;
        }

        public void setTakenAt(String takenAt) {
            this.takenAt = takenAt;
        }
    }

}
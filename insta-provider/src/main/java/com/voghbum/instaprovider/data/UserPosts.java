package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserPosts {
    @JsonProperty("data")
    private Data data;

    @JsonProperty("pagination_token")
    private String paginationToken;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getPaginationToken() {
        return paginationToken;
    }

    public void setPaginationToken(String paginationToken) {
        this.paginationToken = paginationToken;
    }

    public static class Data {
        @JsonProperty("count")
        private int count;

        @JsonProperty("items")
        private List<Item> items;

        @JsonProperty("user")
        private User user;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    /*
        {
          "user_feed": [
            {
              "id": "post1",
              "thumbnail_url": "https://example.com/post1_thumbnail.jpg",
              "caption": "Harika bir gün geçirdik! #tatil #deniz",
              "like_count": 1500,
              "comment_count": 45
            },
            {
              "id": "post2",
              "thumbnail_url": "https://example.com/post2_thumbnail.jpg",
              "caption": "Yeni projem hakkında heyecanlıyım! #iş #proje",
              "like_count": 2000,
              "comment_count": 78
            },
            {
              "id": "post3",
              "thumbnail_url": "https://example.com/post3_thumbnail.jpg",
              "caption": "Bugün harika bir kahvaltı yaptık #kahvaltı #aile",
              "like_count": 1200,
              "comment_count": 30
            },
            {
              "id": "post4",
              "thumbnail_url": "https://example.com/post4_thumbnail.jpg",
              "caption": "Yeni hobim: Fotoğrafçılık #fotoğraf #hobi",
              "like_count": 1800,
              "comment_count": 55
            },
            {
              "id": "post5",
              "thumbnail_url": "https://example.com/post5_thumbnail.jpg",
              "caption": "Doğa yürüyüşü #doğa #spor",
              "like_count": 2200,
              "comment_count": 67
            },
            {
              "id": "post6",
              "thumbnail_url": "https://example.com/post6_thumbnail.jpg",
              "caption": "Kitap okuma zamanı #kitap #okuma",
              "like_count": 1300,
              "comment_count": 40
            }
          ]
        }
     */
    public static class Item {
        @JsonProperty("id")
        private String id;

        @JsonProperty("code")
        private String code;

        @JsonProperty("taken_at")
        private long takenAt;

        @JsonProperty("media_type")
        private int mediaType;

        @JsonProperty("image_versions")
        private ImageVersions imageVersions;

        @JsonProperty("video_versions")
        private List<VideoVersion> videoVersions;

        @JsonProperty("original_width")
        private int originalWidth;

        @JsonProperty("original_height")
        private int originalHeight;

        @JsonProperty("caption")
        private Caption caption;

        @JsonProperty("comment_count")
        private int commentCount;

        @JsonProperty("like_count")
        private int likeCount;

        @JsonProperty("play_count")
        private int playCount;

        @JsonProperty("user")
        private User user;

        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getTakenAt() {
            return takenAt;
        }

        public void setTakenAt(long takenAt) {
            this.takenAt = takenAt;
        }

        public int getMediaType() {
            return mediaType;
        }

        public void setMediaType(int mediaType) {
            this.mediaType = mediaType;
        }

        public ImageVersions getImageVersions() {
            return imageVersions;
        }

        public void setImageVersions(ImageVersions imageVersions) {
            this.imageVersions = imageVersions;
        }

        public List<VideoVersion> getVideoVersions() {
            return videoVersions;
        }

        public void setVideoVersions(List<VideoVersion> videoVersions) {
            this.videoVersions = videoVersions;
        }

        public int getOriginalWidth() {
            return originalWidth;
        }

        public void setOriginalWidth(int originalWidth) {
            this.originalWidth = originalWidth;
        }

        public int getOriginalHeight() {
            return originalHeight;
        }

        public void setOriginalHeight(int originalHeight) {
            this.originalHeight = originalHeight;
        }

        public Caption getCaption() {
            return caption;
        }

        public void setCaption(Caption caption) {
            this.caption = caption;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }
    }

    public static class ImageVersions {
        @JsonProperty("items")
        private List<ImageVersion> items;

        public List<ImageVersion> getItems() {
            return items;
        }

        public void setItems(List<ImageVersion> items) {
            this.items = items;
        }

    }

    public static class ImageVersion {
        @JsonProperty("width")
        private int width;

        @JsonProperty("height")
        private int height;

        @JsonProperty("url")
        private String url;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class VideoVersion {
        @JsonProperty("type")
        private int type;

        @JsonProperty("width")
        private int width;

        @JsonProperty("height")
        private int height;

        @JsonProperty("url")
        private String url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Caption {
        @JsonProperty("text")
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class User {
        @JsonProperty("id")
        private String id;

        @JsonProperty("username")
        private String username;

        @JsonProperty("full_name")
        private String fullName;

        @JsonProperty("is_private")
        private boolean isPrivate;

        @JsonProperty("is_verified")
        private boolean isVerified;

        @JsonProperty("profile_pic_url")
        private String profilePicUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public boolean isPrivate() {
            return isPrivate;
        }

        public void setPrivate(boolean aPrivate) {
            isPrivate = aPrivate;
        }

        public boolean isVerified() {
            return isVerified;
        }

        public void setVerified(boolean verified) {
            isVerified = verified;
        }

        public String getProfilePicUrl() {
            return profilePicUrl;
        }

        public void setProfilePicUrl(String profilePicUrl) {
            this.profilePicUrl = profilePicUrl;
        }
    }
}
package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile {
    private String username;
    private String fullName;
    private String biography;
    private String externalUrl;
    private int followerCount;
    private int followingCount;
    private int mediaCount;
    private String profilePicUrl;
    private boolean isPrivate;
    private boolean isVerified;
    private About about;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("biography")
    public String getBiography() {
        return biography;
    }

    @JsonProperty("external_url")
    public String getExternalUrl() {
        return externalUrl;
    }

    @JsonProperty("follower_count")
    public int getFollowerCount() {
        return followerCount;
    }

    @JsonProperty("following_count")
    public int getFollowingCount() {
        return followingCount;
    }

    @JsonProperty("media_count")
    public int getMediaCount() {
        return mediaCount;
    }

    @JsonProperty("profile_pic_url")
    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    @JsonProperty("is_private")
    public boolean isPrivate() {
        return isPrivate;
    }

    @JsonProperty("is_verified")
    public boolean isVerified() {
        return isVerified;
    }

    @JsonProperty("about")
    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    public static class About {
        private String country;
        private String dateJoined;
        private long dateJoinedAsTimestamp;
        private int formerUsernames;

        @JsonProperty("country")
        public String getCountry() {
            return country;
        }

        @JsonProperty("date_joined")
        public String getDateJoined() {
            return dateJoined;
        }

        @JsonProperty("date_joined_as_timestamp")
        public long getDateJoinedAsTimestamp() {
            return dateJoinedAsTimestamp;
        }

        @JsonProperty("former_usernames")
        public int getFormerUsernames() {
            return formerUsernames;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setDateJoined(String dateJoined) {
            this.dateJoined = dateJoined;
        }

        public void setDateJoinedAsTimestamp(long dateJoinedAsTimestamp) {
            this.dateJoinedAsTimestamp = dateJoinedAsTimestamp;
        }

        public void setFormerUsernames(int formerUsernames) {
            this.formerUsernames = formerUsernames;
        }
    }
}
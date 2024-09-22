package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile {
    @JsonProperty("username")
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("external_url")
    private String externalUrl;
    @JsonProperty("follower_count")
    private int followerCount;
    @JsonProperty("following_count")
    private int followingCount;
    @JsonProperty("media_count")
    private int mediaCount;
    @JsonProperty("profile_pic_url")
    private String profilePicUrl;
    @JsonProperty("is_private")
    private boolean isPrivate;
    @JsonProperty("is_verified")
    private boolean isVerified;
    @JsonProperty("about")
    private About about;

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBiography() {
        return biography;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getMediaCount() {
        return mediaCount;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    public static class About {
        @JsonProperty("country")
        private String country;
        @JsonProperty("date_joined")
        private String dateJoined;
        @JsonProperty("date_joined_as_timestamp")
        private long dateJoinedAsTimestamp;
        @JsonProperty("former_usernames")
        private int formerUsernames;

        public String getCountry() {
            return country;
        }

        public String getDateJoined() {
            return dateJoined;
        }

        public long getDateJoinedAsTimestamp() {
            return dateJoinedAsTimestamp;
        }

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
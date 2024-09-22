package com.voghbum.application.data.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserPosts;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="profile_information")
public class ProfileInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String userProfile;

    @Column(columnDefinition = "TEXT")
    private String userPosts;

    @Column(columnDefinition = "TEXT")
    private String userStories;

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setUserProfile(UserProfile userProfile) throws JsonProcessingException {
        this.userProfile = objectMapper.writeValueAsString(userProfile);
    }

    public void setUserPosts(List<UserPosts.Item> userPosts) throws JsonProcessingException {
        this.userPosts = objectMapper.writeValueAsString(userPosts);
    }

    public void setUserStories(UserStories userStories) throws JsonProcessingException {
        this.userStories = objectMapper.writeValueAsString(userStories);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserProfile getUserProfile() throws JsonProcessingException {
        return objectMapper.readValue(this.userProfile, UserProfile.class);
    }

    public List<UserPosts.Item> getUserPosts() throws JsonProcessingException {
        return objectMapper.convertValue(this.userPosts, new TypeReference<List<UserPosts.Item>>() {});
    }

    public UserStories getUserStories() throws JsonProcessingException {
        return objectMapper.readValue(this.userStories, UserStories.class);
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }
}

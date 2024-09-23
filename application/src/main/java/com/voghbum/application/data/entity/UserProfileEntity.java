package com.voghbum.application.data.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserProfile;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user_profile")
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String userProfile;

    private LocalDateTime lastUpdateTime;

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setUserProfile(UserProfile userProfile) throws JsonProcessingException {
        this.userProfile = objectMapper.writeValueAsString(userProfile);
    }

    public UserProfile getUserProfile() throws JsonProcessingException {
        return objectMapper.readValue(this.userProfile, UserProfile.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}

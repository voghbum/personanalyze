package com.voghbum.application.data.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserProfile;
import com.voghbum.instaprovider.data.UserStories;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user_stories")
public class UserStoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String userStories;

    private LocalDateTime lastUpdateTime;

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setUserStories(UserStories userStories) throws JsonProcessingException {
        this.userStories = objectMapper.writeValueAsString(userStories);
    }

    public UserStories getUserStories() throws JsonProcessingException {
        return objectMapper.readValue(this.userStories, UserStories.class);
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

package com.voghbum.application.data.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.instaprovider.data.UserFeed;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user_feed")
public class UserFeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String userFeed;

    private LocalDateTime lastUpdateTime;

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setUserFeed(UserFeed userFeed) throws JsonProcessingException {
        this.userFeed = objectMapper.writeValueAsString(userFeed);
    }

    public UserFeed getUserFeed() throws JsonProcessingException {
        return objectMapper.readValue(this.userFeed, UserFeed.class);
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

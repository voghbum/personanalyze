package com.voghbum.application.data.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.voghbum.aiprovider.commons.data.AiOutput;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_output", indexes = {
        @Index(name = "idx_id_result_type", columnList = "id, outputType")
})
public class AiOutputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private AiOutputType outputType;

    @Column(columnDefinition = "TEXT")
    private String aiOutput;

    private LocalDateTime lastUpdateTime;

    @Transient
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setAiOutput(AiOutput aiOutput) throws JsonProcessingException {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("excludeImageUrls", SimpleBeanPropertyFilter.serializeAll());
        objectMapper.setFilterProvider(filterProvider);
        this.aiOutput = objectMapper.writeValueAsString(aiOutput);
    }

    public AiOutput getAiOutput() throws JsonProcessingException {
        return objectMapper.readValue(this.aiOutput, AiOutput.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AiOutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(AiOutputType outputType) {
        this.outputType = outputType;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}

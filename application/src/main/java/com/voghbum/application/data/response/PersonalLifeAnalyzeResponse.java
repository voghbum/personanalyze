package com.voghbum.application.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalLifeAnalyzeResponse {
    @JsonProperty("ai_result")
    private String aiResult;

    public void setAiResult(String aiResult) {
        this.aiResult = aiResult;
    }

    public String getAiResult() {
        return aiResult;
    }

    @Override
    public String toString() {
        return "RoastResponse{" +
                ", aiResult='" + aiResult + '\'' +
                '}';
    }
}

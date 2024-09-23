package com.voghbum.application.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipResponse {
    @JsonProperty("ai_result")
    private String aiResult;

    public String getAiResult() {
        return aiResult;
    }

    public void setAiResult(String aiResult) {
        this.aiResult = aiResult;
    }
}

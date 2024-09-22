package com.voghbum.application.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoastResponse {
    @JsonProperty("image_urls")
    private List<String> imageUrls;

    @JsonProperty("ai_result")
    private String aiResult;

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setAiResult(String aiResult) {
        this.aiResult = aiResult;
    }

    public String getAiResult() {
        return aiResult;
    }

    @Override
    public String toString() {
        return "RoastResponse{" +
                "imageUrls=" + imageUrls +
                ", aiResult='" + aiResult + '\'' +
                '}';
    }
}

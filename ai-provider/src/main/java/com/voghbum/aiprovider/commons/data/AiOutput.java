package com.voghbum.aiprovider.commons.data;

import java.util.List;

public class AiOutput {
    private String aiOutput;
    private List<AiInput> relatedInputs;

    public String getAiOutput() {
        return aiOutput;
    }

    public void setAiOutput(String aiOutput) {
        this.aiOutput = aiOutput;
    }

    public List<AiInput> getRelatedImages() {
        return relatedInputs;
    }

    public void setRelatedImages(List<AiInput> relatedImages) {
        this.relatedInputs = relatedImages;
    }
}

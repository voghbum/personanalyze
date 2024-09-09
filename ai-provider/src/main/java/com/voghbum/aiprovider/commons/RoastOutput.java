package com.voghbum.aiprovider.commons;

import java.util.List;

public class RoastOutput {
    private String roastText;
    private List<String> relatedImages;

    public String getRoastText() {
        return roastText;
    }

    public void setRoastText(String roastText) {
        this.roastText = roastText;
    }

    public List<String> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(List<String> relatedImages) {
        this.relatedImages = relatedImages;
    }
}

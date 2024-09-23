package com.voghbum.aiprovider.commons.data;

import java.util.List;

public class ShipOutput {
    private String shipText;
    private List<String> relatedImages;

    public String getShipText() {
        return shipText;
    }

    public void setShipText(String shipText) {
        this.shipText = shipText;
    }

    public List<String> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(List<String> relatedImages) {
        this.relatedImages = relatedImages;
    }
}

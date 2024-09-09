package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
public class ProfilePostsData {
    @JsonProperty("items")
    private List<Item> items;

    // Getter ve setterlar
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
    public static class Item {
        @JsonProperty("caption")
        private Caption caption;

        @JsonProperty("comment_count")
        private int commentCount;

        @JsonProperty("image_versions")
        private ImageVersion imageVersions;

        @JsonProperty("like_count")
        private int likeCount;

//        @JsonProperty("location")
//        private String location;

        @JsonProperty("carousel_media")
        private List<CarouselMedia> carouselMedia;

        // Getter ve setterlar
        public Caption getCaption() {
            return caption;
        }

        public void setCaption(Caption caption) {
            this.caption = caption;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public ImageVersion getImageVersions() {
            return imageVersions;
        }

        public void setImageVersions(ImageVersion imageVersions) {
            this.imageVersions = imageVersions;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public List<CarouselMedia> getCarouselMedia() {
            return carouselMedia;
        }

        public void setCarouselMedia(List<CarouselMedia> carouselMedia) {
            this.carouselMedia = carouselMedia;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "caption=" + caption +
                    ", commentCount=" + commentCount +
                    ", imageVersions=" + imageVersions +
                    ", likeCount=" + likeCount +
                    ", carouselMedia=" + carouselMedia +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
    public static class Caption {
        @JsonProperty("text")
        private String text;

        // Getter ve setterlar
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Caption{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
    // ImageVersion sınıfı
    public static class ImageVersion {
        @JsonProperty("items")
        private List<UrlItem> items;

        // Getter ve setterlar
        public List<UrlItem> getItems() {
            return items;
        }

        public void setItems(List<UrlItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "ImageVersion{" +
                    "items=" + items +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
    public static class UrlItem {
        @JsonProperty("url")
        private String url;

        // Getter ve setterlar
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "UrlItem{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true) // Bilinmeyen özellikleri yoksay
    public static class CarouselMedia {
        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;

        // Getter ve setterlar
        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        @Override
        public String toString() {
            return "CarouselMedia{" +
                    "thumbnailUrl='" + thumbnailUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProfilePostsData{" +
                "items=" + items +
                '}';
    }
}

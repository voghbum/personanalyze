package com.voghbum.instaprovider.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
    {
      "Highlighted Stories": [
        {
          "title": "Tatil",
          "cover_image_url": "https://example.com/cover1.jpg"
        },
        {
          "title": "İş",
          "cover_image_url": "https://example.com/cover2.jpg"
        },
        {
          "title": "Aile",
          "cover_image_url": "https://example.com/cover3.jpg"
        }
      ]
    }
 */
public class UserStories {
    @JsonProperty("Highlighted Stories")
    private List<Story> highlightedStories;

    public List<Story> getHighlightedStories() {
        return highlightedStories;
    }

    public void setHighlightedStories(List<Story> highlightedStories) {
        this.highlightedStories = highlightedStories;
    }

    public static class Story {
        @JsonProperty("title")
        private String title;
        @JsonProperty("cover_image_url")
        private String coverImageUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }
    }
}

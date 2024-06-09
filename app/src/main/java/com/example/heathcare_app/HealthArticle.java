package com.example.heathcare_app;

public class HealthArticle {
    private String title;
    private String description;
    private String imageUrl;
    private String content;

    public HealthArticle(String title, String description, String imageUrl, String content) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContent() {
        return content;
    }
}

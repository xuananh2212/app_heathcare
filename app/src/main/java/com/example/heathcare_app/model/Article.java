package com.example.heathcare_app.model;

import java.util.List;

public class Article {
    private  int id;
    private String title;
    private String description;
    private String content;
    private String image;
    public Article(int id, String title, String description, String content, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }
}
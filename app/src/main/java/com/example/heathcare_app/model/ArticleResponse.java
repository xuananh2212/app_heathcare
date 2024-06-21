package com.example.heathcare_app.model;

import java.util.List;

public class ArticleResponse {
    private String message;
    private int status;
    private DataArticle data;

    // Constructors, getters, and setters
    public ArticleResponse(String message, int status, DataArticle data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public DataArticle getData() {
        return data;
    }
}


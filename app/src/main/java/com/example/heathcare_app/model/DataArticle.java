package com.example.heathcare_app.model;

import java.util.List;

public class DataArticle {
    private List<Article> articles;
    private int count;
    // Constructors, getters, and setters
    public DataArticle(List<Article> articles, int count) {
        this.articles = articles;
        this.count = count;
    }
    public List<Article> getArticles() {
        return articles;
    }
    public int getCount() {
        return count;
    }
}

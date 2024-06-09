package com.example.heathcare_app;

public class Product {
    private final int id;
    private final String title;
    private final double oldPrice;
    private final double price;
    private final String imageUrl;

    public Product(int id, String title, double oldPrice, double price, String imageUrl) {
        this.id = id;
        this.title = title;
        this.oldPrice = oldPrice;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

package com.example.heathcare_app.model;

import com.google.gson.annotations.SerializedName;

public class Medicine {
    private int id;
    private String name;
    @SerializedName("old_price")
    private double oldPrice;
    @SerializedName("new_price")
    private double newPrice;
    private String description;
    private String image;
    private double rate;

    public Medicine(int id, String name, double oldPrice, double newPrice, String description, String image, double rate) {
        this.id = id;
        this.name = name;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.description = description;
        this.image = image;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}

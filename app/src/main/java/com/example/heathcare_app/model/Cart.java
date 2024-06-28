package com.example.heathcare_app.model;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("product_id")
    private int productId;
    @SerializedName("user_id")
    private int userId;
    private int quantity;
    @SerializedName("old_price")
    private double oldPrice;
    @SerializedName("new_price")
    private double newPrice;
    private String status;

    public Cart(int productId, int userId, int quantity, double oldPrice, double newPrice, String status) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.status = status;
    }

    public Cart(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productId=" + productId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", status='" + status + '\'' +
                '}';
    }
}

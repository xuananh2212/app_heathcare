package com.example.heathcare_app.model;

import androidx.annotation.NonNull;

public class Carts {
    private int id;
    private int product_id;
    private int user_id;
    private int quantity;
    private double old_price;
    private double new_price;
    private String status;
    private String created_at;
    private String updated_at;
    private User user;
    private Medicine medicine;

    public Carts(int id, int productId, int userId, int quantity, double oldPrice, double newPrice, String status, String createdAt, String updatedAt, User user, Medicine medicine) {
        this.id = id;
        this.product_id = productId;
        this.user_id = userId;
        this.quantity = quantity;
        this.old_price = oldPrice;
        this.new_price = newPrice;
        this.status = status;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
        this.user = user;
        this.medicine = medicine;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getNewPrice() {
        return new_price;
    }

    public void setNewPrice(double newPrice) {
        this.new_price = newPrice;
    }

    public double getOldPrice() {
        return old_price;
    }

    public void setOldPrice(double oldPrice) {
        this.old_price = oldPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int productId) {
        this.product_id = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", user_id=" + user_id +
                ", quantity=" + quantity +
                ", old_price=" + old_price +
                ", new_price=" + new_price +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user=" + user +
                ", medicine=" + medicine +
                '}';
    }
}

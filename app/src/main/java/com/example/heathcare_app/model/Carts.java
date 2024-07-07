package com.example.heathcare_app.model;

import androidx.annotation.NonNull;

public class Carts {
    private int id;
    private int product_id;
    private int user_id;
    //
    private String name;
    private String description;
    private String image;
    private int quantity;
    //
//    private int quantity;
    private double old_price;
    private double new_price;
    private String status;
    private String created_at;
    private String updated_at;
    private User user;
//    private Medicine medicine;


    public Carts(int id, int product_id, int user_id, String name, String description, String image, int quantity, double old_price, double new_price, String status, String created_at, String updated_at, User user) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.old_price = old_price;
        this.new_price = new_price;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", old_price=" + old_price +
                ", new_price=" + new_price +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user=" + user +
                '}';
    }
}

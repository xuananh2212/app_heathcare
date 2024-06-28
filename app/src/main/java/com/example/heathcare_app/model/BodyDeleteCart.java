package com.example.heathcare_app.model;

public class BodyDeleteCart {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BodyDeleteCart(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BodyDeleteCart{" +
                "id=" + id +
                '}';
    }
}

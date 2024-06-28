package com.example.heathcare_app.model;

public class BodyUpdateCart {
    private int quantity;

    public BodyUpdateCart(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "BodyUpdateCart{" +
                "quantity=" + quantity +
                '}';
    }
}

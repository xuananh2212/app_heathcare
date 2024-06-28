package com.example.heathcare_app.model;

import java.util.List;

public class DataCarts {
    private List<Carts> carts;
    private int count;
    public List<Carts> getCarts() {
        return carts;
    }

    public void setCarts(List<Carts> carts) {
        this.carts = carts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

package com.example.heathcare_app.model;

public class ItemCarts {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private String urlImage;

    public ItemCarts(String name, float price, int quantity,int id,String urlImage) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "ItemCarts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}

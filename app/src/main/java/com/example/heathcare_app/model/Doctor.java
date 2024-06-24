package com.example.heathcare_app.model;

public class Doctor {
    private int id;
    private String name;
    private String address;
    private Float exp;
    private String phone;
    private String  image;
    private Float price;
    private int doctorGroupId;

    public Doctor(String name, String address, Float exp, String phone, String image, Float price) {
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phone = phone;
        this.image = image;
        this.price = price;
    }

    public Doctor(int id, String name, String address, Float exp, String phone, String image, Float price, int doctorGroupId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phone = phone;
        this.image = image;
        this.price = price;
        this.doctorGroupId = doctorGroupId;
    }

    public Doctor(String name, String address, Float exp, String phone, String image, Float price, int doctorGroupId) {
        this.name = name;
        this.address = address;
        this.exp = exp;
        this.phone = phone;
        this.image = image;
        this.price = price;
        this.doctorGroupId = doctorGroupId;
    }

    public int getDoctorGroupId() {
        return doctorGroupId;
    }

    public void setDoctorGroupId(int doctorGroupId) {
        this.doctorGroupId = doctorGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getExp() {
        return exp;
    }

    public void setExp(Float exp) {
        this.exp = exp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", exp=" + exp +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", doctorGroupId=" + doctorGroupId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

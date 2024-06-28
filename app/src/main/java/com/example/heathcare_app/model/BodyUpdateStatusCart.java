package com.example.heathcare_app.model;

public class BodyUpdateStatusCart {
    private String status;

    public String getStatus() {
        return status;
    }

    public BodyUpdateStatusCart(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BodyUpdateStatusCart{" +
                "status='" + status + '\'' +
                '}';
    }
}

package com.example.heathcare_app.model;

public class CartResponse {
    private String message;
    private int status;

    public CartResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}

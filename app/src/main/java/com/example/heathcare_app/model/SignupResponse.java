package com.example.heathcare_app.model;
public class SignupResponse {
    private String message;
    private int userId;

    // Constructors, getters and setters
    public SignupResponse(String message, int userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

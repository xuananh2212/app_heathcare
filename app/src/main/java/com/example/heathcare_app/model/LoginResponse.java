package com.example.heathcare_app.model;

public class LoginResponse{
    private String message;
    private int status;
    private Metadata metadata;

    public LoginResponse(String message, int status, Metadata metadata) {
        this.message = message;
        this.status = status;
        this.metadata = metadata;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}

package com.example.heathcare_app.model;

import java.util.List;

public class MedicineResponse {
    private String message;
    private int status;
    private DataMedicine data;

    // Constructors, getters, and setters
    public MedicineResponse(String message, int status, DataMedicine data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public DataMedicine getData() {
        return data;
    }
}


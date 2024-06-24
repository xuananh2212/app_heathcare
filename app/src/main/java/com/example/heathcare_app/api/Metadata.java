package com.example.heathcare_app.api;

import com.example.heathcare_app.model.Doctor;

import java.util.List;

public class Metadata {
    private List<Doctor> doctors;
    private int count;

    // Getters và Setters

    public Metadata(List<Doctor> doctors, int count) {
        this.doctors = doctors;
        this.count = count;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

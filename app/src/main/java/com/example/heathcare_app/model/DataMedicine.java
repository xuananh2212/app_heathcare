package com.example.heathcare_app.model;

import java.util.List;

public class DataMedicine {
    private List<Medicine> medicines;
    private int count;
    // Constructors, getters, and setters
    public DataMedicine(List<Medicine> medicines, int count) {
        this.medicines = medicines;
        this.count = count;
    }
    public List<Medicine> getMedicines() {
        return medicines;
    }
    public int getCount() {
        return count;
    }
}

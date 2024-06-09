package com.example.heathcare_app;
public class Medicine {
    private String name;
    private String date;

    public Medicine(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}

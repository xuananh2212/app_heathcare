package com.example.heathcare_app.model;

public class User {
    private String name;
    private String password;
    private String email;

    // Constructors, getters and setters
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

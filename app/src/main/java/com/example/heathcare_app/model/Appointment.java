package com.example.heathcare_app.model;

public class Appointment {
    private Doctor doctor;
    private String startTime;
    private String endTime;

    // Constructors, getters, and setters

    public Appointment(Doctor doctor, String startTime, String endTime) {
        this.doctor = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor=" + doctor +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
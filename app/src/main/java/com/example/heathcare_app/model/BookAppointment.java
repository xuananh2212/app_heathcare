package com.example.heathcare_app.model;

import java.util.Date;

public class BookAppointment {
    private  int userId;
    private int doctorId;
    private String startTime;
    private String endTime;

    public BookAppointment(int userId, int doctorId, String startTime, String endTime) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

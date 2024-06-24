package com.example.heathcare_app.model;

import java.util.Date;

public class BookAppointment {
    private  int user_id;
    private int doctor_id;
    private Date startTime;
    private Date endTime;

    public BookAppointment(int user_id, int doctor_id, Date startTime, Date endTime) {
        this.user_id = user_id;
        this.doctor_id = doctor_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

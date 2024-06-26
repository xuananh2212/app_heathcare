package com.example.heathcare_app.model;

import com.example.heathcare_app.model.Appointment;

import java.util.List;

public class ApiResponseGetBookAppointment {
    private String message;
    private int status;
    private Metadata data;


    public ApiResponseGetBookAppointment(String message, int status, Metadata data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

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

    public Metadata getData() {
        return data;
    }

    public void setData(Metadata data) {
        this.data = data;
    }

    public static class Metadata {
        private List<Appointment> appointments;
        private int count;

        public Metadata(List<Appointment> data, int count) {
            this.appointments = data;
            this.count = count;
        }

        public List<Appointment> getAppointments() {
            return appointments;
        }

        public void setAppointments(List<Appointment> appointments) {
            this.appointments = appointments;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}

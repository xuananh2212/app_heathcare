package com.example.heathcare_app.model;

import com.example.heathcare_app.model.Doctor;
import java.util.List;

public class ApiResponseDoctor {
    private String message;
    private int status;
    private Metadata data;

    public ApiResponseDoctor(String message, int status, Metadata data) {
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
        private List<Doctor> doctors;
        private int count;

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
}

package com.example.heathcare_app.model;

public class ApiResponsePatchBookAppointment {
        private String message;
        private int status;

    public ApiResponsePatchBookAppointment(String message, int status) {
        this.message = message;
        this.status = status;
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
    }



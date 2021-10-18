package com.example.demo.responsemodels;

public class UserResponseModel {
    private boolean successful;
    private String message;

    public UserResponseModel(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserRespondModel{" +
                "successful=" + successful +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



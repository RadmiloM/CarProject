package com.example.demo.responsemodels;

import java.util.UUID;

public class UserResponseLoginModel {
    private boolean successful;
    private String info;

    public UserResponseLoginModel(boolean successful, String info) {
        this.successful = successful;
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserRespondLoginModel{" +
                "successful=" + successful +
                ", info=" + info +
                '}';
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean   successful) {
        this.successful = successful;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

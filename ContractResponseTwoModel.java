package com.example.demo.responsemodels;

public class ContractResponseTwoModel {
    private boolean successful;
    private String info;

    public ContractResponseTwoModel(boolean successful, String info) {
        this.successful = successful;
        this.info = info;
    }

    @Override
    public String toString() {
        return "ContractResponseTwoModel{" +
                "successful=" + successful +
                ", info='" + info + '\'' +
                '}';
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

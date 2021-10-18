package com.example.demo.requestmodels;

import java.time.LocalDate;
import java.util.UUID;

public class ContractsRequestSixModel {
    private UUID userId;
    private UUID carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private boolean signed;

    public ContractsRequestSixModel(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate, double totalPrice, boolean signed) {
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.signed = signed;
    }

    @Override
    public String toString() {
        return "ContractsRequestSixModel{" +
                "userId=" + userId +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                ", signed=" + signed +
                '}';
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }
}

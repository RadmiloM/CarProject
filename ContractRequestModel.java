package com.example.demo.requestmodels;

import java.time.LocalDate;
import java.util.UUID;

public class ContractRequestModel {
    private UUID carId;
    private UUID userId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ContractRequestModel(UUID carId, UUID userId, LocalDate startDate, LocalDate endDate) {
        this.carId = carId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ContractRequestModel{" +
                "carId=" + carId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
}

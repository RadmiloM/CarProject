package com.example.demo.responsemodels;

import java.time.LocalDate;
import java.util.Date;

public class ResponseDateModel {
    private LocalDate startDate;
    private LocalDate endDate;

    public ResponseDateModel(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ResponseDateModel{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
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

package com.example.demo.parametersmodel;

public class CarsParametersModel {
    private int year;
    private String make;
    private String model;
    private boolean automatic;
    private double price;
    private int power;
    private int doors;

    public CarsParametersModel(int year, String make, String model, boolean automatic, double price, int power, int doors) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.automatic = automatic;
        this.price = price;
        this.power = power;
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "CarsSpecificModel{" +
                ", year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", automatic=" + automatic +
                ", price=" + price +
                ", power=" + power +
                ", doors=" + doors +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}



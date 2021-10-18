package com.example.demo.requestmodels;

import java.util.UUID;

public class CarsRequestModel {
    private UUID id;
    private String licencePlate;
    private String make;
    private String model;
    private int year;
    private int engineCapacity;
    private String color;
    private double price;
    private int doors;
    private char size;
    private int power;
    private boolean automatic;
    private String fuel;
    private String image;

    public CarsRequestModel(UUID id, String licencePlate, String make, String model, int year, int engineCapacity, String color, double price, int doors, char size, int power, boolean automatic, String fuel, String image) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.color = color;
        this.price = price;
        this.doors = doors;
        this.size = size;
        this.power = power;
        this.automatic = automatic;
        this.fuel = fuel;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CarsModel{" +
                ", licencePlate='" + licencePlate + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engineCapacity=" + engineCapacity +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", doors=" + doors +
                ", size=" + size +
                ", power=" + power +
                ", automatic=" + automatic +
                ", fuel='" + fuel + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}




package com.example.demo.dao;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.model.CarsContractsModel;
import com.example.demo.parametersmodel.CarsParametersModel;
import com.example.demo.requestmodels.CarsRequestModel;
import com.example.demo.responsemodels.CarsResponseModel;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarsDaoSQL {
    private static final Connection conn = DatabaseConnection.getConnection();

    // This method returns all the cars
    public List<CarsResponseModel> getAllCars(){
        List<CarsResponseModel> cars = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cars");
            while (rs.next()){
                CarsResponseModel car = new CarsResponseModel(
                        UUID.randomUUID(),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getInt(9),
                        rs.getString(10).charAt(0),
                        rs.getInt(11),
                        rs.getBoolean(12),
                        rs.getString(13),
                        rs.getString(14)

                );
                cars.add(car);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cars;
    }

    // This method returns specific cars
    public List<CarsParametersModel> getSpecificCars(){
        List<CarsParametersModel> cars = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT year,make,model,automatic,price,power,doors FROM cars");
            while(rs.next()){
                CarsParametersModel car = new CarsParametersModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getInt(7)
                );
                cars.add(car);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cars;
    }

    // This method returns car by specific parameters
    public CarsResponseModel getCar(String id){
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(" SELECT * FROM cars WHERE car_id = " + id);
            if(rs.next()){
                return new CarsResponseModel(UUID.randomUUID(),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),
                        rs.getDouble(8),rs.getInt(9),rs.getString(10).charAt(0),rs.getInt(11),
                        rs.getBoolean(12),rs.getString(13),rs.getString(14));
            }else{
                System.out.println("No results");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    // This method update the cars
    public void updateCar(CarsRequestModel cr){
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE cars " +
                    "SET licence_plate = ? " +
                    "make = ? " +
                    "model = ? " +
                    "year = ? " +
                    "engine_capacity = ? " +
                    "color = ? " +
                    "price = ? " +
                    "doors = ? " +
                    "size = ? " +
                    "power = ? " +
                    "automatic = ? " +
                    "fuel = ? " +
                    "image = ? " +
                    "WHERE car_id = ? ");
            st.setString(1,cr.getLicencePlate());
            st.setString(2,cr.getMake());
            st.setString(3,cr.getModel());
            st.setInt(4,cr.getYear());
            st.setInt(5,cr.getEngineCapacity());
            st.setString(6,cr.getColor());
            st.setDouble(7,cr.getSize());
            st.setInt(8,cr.getSize());
            st.setInt(9,cr.getPower());
            st.setBoolean(10,cr.isAutomatic());
            st.setString(11,cr.getFuel());
            st.setString(12,cr.getImage());
            st.setString(13,cr.getId().toString());
            st.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // This method delete cars
    public void deleteCar(UUID id) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM cars WHERE car_id = ?");
            st.setString(1, id.toString());
            st.executeUpdate();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // This method add cars
    public void addCars(CarsResponseModel car){
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO cars VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            st.setString(1,car.getCarId().toString());
            st.setString(2,car.getLicencePlate());
            st.setString(3,car.getMake());
            st.setString(4,car.getModel());
            st.setInt(5,car.getYear());
            st.setInt(6,car.getEngineCapacity());
            st.setString(7,car.getColor());
            st.setDouble(8,car.getPrice());
            st.setInt(9,car.getDoors());
            st.setString(10,String.valueOf(car.getSize()));
            st.setInt(11,car.getPower());
            st.setBoolean(12, car.isAutomatic());
            st.setString(13,car.getFuel());
            st.setString(14,car.getImage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // This method search for available cars
    public List<CarsContractsModel> getAvailableCars(){
        List<CarsContractsModel> cars = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cars " +
                    "INNER JOIN contracts ON cars.car_id = contracts.car_id "+
                    "WHERE start_date BETWEEN start_date AND end_date");
            while(rs.next()){
                CarsContractsModel cc = new CarsContractsModel(
                        UUID.randomUUID(),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getInt(9),
                        rs.getString(10).charAt(0),
                        rs.getInt(11),
                        rs.getBoolean(12),
                        rs.getString(13),
                        rs.getString(14),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        rs.getDate(18).toLocalDate(),
                        rs.getDate(19).toLocalDate(),
                        rs.getDouble(20),
                        rs.getBoolean(21),
                        rs.getBoolean(22)

                );
                cars.add(cc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cars;

    }




}

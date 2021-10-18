package com.example.demo.dao;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.model.AdminModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminDaoSQL {
    private static final Connection conn = DatabaseConnection.getConnection();

    // This method returns all admins
    public List<AdminModel> getAllAdmins(boolean admin){
        List<AdminModel> admins = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE admind = " + admin);
            while(rs.next()){
                AdminModel ad = new AdminModel(
                        UUID.randomUUID(),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10)
                );
                admins.add(ad);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admins;
    }


}

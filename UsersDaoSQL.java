package com.example.demo.dao;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.model.UsersModel;
import com.example.demo.requestmodels.UserRequestSevenModel;
import com.example.demo.responsemodels.UsersResponseSevenModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersDaoSQL {
    private static final Connection conn = DatabaseConnection.getConnection();

    // This method returns all users.
    public List<UsersModel> getAllUsers(){
        List<UsersModel> users = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while(rs.next()){
                UsersModel u = new UsersModel(
                        UUID.randomUUID(),
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getBoolean(9)
                );
                users.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    // This method update users
    public void update(UserRequestSevenModel ur){
        try {
            PreparedStatement st = conn.prepareStatement("Update users " +
                    "SET username = ? " +
                    "password = ? " +
                    "new_password = ? " +
                    "first_name = ? " +
                    "last_name = ? " +
                    "phone_number = ? " +
                    "image = ? " +
                    "WHERE user_id = ?");
            st.setString(1,ur.getUserName());
            st.setString(2,ur.getPassword());
            st.setString(3,ur.getNewPassword());
            st.setString(4,ur.getFirstName());
            st.setString(5,ur.getLastName());
            st.setString(6,ur.getPhoneNumber());
            st.setString(7,ur.getImage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // This method gets response on specific users
    public List<UsersResponseSevenModel> getUsersByResponse(){
        List<UsersResponseSevenModel> userResponse = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while(rs.next()){
                UsersResponseSevenModel user = new UsersResponseSevenModel(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                userResponse.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userResponse;

    }

    // This method delete specific user depending on provided id.
    public void deleteUser(UUID id){
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM user WHERE user_id = ?");
            st.setString(1,id.toString());
            st.executeUpdate();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

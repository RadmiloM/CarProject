package com.example.demo.dao;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.model.ContractsModel;
import com.example.demo.responsemodels.ContractResponseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractsDaoSQL {
    private static final Connection conn = DatabaseConnection.getConnection();

    //This method returns all contracts
    public List<ContractsModel> getAllContracts(){
        List<ContractsModel> contracts = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contracts");
            while(rs.next()){
                ContractsModel c = new ContractsModel(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getDouble(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8)
                );
                contracts.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contracts;
    }

    // This method add contracts
    public void addContract(ContractsModel contract){
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO contracts VALUES(?,?,?,?,?,?,?,?)");
            st.setString(1,contract.getContractId().toString());
            st.setString(2,contract.getUserId().toString());
            st.setString(3,contract.getCarId().toString());
            st.setString(4,contract.getStartDate().toString());
            st.setString(5,contract.getEndDate().toString());
            st.setDouble(6,contract.getTotalPrice());
            st.setBoolean(7,contract.isSigned());
            st.setBoolean(8,contract.isApproved());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

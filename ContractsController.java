package com.example.demo.controllers;

import com.example.demo.dao.AdminDaoSQL;
import com.example.demo.dao.ContractsDaoSQL;
import com.example.demo.dao.UsersDaoSQL;
import com.example.demo.model.AdminModel;
import com.example.demo.model.ContractsModel;
import com.example.demo.model.UsersModel;
import com.example.demo.requestmodels.ContractRequestModel;
import com.example.demo.requestmodels.ContractsRequestSixModel;
import com.example.demo.responsemodels.ContractResponseModel;
import com.example.demo.responsemodels.ContractResponseTwoModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class ContractsController {
    private static final ContractsDaoSQL contracts = new ContractsDaoSQL();
    private static final AdminDaoSQL admins = new AdminDaoSQL();
    private static final UsersDaoSQL users = new UsersDaoSQL();


    // This method should return sample of contract
    @PostMapping("/contracts/sample")
    public ContractResponseModel getSample(@RequestBody ContractRequestModel contractRequest) {
        for (ContractsModel c : contracts.getAllContracts()) {
            if (!c.getStartDate().equals(contractRequest.getStartDate()) && !c.getEndDate().equals(contractRequest.getEndDate()) && !c.isSigned() && c.isApproved()) {
                return new ContractResponseModel(UUID.randomUUID(), UUID.randomUUID(), c.getStartDate(), c.getEndDate(), 1234.4, false);
            }
        }
        return null;

    }

    //This method should add new contract.
    @PostMapping("/contracts")
    public ContractResponseTwoModel addContract(@RequestBody ContractsRequestSixModel rS) {
        for (ContractsModel c : contracts.getAllContracts()) {
            if (c.getUserId().equals(rS.getUserId()) && c.getCarId().equals(rS.getCarId())) {
                return new ContractResponseTwoModel(false, "Ugovor nije kreiran zauzet je ili korisnik ili automobil");
            }
            return new ContractResponseTwoModel(true, "Ugovor kreiran,ceka odobrenje");
        }
        return null;
    }

    // This method should return all contracts if user is admin.
    @GetMapping("/contracts")
    public List<ContractsModel> getAllContracts(@RequestHeader("id") UUID admid) {
        for (var contract : contracts.getAllContracts()) {
            for (var user : users.getAllUsers()) {
                if (user.getUserId().equals(admid) && user.isAdmin()) {
                    return contracts.getAllContracts();
                }

            }

        }
        return null;


    }

    // Returns all unresolved contracts if user is admin.
    @GetMapping("/contracts/pending")
    public List<ContractsModel> getApprovedContracts(@RequestHeader("admin-id") UUID adminid) {
        for (var contract : contracts.getAllContracts()) {
            for (var user : users.getAllUsers()) {
                if (user.getUserId().equals(adminid) && user.isAdmin() && !contract.isApproved()) {
                    return contracts.getAllContracts();
                }

            }
        }
        return null;


    }

    // This method should delete or approve contract.
    @PostMapping("/contracts/{contractId}/approval")
    public boolean isValidContract(@RequestHeader("authorization") UUID adminid) {
        for (var contract : contracts.getAllContracts()) {
            for (var user : users.getAllUsers()) {
                if (user.getUserId().equals(adminid) && user.isAdmin() && contract.isApproved()) {
                    return true;
                } else {
                    return false;
                }


            }

        }
        return false;



    }
    // This method should check users history.
    @GetMapping("/contracts/{userId}/history")
    public ContractsModel getHistoryOfClient(@RequestParam("userId") UUID userId){
        for(var contract : contracts.getAllContracts()){
            for(var user : users.getAllUsers()){
                if(user.getUserId().equals(userId)){
                    return new ContractsModel(contract.getContractId(),contract.getUserId(),contract.getCarId(),contract.getStartDate(),contract.getEndDate(),contract.getTotalPrice(),contract.isSigned(),contract.isApproved());
                }
            }
        }
        return null;

    }
}
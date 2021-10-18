package com.example.demo.controllers;

import com.example.demo.dao.UsersDaoSQL;
import com.example.demo.model.UsersModel;
import com.example.demo.requestmodels.AdminRequestModel;
import com.example.demo.responsemodels.AdminResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AdminController {

    private static final UsersDaoSQL user = new UsersDaoSQL();

    //this method should update user if user is admin.
    @GetMapping("/admin/update/{id}")
    public AdminResponseModel adminUpdate(@PathVariable("id") UUID id, @RequestBody AdminRequestModel admin) {

        for (UsersModel us : user.getAllUsers()) {
            if (us.getUserId().equals(id)) {
                if (us.getUserName().length() >= 3 && us.getPassword().length() == 8) {
                    return new AdminResponseModel(true, "Changed " + admin.getUsername() + " " + admin.getEmail() + admin.getFirstName() + admin.getLastName() + admin.getPhoneNumber() + admin.getPersonalNumber() + admin.getImage());
                }
            }

        }
    return null;

    }

}

package com.example.demo.controllers;

import com.example.demo.dao.UsersDaoSQL;
import com.example.demo.model.*;
import com.example.demo.requestmodels.UserRequestLoginModel;
import com.example.demo.requestmodels.UserRequestSevenModel;
import com.example.demo.requestmodels.UserRequestThreeModel;
import com.example.demo.responsemodels.UserResponseLoginModel;
import com.example.demo.responsemodels.UserResponseModel;
import com.example.demo.responsemodels.UsersResponseSevenModel;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class UsersController {

    private static final UsersDaoSQL users = new UsersDaoSQL();
    // This method should return new user if user enter the valid information.
    @PostMapping("/users/register")
    public UserResponseModel registerUser(@RequestBody UserRequestThreeModel user) {
        String regularExpression = "^(.+)@(.+)$";
        Pattern compile = Pattern.compile(regularExpression);
        for (var u : users.getAllUsers()) {
            Matcher matcher = compile.matcher(u.getEmail());
            if (u.getUserName().equals(user.getUserName()) || matcher.equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                return new UserResponseModel(true, "Vec postoji u bazi");
            } else {
                return new UserResponseModel(false, user.getUserName() + "-" + user.getEmail() + " je uspesno registrovan");
            }


        }
        return null;


    }
    // This method should allow user to login if the information he provided are correct.
    @PostMapping("/users/login")
    public UserResponseLoginModel loginUser(@RequestBody UserRequestLoginModel user) {
        for (UsersModel ur : users.getAllUsers()) {
            String userName = ur.getUserName();
            String email = ur.getEmail();
            String password = ur.getPassword();
            if (email.equals(user.getEmail()) || userName.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return new UserResponseLoginModel(true, ur.getUserId().toString());

            } else {
                return new UserResponseLoginModel(false, "Pogresan username,email ili password");
            }
        }
        return null;

    }
    //This method should update user if the information are valid.
    @PatchMapping("/users/{id}")
    public UserResponseModel updateUser(@PathVariable("id") UUID userId, @RequestBody UserRequestSevenModel user){
        for(UsersModel u : users.getAllUsers()){
            if(!u.getPassword().equals(user.getPassword())){
                return new UserResponseModel(false,"Password not valid");
            }


        }
        return null;


    }
    // This method should return all the information about user.
    @GetMapping("/users/{id}")
    public UsersResponseSevenModel getValidUser(@RequestParam("id") UUID id, @RequestBody UsersModel user){
        for(UsersModel us : users.getAllUsers()){
            if(us.getUserId().equals(user.getUserId())){
                return new UsersResponseSevenModel(us.getUserName(),us.getEmail(),us.getFirstName(),us.getLastName(),us.getPhoneNumber(),us.getPersonalNumber(),us.getImage());

            }
        }
        return null;
    }

    // This method should return all users.
    @GetMapping("/users")
    public List<UsersResponseSevenModel> getUsersByResponse(){
        return users.getUsersByResponse();
    }


}





















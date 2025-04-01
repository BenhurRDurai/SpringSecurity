package com.example.SpringSecurity.controller;


import com.example.SpringSecurity.model.Users;
import com.example.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/admin/add-user")
    public void addUser(@RequestBody Users user){
        userService.addUser(user);
    }
}

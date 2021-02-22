package com.example.demospring.Controller;

import com.example.demospring.DTO.MyRequestDTO;
import com.example.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
        System.out.println("Inside UserController Constructor");
    }


    @PostConstruct
    public void init() {

        System.out.println("Inside User Controller Post Constructor");

    }

    @Autowired
    private UserService userService;

    @PostMapping(path = "/update/employee/{id}")
    public boolean UpdateEmployee(@PathVariable String id, @RequestBody MyRequestDTO request) {

        return userService.updateEmployee(request, id);

    }

}

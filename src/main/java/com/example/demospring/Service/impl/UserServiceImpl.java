package com.example.demospring.Service.impl;

import com.example.demospring.DTO.MyRequestDTO;
import com.example.demospring.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("Inside UserService Constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("Inside UserService Post Constructor");
    }

    @Override
    public boolean updateEmployee(MyRequestDTO request, String id) {

        System.out.println("Invalid User Service " + request + " id " + id );
        return false;
    }
}

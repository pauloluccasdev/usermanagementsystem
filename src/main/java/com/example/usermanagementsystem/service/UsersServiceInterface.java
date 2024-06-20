package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Users;

import java.util.List;

public interface UsersServiceInterface {

    Users createUsers(Users users);

    List<Users> findAll();

}

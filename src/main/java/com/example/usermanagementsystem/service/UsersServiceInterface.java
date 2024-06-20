package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersServiceInterface {

    Users createUsers(Users users);

    List<Users> findAll();

    Optional<Users> findById(Long id);

    Users updateUsers(Long id, Users users);

    void deleteUsers(Long id);

}

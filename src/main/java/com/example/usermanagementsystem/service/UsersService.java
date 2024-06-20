package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsersService implements UsersServiceInterface {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users createUsers(Users users) {
        return usersRepository.save(users);
    }
}

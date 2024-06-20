package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UsersServiceInterface {

    @Autowired(required = true)
    private UsersRepository usersRepository;

    @Override
    public Users createUsers(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }
}

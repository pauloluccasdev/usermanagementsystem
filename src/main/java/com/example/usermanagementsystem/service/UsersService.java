package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.exceptions.UsersException;
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
        try {
            return usersRepository.save(users);
        } catch (UsersException e) {
            throw new UsersException(e.getMessage());
        }
    }

    @Override
    public List<Users> findAll() {
        try {
            return usersRepository.findAll();
        } catch (UsersException e) {
            throw new UsersException(e.getMessage());
        }
    }

    @Override
    public Optional<Users> findById(Long id) {
        try {
            return usersRepository.findById(id);
        } catch (UsersException e) {
            throw new UsersException(e.getMessage());
        }
    }

    @Override
    public Users updateUsers(Long id, Users users) {
        try {
            Optional<Users> usersOptional = usersRepository.findById(id);
            if (usersOptional.isPresent()) {
                Users usersExist = usersOptional.get();
                usersExist.setName(users.getName());
                usersExist.setEmail(users.getEmail());
                usersExist.setDepartament(users.getDepartament());
                return usersRepository.save(usersExist);
            }
            return null;
        } catch (UsersException e) {
            throw new UsersException(e.getMessage());
        }
    }

    @Override
    public void deleteUsers(Long id) {
        try {
            usersRepository.deleteById(id);
        } catch (UsersException e) {
            throw new UsersException(e.getMessage());
        }
    }
}

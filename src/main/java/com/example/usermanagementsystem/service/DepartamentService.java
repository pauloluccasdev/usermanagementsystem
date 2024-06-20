package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.repository.DepartamentReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepartamentService implements DepartamentServiceInterface{

    @Autowired
    private DepartamentReposiotry departamentReposiotry;

    @Override
    public Optional<Departament> findById(Long id) {
        return departamentReposiotry.findById(id);
    }
}

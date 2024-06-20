package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;

import java.util.Optional;

public interface DepartamentServiceInterface {

    Optional<Departament> findById(Long id);

}

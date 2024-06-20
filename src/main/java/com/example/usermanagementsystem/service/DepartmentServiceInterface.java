package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentServiceInterface {

    Optional<Departament> findById(Long id);

    Optional<Departament> findByName(String name);

    Departament createDepartament(Departament department);

    List<Departament> findAll();
}

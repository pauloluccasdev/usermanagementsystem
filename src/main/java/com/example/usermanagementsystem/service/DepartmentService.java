package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepartmentService implements DepartmentServiceInterface {

    @Autowired
    private DepartmentRepository departamentRepository;

    @Override
    public Optional<Departament> findById(Long id) {
        return departamentRepository.findById(id);
    }

    @Override
    public Optional<Departament> findByName(String name) {
        return departamentRepository.findByName(name);
    }

    @Override
    public Departament createDepartament(Departament department) {
        return departamentRepository.save(department);
    }
}

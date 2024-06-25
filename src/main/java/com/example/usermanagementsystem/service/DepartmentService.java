package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.exceptions.DepartmentException;
import com.example.usermanagementsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService implements DepartmentServiceInterface {

    @Autowired
    private DepartmentRepository departamentRepository;

    @Override
    public Optional<Departament> findById(Long id) {
        try {
            return Optional.ofNullable(departamentRepository.findById(id)
                    .orElseThrow(() -> new DepartmentException("Department not found with id: " + id)));
        } catch (DepartmentException e) {
            throw new DepartmentException(e.getMessage());
        }
    }

    @Override
    public Optional<Departament> findByName(String name) {
        try {
            return Optional.ofNullable(departamentRepository.findByName(name)
                    .orElseThrow(() -> new DepartmentException("Department not found with name: " + name)));
        } catch (DepartmentException e) {
            throw new DepartmentException(e.getMessage());
        }
    }

    @Override
    public Departament createDepartament(Departament department) {
        try {
            return departamentRepository.save(department);
        } catch (DepartmentException e) {
            throw new DepartmentException(e.getMessage());
        }
    }

    @Override
    public List<Departament> findAll() {
        try {
            return departamentRepository.findAll();
        } catch (DepartmentException e) {
            throw new DepartmentException(e.getMessage());
        }
    }
}

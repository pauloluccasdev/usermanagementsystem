package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Departament, Long> {
    Optional<Departament> findByName(String name);
}

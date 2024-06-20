package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentReposiotry extends JpaRepository<Departament, Long> {
}

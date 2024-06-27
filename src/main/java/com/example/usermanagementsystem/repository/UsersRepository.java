package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByDepartmentId(Long departmentId);
}

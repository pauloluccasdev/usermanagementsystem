package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.UsersDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.service.DepartmentServiceInterface;
import com.example.usermanagementsystem.service.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersServiceInterface usersServiceInterface;

    @Autowired
    private DepartmentServiceInterface departmentServiceInterface;

    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody UsersDto usersDto) {
        Departament department = departmentServiceInterface.findById(usersDto.getDepartamentoId())
                    .orElseThrow(() -> new RuntimeException("Department not found."));

        Users users = new Users();
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setDepartament(department);
        return ResponseEntity.ok(usersServiceInterface.createUsers(users));
    }

    @GetMapping
    public List<Users> findAll() {
        return usersServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        return usersServiceInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable Long id, @RequestBody UsersDto usersDto) {
        Departament department = departmentServiceInterface.findById(usersDto.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Department not found."));

        Users users = new Users();
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setDepartament(department);

        Users usersUpdated = usersServiceInterface.updateUsers(id, users);
        if (usersUpdated != null) {
            return ResponseEntity.ok(usersUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        usersServiceInterface.deleteUsers(id);
        return ResponseEntity.noContent().build();
    }
}

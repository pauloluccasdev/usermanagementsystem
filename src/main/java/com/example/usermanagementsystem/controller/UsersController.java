package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.UsersDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.service.DepartamentServiceInterface;
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
    private DepartamentServiceInterface departamentServiceInterface;

    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody UsersDto usersDto) {
        Departament departament = departamentServiceInterface.findById(usersDto.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado."));

        Users users = new Users();
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setDepartament(departament);
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
}

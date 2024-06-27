package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.DepartmentDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.exceptions.DepartmentException;
import com.example.usermanagementsystem.service.DepartmentServiceInterface;
import com.example.usermanagementsystem.service.UsersServiceInterface;
import com.example.usermanagementsystem.utils.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departament")
public class DepartmentController {

    @Autowired
    private DepartmentServiceInterface departmentServiceInterface;

    @Autowired
    private UsersServiceInterface usersServiceInterface;

    @PostMapping
    public ResponseEntity<Departament> createDepartament(@RequestBody DepartmentDto departmentDto) {
        Optional<Departament> departmentExist = departmentServiceInterface.findByName(departmentDto.getName());

        if(departmentExist.isPresent()) {
            throw new DepartmentException("Name for an existing department.");
        }

        Departament newDepartament = new Departament();
        newDepartament.setName(departmentDto.getName());
        return ResponseEntity.ok(departmentServiceInterface.createDepartament(newDepartament));
    }

    @GetMapping
    public List<Departament> findAll() {
        return departmentServiceInterface.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departament> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {

        Departament department = new Departament();
        department.setId(id);
        department.setName(departmentDto.getName());

        Departament departmentUpdated = departmentServiceInterface.updateDepartment(id, department);
        if (departmentUpdated != null) {
            return ResponseEntity.ok(departmentUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable Long id) {
        Departament departament = departmentServiceInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()).getBody();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(departament.getName());

        return ResponseEntity.ok(departmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        List<Users> users = usersServiceInterface.findByDepartmentId(id);
        if(!users.isEmpty()) {
            throw new DepartmentException("Departamento vinculado há um cliente.");
        }
        departmentServiceInterface.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}

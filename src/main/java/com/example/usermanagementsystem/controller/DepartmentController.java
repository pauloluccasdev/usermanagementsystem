package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.DepartmentDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.service.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api/departament")
public class DepartmentController {

    @Autowired
    private DepartmentServiceInterface departmentServiceInterface;

    @PostMapping
    public ResponseEntity<Departament> createDepartament(@RequestBody DepartmentDto departmentDto) {
        Optional<Departament> departmentExist = departmentServiceInterface.findByName(departmentDto.getName());

        if(departmentExist.isPresent()) {
            throw new RuntimeException("Name for an existing department.");
        }

        Departament newDepartament = new Departament();
        newDepartament.setName(departmentDto.getName());
        return ResponseEntity.ok(departmentServiceInterface.createDepartament(newDepartament));
    }
}

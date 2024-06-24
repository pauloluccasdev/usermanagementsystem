package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.DepartmentDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.exceptions.DepartmentException;
import com.example.usermanagementsystem.service.DepartmentServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentServiceInterface departmentServiceInterface;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepartament_WhenDepartmentExists() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("HR");

        when(departmentServiceInterface.findByName("HR")).thenReturn(Optional.of(new Departament()));

        DepartmentException exception = assertThrows(DepartmentException.class, () -> {
            departmentController.createDepartament(departmentDto);
        });

        assertEquals("Name for an existing department.", exception.getMessage());
        verify(departmentServiceInterface, times(1)).findByName("HR");
        verify(departmentServiceInterface, never()).createDepartament(any(Departament.class));
    }

    @Test
    public void testCreateDepartament_WhenDepartmentDoesNotExist() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Finance");

        when(departmentServiceInterface.findByName("Finance")).thenReturn(Optional.empty());

        Departament departament = new Departament();
        departament.setName("Finance");

        when(departmentServiceInterface.createDepartament(any(Departament.class))).thenReturn(departament);

        ResponseEntity<Departament> responseEntity = departmentController.createDepartament(departmentDto);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Finance", responseEntity.getBody().getName());

        verify(departmentServiceInterface, times(1)).findByName("Finance");
        verify(departmentServiceInterface, times(1)).createDepartament(any(Departament.class));
    }

    @Test
    public void testFindAll() {
        List<Departament> departments = new ArrayList<>();
        departments.add(new Departament());
        departments.add(new Departament());

        when(departmentServiceInterface.findAll()).thenReturn(departments);

        List<Departament> result = departmentController.findAll();

        assertEquals(2, result.size());
        verify(departmentServiceInterface, times(1)).findAll();
    }
}

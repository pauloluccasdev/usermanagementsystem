package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.DepartmentDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.exceptions.DepartmentException;
import com.example.usermanagementsystem.service.DepartmentServiceInterface;
import com.example.usermanagementsystem.service.UsersServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
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

    @Mock
    private UsersServiceInterface usersServiceInterface;

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

    @Test
    public void testUpdateDepartment_Success() {
        Long id = 1L;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Updated Department");

        Departament department = new Departament();
        department.setId(id);
        department.setName(departmentDto.getName());

        when(departmentServiceInterface.updateDepartment(eq(id), any(Departament.class))).thenReturn(department);

        ResponseEntity<Departament> response = departmentController.updateDepartment(id, departmentDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(departmentDto.getName(), response.getBody().getName());
    }

    @Test
    public void testUpdateDepartment_NotFound() {
        Long id = 1L;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Updated Department");

        when(departmentServiceInterface.updateDepartment(eq(id), any(Departament.class))).thenReturn(null);

        ResponseEntity<Departament> response = departmentController.updateDepartment(id, departmentDto);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testFindById_Success() {
        Long id = 1L;
        Departament department = new Departament();
        department.setId(id);
        department.setName("Test Department");

        when(departmentServiceInterface.findById(id)).thenReturn(Optional.of(department));

        ResponseEntity<DepartmentDto> response = departmentController.findById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(department.getName(), response.getBody().getName());
    }

    @Test
    public void testFindById_NotFound() {
        Long id = 1L;

        when(departmentServiceInterface.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<DepartmentDto> response = departmentController.findById(id);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteDepartment_Success() {
        Long id = 1L;

        when(usersServiceInterface.findByDepartmentId(id)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = departmentController.deleteDepartment(id);

        verify(departmentServiceInterface, times(1)).deleteDepartment(id);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteDepartment_DepartmentHasUsers() {
        Long id = 1L;
        Users user = new Users();
        user.setId(1L);

        when(usersServiceInterface.findByDepartmentId(id)).thenReturn(List.of(user));

        DepartmentException exception = assertThrows(DepartmentException.class, () -> {
            departmentController.deleteDepartment(id);
        });

        assertEquals("Departamento vinculado há um cliente.", exception.getMessage());
        verify(departmentServiceInterface, never()).deleteDepartment(id);
    }
}

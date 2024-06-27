package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.exceptions.DepartmentException;
import com.example.usermanagementsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findById_Success() {
        Long id = 1L;
        Departament department = new Departament();
        department.setId(id);
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        Optional<Departament> result = departmentService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }


    @Test
    public void findById_NotFound() {
        Long id = 1L;
        when(departmentRepository.findById(id)).thenReturn(Optional.empty());

        DepartmentException exception = assertThrows(DepartmentException.class, () -> departmentService.findById(id));
        assertEquals("Department not found with id: 1", exception.getMessage());
    }



    @Test
    public void findByName_Success() {
        String name = "Finance";
        Departament department = new Departament();
        department.setName(name);
        when(departmentRepository.findByName(name)).thenReturn(Optional.of(department));

        Optional<Departament> result = Optional.ofNullable(departmentService.findByName(name));

        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }

    @Test
    public void findByName_NotFound() {
        String name = "Finance";
        when(departmentRepository.findByName(name)).thenReturn(Optional.empty());

        assertThrows(DepartmentException.class, () -> departmentService.findByName(name));
    }

    @Test
    public void createDepartament_Success() {
        Departament department = new Departament();
        when(departmentRepository.save(department)).thenReturn(department);

        Departament result = departmentService.createDepartament(department);

        assertNotNull(result);
        assertEquals(department, result);
    }

    @Test
    public void findAll_Success() {
        List<Departament> departments = new ArrayList<>();
        departments.add(new Departament());
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Departament> result = departmentService.findAll();

        assertNotNull(result);
        assertEquals(departments.size(), result.size());
    }
}

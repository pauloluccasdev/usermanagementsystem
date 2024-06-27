package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.exceptions.DepartmentException;
import com.example.usermanagementsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private Departament department;

    @BeforeEach
    public void setUp() {
        department = new Departament();
        department.setId(1L);
        department.setName("Finance");
    }

    @Test
    public void testFindById_Success() {
        Long id = 1L;
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        Optional<Departament> foundDepartment = departmentService.findById(id);

        assertTrue(foundDepartment.isPresent());
        assertEquals(department, foundDepartment.get());
    }

    @Test
    public void testFindById_ThrowsDepartmentException() {
        Long id = 1L;
        when(departmentRepository.findById(id)).thenThrow(new DepartmentException("Error fetching department"));

        assertThrows(DepartmentException.class, () -> departmentService.findById(id));
    }

    @Test
    public void testFindByName_Success() {
        String name = "Finance";
        when(departmentRepository.findByName(name)).thenReturn(Optional.of(department));

        Optional<Departament> foundDepartment = departmentService.findByName(name);

        assertTrue(foundDepartment.isPresent());
        assertEquals(department, foundDepartment.get());
    }

    @Test
    public void testFindByName_ThrowsDepartmentException() {
        String name = "Finance";
        when(departmentRepository.findByName(name)).thenThrow(new DepartmentException("Error fetching department"));

        assertThrows(DepartmentException.class, () -> departmentService.findByName(name));
    }

    @Test
    public void testCreateDepartament_Success() {
        when(departmentRepository.save(department)).thenReturn(department);

        Departament createdDepartment = departmentService.createDepartament(department);

        assertEquals(department, createdDepartment);
    }

    @Test
    public void testCreateDepartament_ThrowsDepartmentException() {
        when(departmentRepository.save(department)).thenThrow(new DepartmentException("Error saving department"));

        assertThrows(DepartmentException.class, () -> departmentService.createDepartament(department));
    }

    @Test
    public void testFindAll_Success() {
        List<Departament> departments = Arrays.asList(department);
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Departament> foundDepartments = departmentService.findAll();

        assertEquals(departments, foundDepartments);
    }

    @Test
    public void testFindAll_ThrowsDepartmentException() {
        when(departmentRepository.findAll()).thenThrow(new DepartmentException("Error fetching departments"));

        assertThrows(DepartmentException.class, () -> departmentService.findAll());
    }

    @Test
    public void testUpdateDepartment_Success() {
        Long id = 1L;
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
        when(departmentRepository.save(department)).thenReturn(department);

        Departament updatedDepartment = departmentService.updateDepartment(id, department);

        assertEquals(department, updatedDepartment);
    }

    @Test
    public void testUpdateDepartment_ThrowsDepartmentException() {
        Long id = 1L;
        when(departmentRepository.findById(id)).thenThrow(new DepartmentException("Error fetching department"));

        assertThrows(DepartmentException.class, () -> departmentService.updateDepartment(id, department));
    }

    @Test
    public void testDeleteDepartment_Success() {
        Long id = 1L;

        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteDepartment_ThrowsDepartmentException() {
        Long id = 1L;
        doThrow(new DepartmentException("Error deleting department")).when(departmentRepository).deleteById(id);

        assertThrows(DepartmentException.class, () -> departmentService.deleteDepartment(id));
    }
}

package com.example.usermanagementsystem.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentDtoTest {

    @Test
    public void testGetName() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("HR");

        assertEquals("HR", departmentDto.getName());
    }

    @Test
    public void testSetName() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Finance");

        assertEquals("Finance", departmentDto.getName());
    }
}
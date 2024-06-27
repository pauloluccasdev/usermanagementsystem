package com.example.usermanagementsystem.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentTest {

    @Test
    public void testGetId() {
        Departament departament = new Departament();
        departament.setId(1L);

        assertEquals(1L, departament.getId());
    }

    @Test
    public void testSetId() {
        Departament departament = new Departament();
        departament.setId(2L);

        assertEquals(2L, departament.getId());
    }

    @Test
    public void testGetName() {
        Departament departament = new Departament();
        departament.setName("Finance");

        assertEquals("Finance", departament.getName());
    }

    @Test
    public void testSetName() {
        Departament departament = new Departament();
        departament.setName("HR");

        assertEquals("HR", departament.getName());
    }
}

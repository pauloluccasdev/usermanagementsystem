package com.example.usermanagementsystem.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest {

    @Test
    public void testGetId() {
        Users user = new Users();
        user.setId(1L);

        assertEquals(1L, user.getId());
    }

    @Test
    public void testSetId() {
        Users user = new Users();
        user.setId(2L);

        assertEquals(2L, user.getId());
    }

    @Test
    public void testGetName() {
        Users user = new Users();
        user.setName("John Doe");

        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testSetName() {
        Users user = new Users();
        user.setName("Jane Doe");

        assertEquals("Jane Doe", user.getName());
    }

    @Test
    public void testGetEmail() {
        Users user = new Users();
        user.setEmail("john.doe@example.com");

        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        Users user = new Users();
        user.setEmail("jane.doe@example.com");

        assertEquals("jane.doe@example.com", user.getEmail());
    }

    @Test
    public void testGetDepartament() {
        Users user = new Users();
        Departament department = new Departament();
        department.setName("Finance");
        user.setDepartament(department);

        assertEquals(department, user.getDepartament());
    }

    @Test
    public void testSetDepartament() {
        Users user = new Users();
        Departament department = new Departament();
        department.setName("HR");
        user.setDepartament(department);

        assertEquals(department, user.getDepartament());
    }
}

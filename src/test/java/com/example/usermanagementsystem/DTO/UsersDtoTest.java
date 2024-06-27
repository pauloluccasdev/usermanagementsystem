package com.example.usermanagementsystem.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersDtoTest {

    @Test
    public void testGetName() {
        UsersDto usersDto = new UsersDto();
        usersDto.setName("User Test");

        assertEquals("User Test", usersDto.getName());
    }

    @Test
    public void testSetName() {
        UsersDto usersDto = new UsersDto();
        usersDto.setName("User Test");

        assertEquals("User Test", usersDto.getName());
    }

    @Test
    public void testGetEmail() {
        UsersDto usersDto = new UsersDto();
        usersDto.setEmail("user.test@example.com");

        assertEquals("user.test@example.com", usersDto.getEmail());
    }

    @Test
    public void testSetEmail() {
        UsersDto usersDto = new UsersDto();
        usersDto.setEmail("user.tests@example.com");

        assertEquals("user.tests@example.com", usersDto.getEmail());
    }

    @Test
    public void testgetDepartmentId() {
        UsersDto usersDto = new UsersDto();
        usersDto.setDepartamentoId(1L);

        assertEquals(1L, usersDto.getDepartmentId());
    }

    @Test
    public void testSetDepartamentoId() {
        UsersDto usersDto = new UsersDto();
        usersDto.setDepartamentoId(2L);

        assertEquals(2L, usersDto.getDepartmentId());
    }
}
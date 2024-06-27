package com.example.usermanagementsystem.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersResponseDtoTest {

    @Test
    public void testGettersAndSetters() {
        UsersResponseDto usersResponseDto = new UsersResponseDto();

        // Test setters
        usersResponseDto.setName("John Doe");
        usersResponseDto.setEmail("john.doe@example.com");
        usersResponseDto.setDepartmentName("IT");

        // Test getters
        assertEquals("John Doe", usersResponseDto.getName());
        assertEquals("john.doe@example.com", usersResponseDto.getEmail());
        assertEquals("IT", usersResponseDto.getDepartmentName());
    }
}

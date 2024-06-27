package com.example.usermanagementsystem.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsersExceptionTest {

    @Test
    public void testUsersExceptionMessage() {
        String errorMessage = "User not found";
        UsersException exception = assertThrows(UsersException.class, () -> {
            throw new UsersException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
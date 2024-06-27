package com.example.usermanagementsystem.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepartmentExceptionTest {

    @Test
    public void testDepartmentExceptionMessage() {
        String errorMessage = "Department already exists";
        DepartmentException exception = assertThrows(DepartmentException.class, () -> {
            throw new DepartmentException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}

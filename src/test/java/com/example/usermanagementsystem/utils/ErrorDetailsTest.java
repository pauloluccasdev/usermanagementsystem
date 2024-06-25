package com.example.usermanagementsystem.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ErrorDetailsTest {

    @Test
    public void testErrorDetailsConstructorAndGetters() {
        Date timestamp = new Date();
        String message = "Error message";
        String details = "Error details";

        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        assertNotNull(errorDetails);
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    public void testErrorDetailsGetters() {
        Date timestamp = new Date();
        String message = "Another error message";
        String details = "Another error details";

        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        assertNotNull(errorDetails);
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }
}
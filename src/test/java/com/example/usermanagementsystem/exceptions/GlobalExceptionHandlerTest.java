package com.example.usermanagementsystem.exceptions;

import com.example.usermanagementsystem.utils.ErrorDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest request;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        request = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    public void testHandleUsersException() {
        UsersException usersException = new UsersException("User not found");
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handleUsersException(usersException, request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleDepartmentException() {
        DepartmentException departmentException = new DepartmentException("Department already exists");
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handleDepartmentException(departmentException, request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Department already exists", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleGlobalException() {
        Exception exception = new Exception("Generic error");
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handleGlobalException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Generic error", responseEntity.getBody().getMessage());
    }
}

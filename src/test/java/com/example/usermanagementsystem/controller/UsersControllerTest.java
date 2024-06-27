package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.DTO.UsersDto;
import com.example.usermanagementsystem.DTO.UsersResponseDto;
import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.exceptions.UsersException;
import com.example.usermanagementsystem.service.DepartmentServiceInterface;
import com.example.usermanagementsystem.service.UsersServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsersControllerTest {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersServiceInterface usersServiceInterface;

    @Mock
    private DepartmentServiceInterface departmentServiceInterface;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUsers_WhenDepartmentExists() {
        UsersDto usersDto = new UsersDto();
        usersDto.setName("User Test");
        usersDto.setEmail("user.test@example.com");
        usersDto.setDepartmentId(1L);

        Departament department = new Departament();
        department.setId(1L);

        when(departmentServiceInterface.findById(1L)).thenReturn(Optional.of(department));

        Users users = new Users();
        users.setName("User Test");
        users.setEmail("user.test@example.com");
        users.setDepartament(department);

        when(usersServiceInterface.createUsers(any(Users.class))).thenReturn(users);

        ResponseEntity<Users> responseEntity = usersController.createUsers(usersDto);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("User Test", responseEntity.getBody().getName());

        verify(departmentServiceInterface, times(1)).findById(1L);
        verify(usersServiceInterface, times(1)).createUsers(any(Users.class));
    }

    @Test
    public void testCreateUsers_WhenDepartmentNotFound() {
        UsersDto usersDto = new UsersDto();
        usersDto.setName("User Test");
        usersDto.setEmail("user.test@example.com");
        usersDto.setDepartmentId(1L);

        when(departmentServiceInterface.findById(1L)).thenReturn(Optional.empty());

        UsersException exception = assertThrows(UsersException.class, () -> {
            usersController.createUsers(usersDto);
        });

        assertEquals("Department not found.", exception.getMessage());
        verify(departmentServiceInterface, times(1)).findById(1L);
        verify(usersServiceInterface, never()).createUsers(any(Users.class));
    }

    @Test
    public void testFindAll() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());
        usersList.add(new Users());

        when(usersServiceInterface.findAll()).thenReturn(usersList);

        List<Users> result = usersController.findAll();

        assertEquals(2, result.size());
        verify(usersServiceInterface, times(1)).findAll();
    }

    @Test
    public void testFindById_WhenUserExists() {
        Users user = new Users();
        user.setId(1L);

        when(usersServiceInterface.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<UsersResponseDto> responseEntity = usersController.findById(1L);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1L, responseEntity.getBody().getName());

        verify(usersServiceInterface, times(1)).findById(1L);
    }

    @Test
    public void testFindById_WhenUserNotExists() {
        when(usersServiceInterface.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<UsersResponseDto> responseEntity = usersController.findById(1L);

        assertNotNull(responseEntity);
        assertEquals(404, responseEntity.getStatusCodeValue());

        verify(usersServiceInterface, times(1)).findById(1L);
    }

    @Test
    public void testUpdateUsers_WhenDepartmentExists() {
        UsersDto usersDto = new UsersDto();
        usersDto.setName("User Test Updated");
        usersDto.setEmail("user.test.updated@example.com");
        usersDto.setDepartmentId(1L);

        Departament department = new Departament();
        department.setId(1L);

        when(departmentServiceInterface.findById(1L)).thenReturn(Optional.of(department));

        Users user = new Users();
        user.setName("User Test Updated");
        user.setEmail("user.test.updated@example.com");
        user.setDepartament(department);

        when(usersServiceInterface.updateUsers(eq(1L), any(Users.class))).thenReturn(user);

        ResponseEntity<Users> responseEntity = usersController.updateUsers(1L, usersDto);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("User Test Updated", responseEntity.getBody().getName());

        verify(departmentServiceInterface, times(1)).findById(1L);
        verify(usersServiceInterface, times(1)).updateUsers(eq(1L), any(Users.class));
    }

    @Test
    public void testUpdateUsers_WhenDepartmentNotFound() {
        UsersDto usersDto = new UsersDto();
        usersDto.setName("User Test Updated");
        usersDto.setEmail("user.test.updated@example.com");
        usersDto.setDepartmentId(1L);

        when(departmentServiceInterface.findById(1L)).thenReturn(Optional.empty());

        UsersException exception = assertThrows(UsersException.class, () -> {
            usersController.updateUsers(1L, usersDto);
        });

        assertEquals("Department not found.", exception.getMessage());
        verify(departmentServiceInterface, times(1)).findById(1L);
        verify(usersServiceInterface, never()).updateUsers(anyLong(), any(Users.class));
    }

    @Test
    public void testDeleteUsers() {
        doNothing().when(usersServiceInterface).deleteUsers(1L);

        ResponseEntity<Void> responseEntity = usersController.deleteUsers(1L);

        assertNotNull(responseEntity);
        assertEquals(204, responseEntity.getStatusCodeValue());

        verify(usersServiceInterface, times(1)).deleteUsers(1L);
    }
}

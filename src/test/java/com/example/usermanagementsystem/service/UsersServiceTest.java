package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.entity.Departament;
import com.example.usermanagementsystem.entity.Users;
import com.example.usermanagementsystem.exceptions.UsersException;
import com.example.usermanagementsystem.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUsers_Success() {
        Users users = new Users();
        users.setName("User Test");
        users.setEmail("user.test@example.com");

        when(usersRepository.save(users)).thenReturn(users);

        Users result = usersService.createUsers(users);

        assertNotNull(result);
        assertEquals("User Test", result.getName());
        assertEquals("user.test@example.com", result.getEmail());
    }

    @Test
    public void createUsers_Exception() {
        Users users = new Users();
        users.setName("User Test");
        users.setEmail("user.test@example.com");

        when(usersRepository.save(users)).thenThrow(new UsersException("Error creating user"));

        UsersException exception = assertThrows(UsersException.class, () -> usersService.createUsers(users));
        assertEquals("Error creating user", exception.getMessage());
    }

    @Test
    public void findAll_Success() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());

        when(usersRepository.findAll()).thenReturn(usersList);

        List<Users> result = usersService.findAll();

        assertNotNull(result);
        assertEquals(usersList.size(), result.size());
    }

    @Test
    public void findAll_Exception() {
        when(usersRepository.findAll()).thenThrow(new UsersException("Error finding users"));

        UsersException exception = assertThrows(UsersException.class, () -> usersService.findAll());
        assertEquals("Error finding users", exception.getMessage());
    }

    @Test
    public void findById_Success() {
        Long id = 1L;
        Users users = new Users();
        users.setId(id);
        when(usersRepository.findById(id)).thenReturn(Optional.of(users));

        Optional<Users> result = usersService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void findById_NotFound() {
        Long id = 1L;
        when(usersRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Users> result = usersService.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    public void findById_Exception() {
        Long id = 1L;
        when(usersRepository.findById(id)).thenThrow(new UsersException("Error finding user by id"));

        UsersException exception = assertThrows(UsersException.class, () -> usersService.findById(id));
        assertEquals("Error finding user by id", exception.getMessage());
    }

    @Test
    public void updateUsers_Success() {
        Long id = 1L;
        Users users = new Users();
        users.setName("User Test");
        users.setEmail("user.test@example.com");
        Departament department = new Departament();
        department.setName("HR");
        users.setDepartament(department);

        Users existingUser = new Users();
        existingUser.setId(id);
        when(usersRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(usersRepository.save(existingUser)).thenReturn(existingUser);

        Users result = usersService.updateUsers(id, users);

        assertNotNull(result);
        assertEquals("User Test", result.getName());
        assertEquals("user.test@example.com", result.getEmail());
        assertEquals(department, result.getDepartament());
    }

    @Test
    public void updateUsers_NotFound() {
        Long id = 1L;
        Users users = new Users();
        users.setName("User Test");
        users.setEmail("user.test@example.com");

        when(usersRepository.findById(id)).thenReturn(Optional.empty());

        Users result = usersService.updateUsers(id, users);

        assertNull(result);
    }

    @Test
    public void updateUsers_Exception() {
        Long id = 1L;
        Users users = new Users();
        users.setName("User Test");
        users.setEmail("user.test@example.com");

        when(usersRepository.findById(id)).thenThrow(new UsersException("Error updating user"));

        UsersException exception = assertThrows(UsersException.class, () -> usersService.updateUsers(id, users));
        assertEquals("Error updating user", exception.getMessage());
    }

    @Test
    public void deleteUsers_Success() {
        Long id = 1L;

        doNothing().when(usersRepository).deleteById(id);

        assertDoesNotThrow(() -> usersService.deleteUsers(id));
    }

    @Test
    public void deleteUsers_Exception() {
        Long id = 1L;

        doThrow(new UsersException("Error deleting user")).when(usersRepository).deleteById(id);

        UsersException exception = assertThrows(UsersException.class, () -> usersService.deleteUsers(id));
        assertEquals("Error deleting user", exception.getMessage());
    }
}

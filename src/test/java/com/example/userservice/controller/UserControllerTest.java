package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;  // Mocking Repository

    @InjectMocks
    private UserController userController;  // Injecting Mock into Controller

    @Test
    void testGetAllUsers() {
        // ✅ Fix: Use UserDTO instead of User
        List<UserDTO> users = Arrays.asList(
                new UserDTO("Alice", "alice@example.com"),
                new UserDTO("Bob", "bob@example.com")
        );

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userController.getAllUsers();
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    void testCreateUser() {
        // ✅ Fix: Use UserDTO instead of User
        UserDTO user = new UserDTO("Charlie", "charlie@example.com");
        when(userRepository.save(user)).thenReturn(user);

        UserDTO savedUser = userController.createUser(user);
        assertEquals("Charlie", savedUser.getName());
    }

    @Test
    void testDeleteUser() {
        // ✅ Fix: Use UserDTO instead of User
        UserDTO user = new UserDTO("Dave", "dave@example.com");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        ResponseEntity<String> response = userController.deleteUser(1L);
        
        // ✅ Fix deprecated method
        assertEquals(200, response.getStatusCode().value());
    }
}
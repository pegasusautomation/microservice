package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userRepository.save(user);
    }

@PutMapping("/{id}")
public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
    return userRepository.findById(id)
        .map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            return ResponseEntity.ok(user);  // ✅ Returns updated user object
        })
        .orElseGet(() -> ResponseEntity.notFound().build());  // ✅ Returns 404 NOT FOUND
}

    
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    return userRepository.findById(id)
        .map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok("User deleted successfully"); // Fixed typo
        })
        .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if user not found
}

}

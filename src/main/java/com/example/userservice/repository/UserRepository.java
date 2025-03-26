package com.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
    // You can define any custom queries if needed
}

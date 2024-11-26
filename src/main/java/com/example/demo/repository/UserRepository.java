package com.example.demo.repository;

import com.example.demo.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);
}

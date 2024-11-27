package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    User updateUser(Long id, User updatedUser);

    List<UserDto> findAllUsers();

    void deleteUserByEmail(String email);
}

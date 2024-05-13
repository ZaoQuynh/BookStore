package com.example.BookStore.service;

import com.example.BookStore.dto.UserDTO;

import java.util.List;

public interface UserService {
    Long getCurrentUserId();
    UserDTO findById(Long currentUserId);
    List<UserDTO> findAll();
    UserDTO save(UserDTO userDTO);
}

package com.example.BookStore.service;

import com.example.BookStore.dto.UserDTO;

public interface UserService {
    Long getCurrentUserId();
    UserDTO findById(Long currentUserId);
}

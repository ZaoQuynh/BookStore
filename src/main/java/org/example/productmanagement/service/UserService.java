package org.example.productmanagement.service;

import org.example.productmanagement.dto.UserDTO;

public interface UserService {
    int getCurrentUserId();
    UserDTO findById(int currentUserId);
}

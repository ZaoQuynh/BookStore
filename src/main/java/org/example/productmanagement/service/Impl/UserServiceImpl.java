package org.example.productmanagement.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.productmanagement.dto.UserDTO;
import org.example.productmanagement.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public int getCurrentUserId() {
        return 1;
    }

    @Override
    public UserDTO findById(int currentUserId) {
        return null;
    }
}

package com.example.BookStore.service.impl;

import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.entity.User;
import com.example.BookStore.exception.UserNotFoundException;
import com.example.BookStore.mapper.UserMapper;
import com.example.BookStore.repos.UserRepos;
import com.example.BookStore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepos repos;

    @Autowired
    private UserMapper mapper;

    @Override
    public Long getCurrentUserId() {
        try {
            return Long.valueOf(1);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDTO findById(Long id) {
        User user = repos
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Could not find any user with Id=" + id));
        return mapper.toDto(user);
    }
}

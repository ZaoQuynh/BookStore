package com.example.BookStore.service.impl;

import com.example.BookStore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public Long getCurrentUserId() {
        try {
            return Long.valueOf(1);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

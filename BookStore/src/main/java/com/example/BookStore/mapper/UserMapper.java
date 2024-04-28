package com.example.BookStore.mapper;

import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<User, UserDTO> {

    @Override
    protected Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected void configuration() {

    }
}

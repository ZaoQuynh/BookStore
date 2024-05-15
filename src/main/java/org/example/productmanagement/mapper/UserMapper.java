package org.example.productmanagement.mapper;

import org.example.productmanagement.dto.UserDTO;
import org.example.productmanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<User, UserDTO> {
    @Override
    protected Class getDtoClass() {
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

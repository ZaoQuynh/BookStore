package com.example.BookStore.dto;

import com.example.BookStore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private User.EGender gender;
    private Date birthday;
    private String email;
    private User.ERole role;
    private boolean isEnabled = false;
    private boolean isBlocked = false;
    private boolean isDeleted = false;
}

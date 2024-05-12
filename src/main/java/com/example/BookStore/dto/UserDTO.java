package com.example.BookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private Date birthday;
    private String email;
    private String role;
    private boolean isEnabled = false;
    private boolean isBlocked = false;
    private boolean isDeleted = false;
}

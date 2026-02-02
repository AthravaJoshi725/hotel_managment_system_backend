package com.example.demo.model;

import com.example.demo.enums.AccountStatus;
import com.example.demo.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private UserRole role;
    private AccountStatus status;
}

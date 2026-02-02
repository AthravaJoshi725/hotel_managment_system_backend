package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.enums.AccountStatus;
import com.example.demo.enums.UserRole;
import com.example.demo.model.User;
import com.example.demo.util.JsonFileUtil;

import org.springframework.stereotype.Service;


import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class UserService {
    public User registerUser(RegisterRequest request, UserRole role){

        List<User> users = JsonFileUtil.readUsers();


        for(User existingUser : users){
            if(existingUser.getEmail().equals(request.getEmail())){
                return null;
            }
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setCustomerName(request.getCustomerName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCreatedAt(LocalDateTime.now());


        user.setRole(role);
        user.setStatus(AccountStatus.ACTIVE);

        users.add(user);
        JsonFileUtil.writeUsers(users);

        return user;
    }


    public User loginUser(LoginRequest request){

        List<User> users = JsonFileUtil.readUsers();

        for(User user : users){

            if(user.getEmail().equals(request.getEmail())){

                // 1. Check password
                if(!user.getPassword().equals(request.getPassword())){
                    return null;
                }

                // 2. Check account status
                if(user.getStatus() != AccountStatus.ACTIVE){
                    return null;
                }

                // 3. Successful login
                return user;
            }
        }
        return null;
    }

}

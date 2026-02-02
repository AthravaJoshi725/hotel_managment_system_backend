package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.util.JsonFileUtil;

import org.springframework.stereotype.Service;


import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class UserService {
    
    public User registerUser(RegisterRequest request){

        // Read existing users from json
        List<User> users = JsonFileUtil.readUsers();

        // Check for duplicate email
        for(User existingUser: users){
            if(existingUser.getEmail().equals(request.getEmail())){
                return null;
            }
        }

        // Craete new User class before storing
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setCustomerName(request.getCustomerName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCreatedAt(LocalDateTime.now());

        
        // add the new User to Json
        users.add(user);

        // update json with new list
        JsonFileUtil.writeUsers(users);

        return user;
    }

    public User loginUser(LoginRequest request){
        
        List<User> users = JsonFileUtil.readUsers();

        String loginMail = request.getEmail();
        String loginPassword = request.getPassword();

        for(User user: users){

            if(user.getEmail().equals(loginMail)){

                if(user.getPassword().equals(loginPassword)){
                    return user;
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }
}

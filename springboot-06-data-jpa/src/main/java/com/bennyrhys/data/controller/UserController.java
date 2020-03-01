package com.bennyrhys.data.controller;

import com.bennyrhys.data.entity.User;
import com.bennyrhys.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id){
        return userRepository.findById(id);
    }
    @GetMapping("/user")
    public User insertUser(User user){
        return userRepository.save(user);
    }

}

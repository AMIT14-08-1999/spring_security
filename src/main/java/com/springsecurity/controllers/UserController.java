package com.springsecurity.controllers;


import com.springsecurity.models.User;
import com.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    // @PreAuthorize("hasRole('ADMIN')")

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @PostMapping("/")
    public User add(@RequestBody User user){
        return this.userService.addUser(user);
    }
}

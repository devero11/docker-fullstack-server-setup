
package com.example.spring_boot_docker.controller;

import com.example.spring_boot_docker.model.User;
import com.example.spring_boot_docker.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService userService;

    public AuthController(AuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.authenticate(user.getUsername(), user.getPassword());
    }
    @GetMapping("/test")
    public String test() {
     return "OK";
    }
}

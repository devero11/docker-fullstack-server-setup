package com.example.spring_boot_docker.controller;
import java.util.Map;

import java.util.Collections;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
      String token = userService.register(user.getUsername(), user.getEmail(), user.getPassword());
      if (token == null) {
        throw new RuntimeException("Invalid credentials");
      }
      return Collections.singletonMap("token", token);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
      String token = userService.authenticate(user.getUsername(), user.getPassword());
      if (token == null) {
        throw new RuntimeException("Invalid credentials");
      }
      return Collections.singletonMap("token", token);
    }
        
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/test")
    public String test() {
     return "OK";
    }
}

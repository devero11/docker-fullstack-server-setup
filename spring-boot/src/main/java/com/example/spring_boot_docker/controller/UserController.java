package com.example.spring_boot_docker.controller;
import org.springframework.web.bind.annotation.*;
import com.example.spring_boot_docker.service.UserService;
import com.example.spring_boot_docker.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
   private final UserService userService;

   public UserController(UserService userService) {
       this.userService = userService;
   }

   @CrossOrigin(origins = "http://localhost:4200")
   @GetMapping("/username")
   public String username(@RequestHeader("Authorization") String token) {
      return userService.getCurrentUser(token).getUsername();
   }

}

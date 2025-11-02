package com.example.spring_boot_docker.service;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import com.example.spring_boot_docker.model.User;
import io.jsonwebtoken.Claims;
import com.example.spring_boot_docker.repository.UserRepository;
import com.example.spring_boot_docker.service.AuthService;
@Service
public class UserService {


  private final UserRepository userRepository;
  public UserService(UserRepository userRepository) {
     this.userRepository = userRepository;
  }


  public User getCurrentUser(String token) {
      if (token == null || !token.startsWith("Bearer ")) return null;
      token = token.substring(7); // remove "Bearer "

      Claims claims = Jwts.parserBuilder()   // new parser builder
        .setSigningKey(AuthService.key)            // use Key, not String
        .build()
        .parseClaimsJws(token)         // parse the token
        .getBody();

      Long userId = Long.parseLong(claims.getSubject());
      return userRepository.findById(userId).orElse(null);
  }
}


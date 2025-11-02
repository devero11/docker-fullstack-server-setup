
package com.example.spring_boot_docker.service;

import com.example.spring_boot_docker.model.User;
import com.example.spring_boot_docker.repository.UserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String email, String rawPassword) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        // Hash the password with bcrypt (automatically salted)
        String hashed = BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
        user.setPassword(hashed);

        return userRepository.save(user);
    }

    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) return false;

        // Verify password
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), user.getPassword());
        return result.verified;
    }
}


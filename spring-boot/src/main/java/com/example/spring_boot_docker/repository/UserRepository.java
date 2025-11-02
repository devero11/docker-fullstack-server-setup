
package com.example.spring_boot_docker.repository;

import com.example.spring_boot_docker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByEmail(String email);
}

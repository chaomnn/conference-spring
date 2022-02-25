package com.example.conferenceweb.democonf.repositories;

import com.example.conferenceweb.democonf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}

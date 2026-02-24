package com.videoRental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.videoRental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findByUsername(String username);
}


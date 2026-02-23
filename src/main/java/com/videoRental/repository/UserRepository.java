package com.videoRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.videoRental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}


package com.videoRental.controller;

import org.springframework.web.bind.annotation.*;
import com.videoRental.dto.AuthResponse;
import com.videoRental.dto.LoginRequestDTO;
import com.videoRental.dto.RegisterDTO;
import com.videoRental.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDTO request) {
        return ResponseEntity.ok(authService.register(request));
        
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
        
    }
}


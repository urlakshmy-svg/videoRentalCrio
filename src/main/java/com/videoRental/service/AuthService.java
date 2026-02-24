package com.videoRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.videoRental.dto.AuthResponse;
import com.videoRental.dto.LoginRequestDTO;
import com.videoRental.dto.RegisterDTO;
import com.videoRental.model.Role;
import com.videoRental.model.User;
import com.videoRental.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired 
    UserRepository userRepository;

    public AuthResponse register(RegisterDTO request) {
        if(request.getRole() == null) {
            request.setRole(Role.CUSTOMER);
        }
         User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(request.getRole())
                .build();

       userRepository.save(user);
        return AuthResponse.builder().build();
    }

    public String login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        String token = jwtService.generateToken(request.getEmail());
        return token;
    }
    
}

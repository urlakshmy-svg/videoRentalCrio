package com.videoRental.dto;

import com.videoRental.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String email;
    private String name;
     private Role role;
}

package com.videoRental.dto;

import com.videoRental.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String email;
    private String password;
    private String name;
    private Role role;
   
}

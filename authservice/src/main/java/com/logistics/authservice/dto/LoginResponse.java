package com.logistics.authservice.dto;

import com.logistics.authservice.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO for login response
public class LoginResponse {

    private String token;

    private String email;

    private String name;

    private Role role;
}

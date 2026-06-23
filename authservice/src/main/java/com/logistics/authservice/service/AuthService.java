package com.logistics.authservice.service;

import com.logistics.authservice.dto.LoginRequest;
import com.logistics.authservice.dto.LoginResponse;
import com.logistics.authservice.dto.RegisterRequest;
import com.logistics.authservice.dto.RegisterResponse;
// Service interface for authentication operations

public interface AuthService {
// Register a new user and return registration response

    RegisterResponse register(RegisterRequest request);

// Login an existing user and return login response
    LoginResponse login(LoginRequest request);
}

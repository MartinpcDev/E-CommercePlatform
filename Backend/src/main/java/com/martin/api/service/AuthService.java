package com.martin.api.service;

import com.martin.api.dto.request.LoginRequest;
import com.martin.api.dto.request.RegisterRequest;
import com.martin.api.dto.response.LoginResponse;
import com.martin.api.dto.response.RegisterResponse;

public interface AuthService {

  RegisterResponse register(RegisterRequest request);

  LoginResponse login(LoginRequest request);
}

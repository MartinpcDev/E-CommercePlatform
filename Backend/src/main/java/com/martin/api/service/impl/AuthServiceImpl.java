package com.martin.api.service.impl;

import com.martin.api.dto.request.LoginRequest;
import com.martin.api.dto.request.RegisterRequest;
import com.martin.api.dto.response.LoginResponse;
import com.martin.api.dto.response.RegisterResponse;
import com.martin.api.exception.InvalidAuthException;
import com.martin.api.persistence.model.User;
import com.martin.api.persistence.repository.UserRepository;
import com.martin.api.service.AuthService;
import com.martin.api.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;

  @Override
  public RegisterResponse register(RegisterRequest request) {
    if (userRepository.existsByUsernameIgnoreCase(request.username()) ||
        userRepository.existsByEmailIgnoreCase(request.email())) {
      throw new InvalidAuthException("El email o el username ya pertenecen a otro usuario");
    }

    User user = new User();
    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));

    userRepository.save(user);

    return new RegisterResponse("Registro completado Satisfactoriamente");
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    User userExists = userRepository.findByUsernameIgnoreCase(request.username())
        .orElseThrow(() -> new InvalidAuthException("El username no se encuentra registrado"));

    if (!passwordEncoder.matches(request.password(), userExists.getPassword())) {
      throw new InvalidAuthException("Password incorrecto");
    }

    String token = jwtUtils.generateToken(userExists);

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password())
    );

    return new LoginResponse(token);
  }
}

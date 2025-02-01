package com.example.demo.service.impl;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.AuthDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserService userService;
  private final JwtService jwtService;
  private final AuthenticationManager authManager;
  private final PasswordEncoder encoder;

  @Override
  public AccessTokenDto auth(AuthDto authDto) {
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(authDto.getIdentifier(), authDto.getPassword());
    authManager.authenticate(authentication);

    User user = userService.findByIdentifier(authDto.getIdentifier());
    String accessToken = jwtService.generateToken(user);

    return new AccessTokenDto(accessToken);
  }

  @Override
  public AccessTokenDto register(RegisterDto registerDto) {
    String password = registerDto.getPassword();
    registerDto.setPassword(encoder.encode(password));

    User user = userService.create(registerDto);
    String accessToken = jwtService.generateToken(user);

    return new AccessTokenDto(accessToken);
  }
}

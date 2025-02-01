package com.example.BFF.service.impl;

import com.example.BFF.dto.AccessTokenDto;
import com.example.BFF.dto.AuthDto;
import com.example.BFF.dto.RegisterDto;
import com.example.BFF.dto.UserDto;
import com.example.BFF.service.UserService;
import com.example.BFF.util.RequestUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final RequestUtils requestUtils;

  @Value("${service.user-service}")
  private String USER_SERVICE_URL;

  @Override
  public AccessTokenDto auth(AuthDto authDto) {
    return requestUtils.postRequest(
        USER_SERVICE_URL + "/auth/login", authDto, AccessTokenDto.class);
  }

  @Override
  public AccessTokenDto register(RegisterDto registerDto) {
    return requestUtils.postRequest(
        USER_SERVICE_URL + "/auth/register", registerDto, AccessTokenDto.class);
  }

  @Override
  public UserDto findById(UUID id) {
    return requestUtils.getRequest(USER_SERVICE_URL + "/users/{id}", UserDto.class, id);
  }

  @Override
  public UserDto findByUsername(String username) {
    return requestUtils.getRequest(
        USER_SERVICE_URL + "/users/username/{username}", UserDto.class, username);
  }
}

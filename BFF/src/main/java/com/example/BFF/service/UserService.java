package com.example.BFF.service;

import com.example.BFF.dto.AccessTokenDto;
import com.example.BFF.dto.AuthDto;
import com.example.BFF.dto.RegisterDto;
import com.example.BFF.dto.UserDto;
import java.util.UUID;

public interface UserService {
  AccessTokenDto auth(AuthDto authDto);

  AccessTokenDto register(RegisterDto registerDto);

  UserDto findById(UUID id);

  UserDto findByUsername(String username);
}

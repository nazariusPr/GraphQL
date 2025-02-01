package com.example.demo.service;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.AuthDto;
import com.example.demo.dto.RegisterDto;

public interface AuthenticationService {
  AccessTokenDto auth(AuthDto authDto);

  AccessTokenDto register(RegisterDto registerDto);
}

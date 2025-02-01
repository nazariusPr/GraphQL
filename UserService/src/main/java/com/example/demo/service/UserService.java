package com.example.demo.service;

import com.example.demo.dto.DetailUserDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.model.User;
import java.util.UUID;

public interface UserService {
  User findById(UUID id);

  User findByEmail(String email);

  User findByUsername(String username);

  User findByIdentifier(String identifier);

  User create(RegisterDto registerDto);

  DetailUserDto read(String identifier);

  DetailUserDto read(UUID id);

  void delete(UUID id);

  void delete(String identifier);
}

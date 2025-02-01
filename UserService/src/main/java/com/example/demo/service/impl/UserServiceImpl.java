package com.example.demo.service.impl;

import com.example.demo.dto.DetailUserDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final UserMapper mapper;

  @Override
  public User findById(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
  }

  @Override
  public User findByEmail(String email) {
    return repository
        .findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
  }

  @Override
  public User findByUsername(String username) {
    return repository
        .findByUsername(username)
        .orElseThrow(
            () -> new EntityNotFoundException("User with username " + username + " not found"));
  }

  @Override
  public User findByIdentifier(String identifier) {
    return repository
        .findByEmail(identifier)
        .or(() -> repository.findByUsername(identifier))
        .orElseThrow(
            () -> new EntityNotFoundException("User with identifier " + identifier + " not found"));
  }

  @Override
  public User create(RegisterDto registerDto) {
    User user = mapper.registerDtoToUser(registerDto);
    return repository.save(user);
  }

  @Override
  public DetailUserDto read(String identifier) {
    User user = findByIdentifier(identifier);
    return mapper.userToDetailUserDto(user);
  }

  @Override
  public DetailUserDto read(UUID id) {
    User user = findById(id);
    return mapper.userToDetailUserDto(user);
  }

  @Override
  public void delete(UUID id) {
    User user = findById(id);
    repository.delete(user);
  }

  @Override
  public void delete(String identifier) {
    User user = findByIdentifier(identifier);
    repository.delete(user);
  }
}

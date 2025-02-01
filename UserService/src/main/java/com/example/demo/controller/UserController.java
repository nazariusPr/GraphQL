package com.example.demo.controller;

import com.example.demo.dto.DetailUserDto;
import com.example.demo.service.UserService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public DetailUserDto getUserById(@PathVariable UUID id) {
    log.info("**/ Request to fetch user by ID: {}", id);
    return userService.read(id);
  }

  @GetMapping("/email/{email}")
  @ResponseStatus(HttpStatus.OK)
  public DetailUserDto getUserByEmail(@PathVariable String email) {
    log.info("**/ Request to fetch user by email: {}", email);
    return userService.read(email);
  }

  @GetMapping("/username/{username}")
  @ResponseStatus(HttpStatus.OK)
  public DetailUserDto getUserByUsername(@PathVariable String username) {
    log.info("**/ Request to fetch user by username: {}", username);
    return userService.read(username);
  }
}

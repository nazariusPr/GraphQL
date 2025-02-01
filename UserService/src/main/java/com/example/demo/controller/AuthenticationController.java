package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDto;
import com.example.demo.dto.AuthDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public AccessTokenDto authenticate(@Valid @RequestBody AuthDto authDto) {
    log.info("**/ Authentication request received for username: {}", authDto.getIdentifier());
    return authenticationService.auth(authDto);
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public AccessTokenDto register(@Valid @RequestBody RegisterDto registerDto) {
    log.info("**/ Registration request received for username: {}", registerDto.getUsername());
    return authenticationService.register(registerDto);
  }
}

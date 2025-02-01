package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
    var user = userService.findByIdentifier(identifier);

    return User.withUsername(identifier)
        .password(user.getPassword())
        .authorities("USER")
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }
}

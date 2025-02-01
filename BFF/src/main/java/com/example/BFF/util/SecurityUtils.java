package com.example.BFF.util;

import com.example.BFF.service.UserService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUtils {
  private final UserService userService;

  public static String getUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (String) authentication.getPrincipal();
  }

  public UUID getUserId() {
    String username = getUsername();
    return userService.findByUsername(username).getId();
  }
}

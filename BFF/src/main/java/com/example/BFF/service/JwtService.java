package com.example.BFF.service;

import io.jsonwebtoken.Claims;
import java.util.function.Function;

public interface JwtService {
  <T> T extractClaims(String token, Function<Claims, T> claimsResolver);

  String extractUsername(String token);

  boolean isTokenExpired(String token);
}

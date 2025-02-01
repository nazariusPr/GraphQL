package com.example.demo.service;

import com.example.demo.model.User;
import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

  <T> T extractClaims(String token, Function<Claims, T> claimsResolver);

  String extractUsername(String token);

  String generateToken(Map<String, Object> extraClaims, User user);

  String generateToken(User user);

  boolean isTokenExpired(String token);

  boolean isTokenValid(String token, User user);
}

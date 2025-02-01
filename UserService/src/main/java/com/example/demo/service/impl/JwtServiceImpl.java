package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
  @Value("${jwt.secrets.access-token}")
  private String ACCESS_TOKEN_SECRET;

  @Value("${jwt.durations.access-token}")
  private Long ACCESS_TOKEN_DURATION;

  @Override
  public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
    Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  @Override
  public String extractUsername(String token) {
    return extractClaims(token, Claims::getSubject);
  }

  @Override
  public String generateToken(Map<String, Object> extraClaims, User user) {
    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_DURATION))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public String generateToken(User user) {
    return generateToken(new HashMap<>(), user);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  @Override
  public boolean isTokenValid(String token, User user) {
    String username = extractUsername(token);
    return username.equals(user.getUsername()) && !isTokenExpired(token);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Date extractExpiration(String token) {
    return extractClaims(token, Claims::getExpiration);
  }
}

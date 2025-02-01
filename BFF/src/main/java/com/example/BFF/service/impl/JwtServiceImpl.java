package com.example.BFF.service.impl;

import com.example.BFF.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
  @Value("${jwt.secrets.access-token}")
  private String ACCESS_TOKEN_SECRET;

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
  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
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

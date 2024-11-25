package com.martin.api.utils;

import com.martin.api.persistence.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  @Value("${jwt.secret.key}")
  private String SECRET_KEY;

  @Value("${jwt.time.expiration}")
  private Long EXPIRATION;

  public String generateToken(User user) {
    Date issueAt = new Date(System.currentTimeMillis());
    Date expirationAt = new Date(issueAt.getTime() + EXPIRATION);
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(issueAt)
        .expiration(expirationAt)
        .signWith(generateKey())
        .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, User user) {
    final String username = extractUsername(token);
    return username.equalsIgnoreCase(user.getUsername()) && !isTokenExpired(token);
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = Jwts.parser()
        .verifyWith(generateKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    return claimResolver.apply(claims);
  }

  private SecretKey generateKey() {
    final byte[] secretBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(secretBytes);
  }

}

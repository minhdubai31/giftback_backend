package com.minhdubai.Giftback.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.minhdubai.Giftback.repository.BlacklistTokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {
   @Value("${jwt.secret}")
   private String secret;
   private final BlacklistTokenRepository blacklistTokenRepository;

   public String generateToken(String username) {
      Map<String, Object> claims = new HashMap<>();
      return createToken(claims, username);
   }

   private String createToken(Map<String, Object> claims, String subject) {
      return Jwts.builder()
         .setClaims(claims)
         .setSubject(subject)
         .setIssuedAt(new Date(System.currentTimeMillis()))
         .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
         .signWith(SignatureAlgorithm.HS256, secret)
         .compact();
   }

   public boolean isValidToken(String token, UserDetails userDetails) {
      final String extractedUsername = extractUsername(token);
      boolean isBlacklisted = blacklistTokenRepository.existsById(token);
      return (extractedUsername.equals(userDetails.getUsername()) && !isExpiredToken(token) && !isBlacklisted);
   }

   public String extractUsername(String token) {
      return extractAllClaims(token).getSubject();
   }

   private boolean isExpiredToken(String token) {
      return extractAllClaims(token).getExpiration().before(new Date());
   }

   private Claims extractAllClaims(String token) {
      return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
   }
}

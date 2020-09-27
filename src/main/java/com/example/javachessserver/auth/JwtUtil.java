package com.example.javachessserver.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    private final String SECRET_KEY = "KUIZsIrFW1Y-w8QgDqlgWLTzAZt-hoxQ6W6m5bRNueSepxGd0QjOV28p";

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

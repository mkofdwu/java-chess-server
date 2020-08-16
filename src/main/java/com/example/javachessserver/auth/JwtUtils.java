package com.example.javachessserver.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "KUIZsIrFW1Y-w8QgDqlgWLTzAZt-hoxQ6W6m5bRNueSepxGd0QjOV28p";

    public static String generateToken(String userId) {
        return Jwts.builder().setSubject(userId).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String extractUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

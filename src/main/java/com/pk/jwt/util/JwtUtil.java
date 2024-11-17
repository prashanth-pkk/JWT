package com.pk.jwt.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "mysecretkey";  // Use a stronger secret key in production

    // Generate JWT token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate JWT token
    public static boolean validateToken(String token, String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken.equals(username) && !isTokenExpired(token));
    }

    // Get username from JWT token
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Check if token is expired
    private static boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private static Date getExpirationDateFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}


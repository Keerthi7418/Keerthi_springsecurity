package com.test.springbootasssignment.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	 private static final String SECRET_KEY = "your_secret_key"; // Change this to your secret key

	    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours



	    // Generate JWT token

	    public String generateToken(String username, String role) {

	        Map<String, Object> claims = new HashMap<>();

	        claims.put("role", role);

	        return createToken(claims, username);

	    }



	    // Create token

	    private String createToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder()

	                .setClaims(claims)

	                .setSubject(subject)

	                .setIssuedAt(new Date(System.currentTimeMillis()))

	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))

	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)

	                .compact();

	    }



	    // Extract username from token

	    public String extractUsername(String token) {

	        return extractClaim(token, Claims:: username);

	    }



	    // Extract role from token

	    public String extractRole(String token) {

	        return (String) extractAllClaims(token).get("role");

	    }



	    // Extract claims from token

	    private Claims extractAllClaims(String token) {

	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

	    }



	    // Extract claim using provided function

	    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

	        final Claims claims = extractAllClaims(token);

	        return claimsResolver.apply(claims);

	    }



	    // Validate token

	    public boolean validateToken(String token, String username) {

	        final String extractedUsername = extractUsername(token);

	        return (extractedUsername.equals(username) && !isTokenExpired(token));

	    }



	    // Check if token is expired

	    private boolean isTokenExpired(String token) {

	        return extractExpiration(token).before(new Date());

	    }



	    // Extract expiration date from token

	    private Date extractExpiration(String token) {

	        return extractClaim(token, Claims::getExpiration);

	    }

}


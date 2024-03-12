package com.test.SpringbootAssignment.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.test.SpringbootAssignment.model.ApiUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private static String secret = "tjxcImNe9wVIe5slY+UwMO9cAna8eBtjrUHNXb2XC77cF1SG9O1cNp2VIAq+dF+smqpaTwYDsimWOIXqZMr1DKmSZSzFF5QMNMD0HJO4gis6+7t/NT2bMrmYLEdj7vnz";

	public Key getSignKey() {
		byte[] keySecret = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keySecret);
	}

	public String generateJwt(ApiUser user) {
		Date issedAt = new Date();

		Claims claims = Jwts.claims().setIssuer(String.valueOf(user.getId())).setIssuedAt(issedAt);

		return Jwts.builder().addClaims(claims).setSubject(user.getName())
				.setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public Claims verify(String authorizaton) throws Exception {

		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorizaton).getBody();
			return claims;

		} catch (Exception e) {
			// throw the new AccessDeniedException("Access Denied")
			throw  new AccessDeniedException("Access Denied");
			//return null;
		}
	}
}

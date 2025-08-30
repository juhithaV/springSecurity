package com.first.firstProject.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.first.firstProject.model.LocalUser;

import jakarta.annotation.PostConstruct;

@Service
public class JWTService {

	@Value("${jwt.algorithm.key}")
	private String algorithmKey;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	@Value("${jwt.expireInSeconds}")
	private int jwtExpireInSeconds;
	
	private Algorithm algorithm;
	
	private static final String USER_NAME = "USERNAME";
	
	@PostConstruct
	public void postconstrct() {
		algorithm = Algorithm.HMAC256(USER_NAME);
	}
	
	public String generateJWT (LocalUser user) {
		return JWT.create()
				.withClaim(USER_NAME, user.getUserName())
				.withExpiresAt(new Date(System.currentTimeMillis() + (1000 * jwtExpireInSeconds)))
				.withIssuer(issuer)
				.sign(algorithm);
	}
	
	public String getUserName (String token) {
		return JWT.decode(token).getClaim(USER_NAME).asString();
	}
}

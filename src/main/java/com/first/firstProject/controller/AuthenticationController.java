package com.first.firstProject.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.firstProject.controller.model.LoginBody;
import com.first.firstProject.controller.model.RegistrationBody;
import com.first.firstProject.model.LocalUser;
import com.first.firstProject.service.AuthenticationService;


@RestController
@RequestMapping("/")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@PostMapping("auth/register")
	public ResponseEntity<String> registerUser(@RequestBody RegistrationBody registrationBody) {
		authenticationService.registerUser(registrationBody);
		return ResponseEntity.ok().body("Successfully Created");
	}

	@PostMapping("auth/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginBody loginBody) {
		String jwtToken = authenticationService.loginService(loginBody);
		ResponseCookie jwtCookie = ResponseCookie.from("JWT", jwtToken)
			    .httpOnly(true)
			    .secure(false)
			    .path("/")
			    .maxAge(Duration.ofHours(2))
			    .build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString()).body("Login Succesful");
	}
	
	@GetMapping("products")
	public ResponseEntity<?> getProducts(@AuthenticationPrincipal LocalUser user) {
		if (user == null) {
			return ResponseEntity.badRequest().body("Authentication Unsuccesful");
		}
		return ResponseEntity.ok().body(user);
	}
}

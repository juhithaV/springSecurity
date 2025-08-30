package com.first.firstProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
	
	private JWTRequestFilter jwtRequestFilter;
	
	public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}
	
	@Bean
	public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
		http.csrf(csrf-> csrf.disable())
			.cors(cors-> cors.disable());
		http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
		http.authorizeHttpRequests(h->h
				.requestMatchers("/auth/register","/auth/login","/products").permitAll()
				.anyRequest().authenticated());
		return http.build();	
		
	}

}

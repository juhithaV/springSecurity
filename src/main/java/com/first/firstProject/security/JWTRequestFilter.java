package com.first.firstProject.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.first.firstProject.model.LocalUser;
import com.first.firstProject.repository.AuthenticationRepository;
import com.first.firstProject.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{
	
	private JWTService jwtService;
	
	private AuthenticationRepository authRepo ;
	
	public JWTRequestFilter(JWTService jwtService,AuthenticationRepository authRepo) {
		this.jwtService = jwtService;
		this.authRepo = authRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenHeader = request.getHeader("Authorization");
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.substring(7);
			try {
				String userName = jwtService.getUserName(token);
				Optional<LocalUser> opUser = authRepo.findByuserNameIgnoreCase(userName);
				if (opUser.isPresent()) {
					LocalUser user = opUser.get();
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
			catch(JWTDecodeException ex) {
				
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}

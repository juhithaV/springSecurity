package com.first.firstProject.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.firstProject.controller.model.LoginBody;
import com.first.firstProject.controller.model.RegistrationBody;
import com.first.firstProject.exception.IncorrectUserCredentials;
import com.first.firstProject.exception.UserAlreadyExistsException;
import com.first.firstProject.mapper.UserMapper;
import com.first.firstProject.model.LocalUser;
import com.first.firstProject.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
			
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired
	private JWTService jwtService;
	
	public void registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
		
		logger.info("Attempting to Log User : {}", registrationBody.getUserName());
		
		if (authenticationRepository.findByuserNameIgnoreCase(registrationBody.getUserName()).isPresent()) {
			throw UserAlreadyExistsException.userNameAlreadyExists();
		}
		
		if (authenticationRepository.findByemailIgnoreCase(registrationBody.getEmail()).isPresent()) {
			logger.warn("Username/Email already exists - {},{}", registrationBody.getEmail(),
					registrationBody.getEmail());
			throw UserAlreadyExistsException.mailAlreadyExists();
		}
		
		LocalUser user = UserMapper.toEntity(registrationBody);
		user.setPassword(encryptionService.encryptPassword(user.getPassword()));
		
		authenticationRepository.save(user);
		
		logger.info("User has successfully registered : {}", registrationBody.getUserName());
	}

	
	public String loginService(LoginBody loginBody) throws IncorrectUserCredentials {
		Optional<LocalUser> opUser = authenticationRepository.findByuserNameIgnoreCase(loginBody.getUserName());
		if (opUser.isPresent()) {
			LocalUser user = opUser.get();
			if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
				return (jwtService.generateJWT(user));
			}
			else {
				throw IncorrectUserCredentials.IncorrectCredentials();
			}
		}
		else {
			throw IncorrectUserCredentials.UserNotExist();
		}
	}
}

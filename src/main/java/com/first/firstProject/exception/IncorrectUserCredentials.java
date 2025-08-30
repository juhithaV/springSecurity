package com.first.firstProject.exception;

public class IncorrectUserCredentials extends RuntimeException{
	
	public IncorrectUserCredentials(String message) {
		super(message);
	}
	
	public static IncorrectUserCredentials IncorrectCredentials() {
		return new IncorrectUserCredentials("Incorrect Credentials");
	}

	public static IncorrectUserCredentials UserNotExist() {
		return new IncorrectUserCredentials("User Doesn't Exist");
	}
}

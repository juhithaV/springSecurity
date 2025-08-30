package com.first.firstProject.exception;

public class UserAlreadyExistsException extends RuntimeException{
	
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
	public static UserAlreadyExistsException userNameAlreadyExists() {
		throw new UserAlreadyExistsException("UserName Already Exists");
	}
	
	public static UserAlreadyExistsException mailAlreadyExists() {
		throw new UserAlreadyExistsException("Mail Already Exists");
	}

}

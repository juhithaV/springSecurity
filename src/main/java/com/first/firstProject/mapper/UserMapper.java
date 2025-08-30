package com.first.firstProject.mapper;

import com.first.firstProject.model.LocalUser;
import com.first.firstProject.controller.model.RegistrationBody;

public class UserMapper {
	
	public static LocalUser toEntity(RegistrationBody registrationBody) {
		LocalUser user = new LocalUser();
		user.setUserName(registrationBody.getUserName());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(registrationBody.getPassword());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        return user;
	}

}

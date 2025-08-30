package com.first.firstProject.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.first.firstProject.model.LocalUser;

public interface AuthenticationRepository extends ListCrudRepository<LocalUser, Long>{

	Optional<LocalUser> findByuserNameIgnoreCase(String userName);
	
	Optional<LocalUser> findByemailIgnoreCase(String email);
	
	
}

package com.first.firstProject.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.first.firstProject.model.LocalUser;
import com.first.firstProject.model.UserOrder;

public interface OrderRepository extends ListCrudRepository<UserOrder, Long> {
	
	List<UserOrder> findByUser(LocalUser user);
	
}

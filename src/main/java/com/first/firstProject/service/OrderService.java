package com.first.firstProject.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.first.firstProject.model.LocalUser;
import com.first.firstProject.model.UserOrder;
import com.first.firstProject.repository.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepository;
	
	public OrderService (OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<UserOrder> getOrders(LocalUser user){
		return this.orderRepository.findByUser(user);
	}

}

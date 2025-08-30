package com.first.firstProject.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.firstProject.model.LocalUser;
import com.first.firstProject.model.UserOrder;
import com.first.firstProject.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController (OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public List<UserOrder> getOrders(@AuthenticationPrincipal LocalUser user) {
		return this.orderService.getOrders(user);
	}
	
}

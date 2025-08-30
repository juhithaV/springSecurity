package com.first.firstProject.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.firstProject.model.Product;
import com.first.firstProject.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	private ProductService productService;
	
	public ProductController(@AuthenticationPrincipal ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> getProducts(){
		return this.productService.getProducts();
	}
}

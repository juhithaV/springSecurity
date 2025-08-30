package com.first.firstProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.firstProject.model.Product;
import com.first.firstProject.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProducts() {
		return this.productRepository.findAll();
	}
}


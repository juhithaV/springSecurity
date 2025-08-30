package com.first.firstProject.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.first.firstProject.model.Product;

public interface ProductRepository extends ListCrudRepository<Product, Long>{
	
	
}

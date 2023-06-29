package com.test.cilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.cilia.domain.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{
    
}

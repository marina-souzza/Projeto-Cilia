package com.test.cilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.cilia.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long> {
    
}

package com.test.cilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.cilia.domain.Client;

public interface ClientRepository  extends JpaRepository <Client, Long>{
    
}

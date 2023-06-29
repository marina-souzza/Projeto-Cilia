package com.test.cilia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.test.cilia.domain.Product;
import com.test.cilia.error.NotFoundException;
import com.test.cilia.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

     public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado. id= " + id));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void update(Product product) {

        if (!productRepository.existsById(product.getId())) {
            throw new NotFoundException("Produto não encontrado. id= " + product.getId());

        }

        productRepository.saveAndFlush(product);
    }

    public void deleteById(Long id) {

        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado. id= " + id);

        }

        productRepository.deleteById(id);
    }
    
}

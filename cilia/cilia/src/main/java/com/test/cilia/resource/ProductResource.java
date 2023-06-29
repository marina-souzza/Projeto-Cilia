package com.test.cilia.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.test.cilia.domain.Product;
import com.test.cilia.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/product")
public class ProductResource {

    @Autowired
    ProductService productService;

     @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.findAll();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {
        Product product;
        product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody Product product, HttpServletRequest request) {

        productService.save(product);
        String path = request.getRequestURI() + product.getId();

        return ResponseEntity.created(URI.create(path)).build();
    }

    @PutMapping
    public ResponseEntity<Product> updateClient(@RequestBody Product product) {

        productService.update(product);

        return ResponseEntity.ok(product);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){
        
        productService.deleteById(id);
        
        return ResponseEntity.noContent().build();
        }


    


}

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
import com.test.cilia.domain.Sale;
import com.test.cilia.service.SaleService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/sale")
public class SaleResource {

    @Autowired
    SaleService saleService;

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {

        List<Sale> sales = saleService.findAll();

        if (sales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sales);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSaleById(@PathVariable("id") Long id) {
        Sale sale;
        sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public ResponseEntity<Void> saveClient(@RequestBody Sale sale, HttpServletRequest request) {

        saleService.save(sale);
        String path = request.getRequestURI() + sale.getId();

        return ResponseEntity.created(URI.create(path)).build();
    }

    @PutMapping
    public ResponseEntity<Sale> updateClient(@RequestBody Sale sale) {

        saleService.update(sale);

        return ResponseEntity.ok(sale);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long id) {

        saleService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}

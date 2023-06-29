package com.test.cilia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.test.cilia.domain.Sale;
import com.test.cilia.error.NotFoundException;
import com.test.cilia.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

     public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venda não encontrada. id= " + id));
    }

    public void save(Sale sale) {
        saleRepository.save(sale);
    }

    public void update(Sale sale) {

        if (!saleRepository.existsById(sale.getId())) {
            throw new NotFoundException("Venda não encontrada. id= " + sale.getId());

        }

        saleRepository.saveAndFlush(sale);
    }

    public void deleteById(Long id) {

        if (!saleRepository.existsById(id)) {
            throw new NotFoundException("Venda não encontrada. id= " + id);

        }

        saleRepository.deleteById(id);
    }
    
    

}

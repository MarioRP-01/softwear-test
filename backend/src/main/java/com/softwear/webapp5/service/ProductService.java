package com.softwear.webapp5.service;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public void save(Product product){
        repository.save(product);
    }
}
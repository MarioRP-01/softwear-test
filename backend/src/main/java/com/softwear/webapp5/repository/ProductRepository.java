package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.softwear.webapp5.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {
    public List<Product> findById(String code);
    public List<Product> findByName(String name);
    public List<Product> findByType(String type);
    public List<Product> modify(long id);
}

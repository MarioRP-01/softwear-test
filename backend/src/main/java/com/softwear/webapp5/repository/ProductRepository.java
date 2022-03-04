package com.softwear.webapp5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softwear.webapp5.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {

    public Page<Product> findByName(String name, Pageable page);
    public Page<Product> findByDescription(String description, Pageable page);
    public Page<Product> findByPrice(double price, Pageable page);
    public Page<Product> findByStock(Long stock, Pageable page);
    public Page<Product> findBySupplier(String supplier, Pageable page);
    public Page<Product> findByPlaceMade(String placeMade, Pageable page);
    public Page<Product> findByBrand(String brand, Pageable page);
    public Page<Product> findByManufactDate(String ManufactDate, Pageable page);
}

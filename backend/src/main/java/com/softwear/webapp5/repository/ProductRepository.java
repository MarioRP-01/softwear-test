package com.softwear.webapp5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.data.StaticsDTO;
import com.softwear.webapp5.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {

    public Page<Product> findByName(String name, Pageable page);
    public Page<Product> findByDescription(String description, Pageable page);
    public Page<Product> findByPrice(double price, Pageable page);
    public Page<Product> findByStock(Long stock, Pageable page);
    public Page<Product> findBySize(ProductSize size, Pageable page);
    Optional<Product> findByNameAndSize(String name, ProductSize size);

    @Query(value = "SELECT DISTINCT size FROM Product WHERE name = :name AND stock <> 0", nativeQuery = true)
    public List<ProductSize> FindSizeAvailableByName(@Param("name") String name);
}

package com.softwear.webapp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.softwear.webapp5.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {
    public List<Product> findByName(String name);
    public List<Product> findByPrice(double price);
    public List<Product> findByDescription(String description);
    public List<Product> findByImgRoutes(String img_routes);
    public List<Product> findByStock(int stock);
    public List<Product> findBySupplier(String supplier);
    public List<Product> findByMadeIn(String madeIn);
    public List<Product> findByBrand(String brand);
}

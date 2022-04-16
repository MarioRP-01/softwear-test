package com.softwear.webapp5.data;

import java.util.List;

import com.softwear.webapp5.model.Product;

public class ProductPageDTO {

    private List<Product> products;
    private int totalPages;
    
    public ProductPageDTO(List<Product> products, int totalPages) {
        this.products = products;
        this.totalPages = totalPages;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
 
}

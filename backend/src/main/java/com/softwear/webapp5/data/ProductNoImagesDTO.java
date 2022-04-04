package com.softwear.webapp5.data;

public class ProductNoImagesDTO {

    private String name;
    private String description;
    private double price;
    private Long stock;
    private ProductSize size;

    public ProductNoImagesDTO(String name, String description, double price, Long stock, ProductSize size) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public ProductSize getSize() {
        return size;
    }

    public void setSize(ProductSize size) {
        this.size = size;
    }
    
}
